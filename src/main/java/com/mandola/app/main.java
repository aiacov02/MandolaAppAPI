package com.mandola.app;

import com.google.gson.Gson;
// import org.tc33.jheatchart.HeatChart;

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

//         get("/getTwitterReports", (request, response) -> {
//             response.type("application/json");


//             try {
//                 StatisticsController.RetrieveTweets();

//                 return "200";
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 return "500";
//             }

//         });

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
