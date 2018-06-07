package com.mandola.app;

import com.google.gson.Gson;
import org.tc33.jheatchart.HeatChart;

import java.awt.*;
import java.io.File;

import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.white;
import static spark.Spark.get;
import static spark.Spark.post;

public class main {
    public static void main(String[] args) {

        post("/addReport", (request, response) -> {
            response.type("application/json");
            Report report = new Gson().fromJson(request.body(), Report.class);

            System.out.println("Report: " + report.getDescription());

            try {
                MySQLAccess mySQLAccess = MySQLAccess.getInstance();
                mySQLAccess.addReport(report);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "200";


        });

        get("/getTwitterReports", (request, response) -> {
            response.type("application/json");


            try {
                StatisticsController.RetrieveTweets();

                return "200";
            } catch (Exception e) {
                e.printStackTrace();
                return "500";
            }

        });

        get("/test", (request, response) -> {
            response.type("application/json");


            double[][] data = new double[][]{{3,2,3,4,5,6},
                    {2,3,4,5,6,7},
                    {3,4,5,6,7,6},
                    {4,5,6,7,6,5}};

            // Step 1: Create our heat map chart using our data.
            HeatChart map = new HeatChart(data);

            // Step 2: Customise the chart.
            map.setTitle("This is my heat chart title");
            map.setXAxisLabel("X Axis");
            map.setYAxisLabel("Y Axis");
            map.setLowValueColour(white);
            map.setHighValueColour(blue);
            map.setXValues(new Object[]{"Religious","Gender","Sexual","Class","Politics","Ethnicity","Nationality","Other"});
            map.setXValuesHorizontal(false);
            map.setYValues(new Object[]{});
            map.setXAxisLabel("Categories");
            map.setYAxisLabel("Time Periods");
            Dimension dimension = new Dimension();
            dimension.height = 50;
            dimension.width=50;
            map.setCellSize(dimension);


            // Step 3: Output the chart to a file.
            map.saveToFile(new File("java-heat-chart.png"));

            return "200";

        });

//        get("/CalculateStatistics", (request, response) -> {
//            response.type("application/json");
//
//
//            try {
//                StatisticsController.CalculateStatistics();
//
//                return "200";
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "500";
//            }
//
//        });



        post("/addPictureReport", (request, response) -> {
            response.type("application/json");
            PictureReport report = new Gson().fromJson(request.body(), PictureReport.class);

            System.out.println("Report: " + report.getDescription());

            try {
                MySQLAccess mySQLAccess = MySQLAccess.getInstance();
                mySQLAccess.addPictureReport(report);
                return "200";
            } catch (Exception e) {
                e.printStackTrace();
                return "500";
            }




        });



    }

}
