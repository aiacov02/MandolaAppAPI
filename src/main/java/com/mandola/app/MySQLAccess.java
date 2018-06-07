package com.mandola.app;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static MySQLAccess instance;

    private MySQLAccess(){
        //defeat instantiation
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        // Setup the connection with the DB
        try{
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/Mandola?"
                            + "user=root&password=1234");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static MySQLAccess getInstance(){
        if(instance==null){
            instance = new MySQLAccess();
        }
        return instance;
    }

    public boolean addReport(Report report){
        String table=null;
        // PreparedStatements can use variables and are more efficient
        if(report.getApp().equals("twitter")){
            table = "TwitterReport";
        }
        else if(report.getApp().equals("facebook")){
            table = "FacebookReport";
        }
        else if(report.getApp().equals("browser")){
            table = "BrowserReport";
        }
        else{
            return false;
        }

        try{
            preparedStatement = connect
                    .prepareStatement("insert into  Mandola."+table+" values (default, ?, ?, ?, ? , NOW())");
            // Parameters start with 1
            preparedStatement.setString(1, report.getDescription());
            preparedStatement.setString(2, report.getCategory());
            preparedStatement.setString(3, report.getAuthority());
            preparedStatement.setString(4, report.getUrl());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean addPictureReport(PictureReport report){
        String table="PictureReport";
        // PreparedStatements can use variables and are more efficient

        try{
            preparedStatement = connect
                    .prepareStatement("insert into  Mandola."+table+" values (default, ?, ?, ?, ? ,?, NOW())");
            // Parameters start with 1
            preparedStatement.setString(1, report.getDescription());
            preparedStatement.setString(2, report.getCategory());
            preparedStatement.setString(3, report.getAuthority());
            Blob blob = new SerialBlob(report.getImage());
            preparedStatement.setBlob(4,blob);
            preparedStatement.setString(5,report.getLocation());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        FileOutputStream stream = null;

        BufferedImage image = DbBitmapUtility.createRGBImage(report.getImage(), 50, 50);

        try {
            stream = new FileOutputStream("sonoo.jpg");
            ImageIO.write(image, "BMP", stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }



    public ArrayList<Tweet> readTwitterReports() throws Exception {
        try {


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from Mandola.TwitterReport");
            return writeTwitterResultSet(resultSet);


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }


    private ArrayList<Tweet> writeTwitterResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        ArrayList<Tweet> tweets = new ArrayList<>();
        int index=1;
        while (resultSet.next()) {

            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            int id = resultSet.getInt("ID");
            String description = resultSet.getString("DESCRIPTION");
            String category = resultSet.getString("CATEGORY");
            String authority = resultSet.getString("AUTHORITY");
            Date date = resultSet.getDate("DATE");
            String URL = resultSet.getString("URL");
            tweets.add(new Tweet(id,URL,description,category,authority,date.toString()));


        }
        return tweets;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}