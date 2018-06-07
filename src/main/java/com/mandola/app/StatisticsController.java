package com.mandola.app;


import com.google.gson.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
// import org.tc33.jheatchart.HeatChart;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.blue;
import static java.awt.Color.white;

public class StatisticsController {

    private static int alreadyRemovedTweets = 0;

//    public static void CalculateStatistics() throws Exception {
//
//        ArrayList<Tweet> tweets = RetrieveSavedTweets();
//
//        for (Tweet tweet : tweets) {
//            String data = "{\"text\":\"" + tweet.getStatus().getText() + "\"}";
//            OutputStream out = null;
//            try {
//                URL url = new URL("https://mandola.grid.ucy.ac.cy:8080/api/analyze-text");
//
//                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setDoOutput(true);
//                urlConnection.setDoInput(true);
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//
//                out = new BufferedOutputStream(urlConnection.getOutputStream());
//
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//
//                writer.write(data);
//
//                writer.flush();
//
//                writer.close();
//
//                out.close();
//
//                //urlConnection.connect();
//
//                BufferedReader br = new BufferedReader((new InputStreamReader(urlConnection.getInputStream())));
//                String s;
//                while ((s = br.readLine()) != null) {
//                    String result[] = s.split(" ");
//                    if (!result[0].equals("404")) {
//                        int indexOfPercent = result[0].indexOf("%");
//                        if (indexOfPercent != -1) {
//                            String hatespeechString = result[0].substring(0, indexOfPercent);
//                            Float hatespeech = Float.parseFloat(hatespeechString);
//                            tweet.setHateSpeechPercent(hatespeech);
//                            System.out.println("Percent"+hatespeech);
//                        }
//                    }
//                }
//
//
//                System.out.println(urlConnection.getResponseMessage());
//
//                System.out.println(urlConnection.getResponseCode());
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    public static ArrayList<Tweet> RetrieveSavedTweets(){

        ArrayList<Tweet> tweets=null;
        Tweet tweet = null;
        try{
            JsonParser jsonParser = new JsonParser();
            // Convert JSON Array String into JSON Array
            JsonObject a = (JsonObject) jsonParser.parse(new FileReader("tweets.json"));
            JsonArray jsonArray = a.getAsJsonArray("tweets");

            if(jsonArray.size()>0) tweets = new ArrayList<>();
            for(int i=0; i<jsonArray.size(); i++){
                tweet = new Gson().fromJson(jsonArray.get(i),Tweet.class);
                tweets.add(tweet);
            }



        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return tweets;
        }



    }


//     public static void RetrieveTweets() throws Exception{
//         MySQLAccess mySQLAccess = MySQLAccess.getInstance();
//         ArrayList<Tweet> tweetsOld = mySQLAccess.readTwitterReports();
//         System.out.println(tweetsOld.size());
//         ArrayList<Tweet> tweets = getTweets(tweetsOld);

// //        Gson gson = new Gson();
// //        String json = gson.toJson(tweets);
// //        String mystring = "{ \"tweets\" : " + json + "}";
// //        JsonParser parser = new JsonParser();
// //        JsonObject jsonObject = parser.parse(mystring).getAsJsonObject();
// //        System.out.println(jsonObject.toString());
// //        try (Writer writer = new FileWriter("tweets.json")) {
// //            gson = new GsonBuilder().create();
// //            gson.toJson(jsonObject, writer);
// //        }
// //        catch (IOException e){
// //            e.printStackTrace();
// //        }
// //
// //        System.out.println(alreadyRemovedTweets);
// //
// //        System.out.println(tweets.size());

//         for (Tweet tweet : tweets) {
//             String TweetText = tweet.getStatus().getText().replaceAll("[^A-Za-z0-9\\.\\?\\!\\,\\’\\s]","\\s");
//             System.out.println(TweetText);
//             String data = "{\"text\":\"" + TweetText + "\"}";
//             OutputStream out = null;
//             try {
//                 URL url = new URL("https://mandola.grid.ucy.ac.cy:8080/api/analyze-text");

//                 HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

//                 urlConnection.setRequestMethod("POST");
//                 urlConnection.setDoOutput(true);
//                 urlConnection.setDoInput(true);
//                 urlConnection.setRequestProperty("Content-Type", "application/json");

//                 out = new BufferedOutputStream(urlConnection.getOutputStream());

//                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

//                 writer.write(data);

//                 writer.flush();

//                 writer.close();

//                 out.close();

//                 //urlConnection.connect();

//                 BufferedReader br = new BufferedReader((new InputStreamReader(urlConnection.getInputStream())));
//                 String s;
//                 while ((s = br.readLine()) != null) {
//                     String result[] = s.split(" ");
//                     if (!result[0].equals("400")) {
//                         int indexOfPercent = result[0].indexOf("%");
//                         if (indexOfPercent != -1) {
//                             String hatespeechString = result[0].substring(0, indexOfPercent);
//                             Float hatespeech = Float.parseFloat(hatespeechString);
//                             tweet.setHateSpeechPercent(hatespeech);
//                             System.out.println("Percent"+hatespeech);
//                         }
//                     }
//                     else {
//                         System.out.println("400 " + TweetText);
//                     }
//                 }

//             } catch (Exception e) {
//                 e.printStackTrace();
//             }

//         }


//         int ZeroHateSpeechCount = 0, ZeroToTwentyHateSpeechCount = 0, TwentyToFiftyHateSpeechCount = 0, FiftyToSeventyHateSpeechCount = 0, SeventyToHundredHAteSpeechCount = 0;
//         int[] CategoriesCount = new int[Categories.Number];
//         double [][] CatTimeCount = new double[8][6];
//         for(int i=0; i<CategoriesCount.length; i++){
//             CategoriesCount[i]=0;
//         }

//         Map<String, Integer> countriesTest = new HashMap<>();


//         int tweetswithhatespeech = 0;

//         int _0_4 = 0, _4_8 = 0, _8_12 = 0, _12_16 = 0, _16_20 = 0, _20_24 = 0;

//         for(Tweet tweet : tweets) {
//             float HateSpeech = tweet.getHateSpeechPercent();
//             if (HateSpeech == 0) {
//                 ZeroHateSpeechCount++;
//             } else if (HateSpeech <= 20) {
//                 ZeroToTwentyHateSpeechCount++;
//             } else if (HateSpeech <= 50) {
//                 TwentyToFiftyHateSpeechCount++;
//             } else if (HateSpeech <= 70) {
//                 FiftyToSeventyHateSpeechCount++;
//             } else if (HateSpeech <= 100) {
//                 SeventyToHundredHAteSpeechCount++;
//             }

//             if (!(HateSpeech == 0)) {
//                 tweetswithhatespeech++;
//                 ArrayList<String> categories = tweet.getCategory();


//                 try{

//                     int hours = tweet.getStatus().getCreatedAt().getHours();

//                     int minutes = tweet.getStatus().getCreatedAt().getMinutes();


//                     String hours2 = ""+hours;
//                     String minutes2 = ""+minutes;


//                     LocalTime time;
//                     if(hours<10){
//                         hours2 = "0"+hours;
//                     }
//                     if(minutes<10){
//                         minutes2= "0"+minutes;
//                     }


//                     time = LocalTime.parse(hours2+":"+minutes2);
//                     if(isTimeBetween(time,LocalTime.parse("00:00"),LocalTime.parse("03:59"))){
//                         _0_4++;
//                         for (String cat : categories) {
//                             CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][0]++;
//                         }
//                     }
//                     else if(isTimeBetween(time,LocalTime.parse("04:00"),LocalTime.parse("07:59"))){
//                         _4_8++;
//                         for (String cat : categories) {
//                             CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][1]++;
//                         }
//                     }
//                     else if(isTimeBetween(time,LocalTime.parse("08:00"),LocalTime.parse("11:59"))){
//                         _8_12++;
//                         for (String cat : categories) {
//                             CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][2]++;
//                         }
//                     }
//                     else if(isTimeBetween(time,LocalTime.parse("12:00"),LocalTime.parse("15:59"))){
//                         _12_16++;
//                         for (String cat : categories) {
//                             //CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][3]++;
//                         }
//                     }
//                     else if(isTimeBetween(time,LocalTime.parse("16:00"),LocalTime.parse("19:59"))){
//                         _16_20++;
//                         for (String cat : categories) {
//                             CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][4]++;
//                         }
//                     }
//                     else if(isTimeBetween(time,LocalTime.parse("20:00"),LocalTime.parse("23:59"))){
//                         _20_24++;
//                         for (String cat : categories) {
//                             CategoriesCount[Categories.getCatNumber(cat)]++;
//                             CatTimeCount[Categories.getCatNumber(cat)][5]++;
//                         }
//                     }


//                     String country = tweet.getStatus().getPlace().getCountry();

//                     if(!countriesTest.containsKey(country)){
//                         countriesTest.put(country,1);
//                     }else {
//                         int count = countriesTest.get(country);
//                         count++;
//                         countriesTest.put(country,count);
//                     }

//                 }catch (Exception e){
//                     e.printStackTrace();
//                     //System.err.println("No time information");
//                 }

//             }

//         }

//         System.out.println("All reports: " + tweets.size());

//         System.out.println("Reports with hate speech: " + tweetswithhatespeech);

//         ////////////////////////////////////////////////////
//         //Number of reports per hate speech percentage

//         System.out.println("Number of reports per hate speech percentage: ");

//         DefaultCategoryDataset objDataset = new DefaultCategoryDataset();

//         objDataset.setValue(ZeroHateSpeechCount,"Q1","0%");
//         objDataset.setValue(ZeroToTwentyHateSpeechCount,"Q1","1-20%");
//         objDataset.setValue(TwentyToFiftyHateSpeechCount,"Q1","20-50%");
//         objDataset.setValue(FiftyToSeventyHateSpeechCount,"Q1","50-70%");
//         objDataset.setValue(SeventyToHundredHAteSpeechCount,"Q1","70-100%");

//         JFreeChart objChart = ChartFactory.createBarChart(
//                 "Reports per hate speech percentage",     //Chart title
//                 "Hate Speech Percentage",     //Domain axis label
//                 "Number of reports",         //Range axis label
//                 objDataset,         //Chart Data
//                 PlotOrientation.VERTICAL, // orientation
//                 true,             // include legend?
//                 true,             // include tooltips?
//                 false             // include URLs?
//         );

//         ChartFrame frame = new ChartFrame("Demo", objChart);
//         frame.pack();
//         frame.setVisible(true);



//         ///////////////////////////////////////////////////////////////////////////////
//         //Number of reports per category


//         DefaultCategoryDataset objDataset2 = new DefaultCategoryDataset();

//         objDataset2.setValue(CategoriesCount[Categories.Religious],"Q1","Religious");
//         objDataset2.setValue(CategoriesCount[Categories.Gender],"Q1","Gender");
//         objDataset2.setValue(CategoriesCount[Categories.Sexual],"Q1","Sexual");
//         objDataset2.setValue(CategoriesCount[Categories.Class],"Q1","Class");
//         objDataset2.setValue(CategoriesCount[Categories.Politics],"Q1","Politics");
//         objDataset2.setValue(CategoriesCount[Categories.Ethnicity],"Q1","Ethnicity");
//         objDataset2.setValue(CategoriesCount[Categories.Nationality],"Q1","Nationality");
//         objDataset2.setValue(CategoriesCount[Categories.Other],"Q1","Other");



//         JFreeChart objChart2 = ChartFactory.createBarChart(
//                 "Reports per Category",     //Chart title
//                 "Category",     //Domain axis label
//                 "Number of reports",         //Range axis label
//                 objDataset2,         //Chart Data
//                 PlotOrientation.VERTICAL, // orientation
//                 true,             // include legend?
//                 true,             // include tooltips?
//                 false             // include URLs?
//         );

//         ChartFrame frame2 = new ChartFrame("Demo", objChart2);
//         frame2.pack();
//         frame2.setVisible(true);


//         ///////////////////////////////////////////////////////////////////////////////////////////
//         //Reports per country

//         DefaultCategoryDataset objDataset3 = new DefaultCategoryDataset();

//         for(Map.Entry<String, Integer> country : countriesTest.entrySet()){
//             objDataset3.setValue(country.getValue(),"Q1",country.getKey());
//         }

//         JFreeChart objChart3 = ChartFactory.createBarChart(
//                 "Reports per Country",     //Chart title
//                 "Country",     //Domain axis label
//                 "Number of reports",         //Range axis label
//                 objDataset3,         //Chart Data
//                 PlotOrientation.VERTICAL, // orientation
//                 true,             // include legend?
//                 true,             // include tooltips?
//                 false             // include URLs?
//         );

//         ChartFrame frame3 = new ChartFrame("Demo", objChart3);
//         frame3.pack();
//         frame3.setVisible(true);

//         ///////////////////////////////////////////////////////////////////////////////////////////
//         //Tweets per time period

//         DefaultCategoryDataset objDataset4 = new DefaultCategoryDataset();

//         objDataset4.setValue(_0_4,"Q1","0-3:59");
//         objDataset4.setValue(_4_8,"Q1","4-7:59");
//         objDataset4.setValue(_8_12,"Q1","8-11:59");
//         objDataset4.setValue(_12_16,"Q1","12-15:59");
//         objDataset4.setValue(_16_20,"Q1","16-19:59");
//         objDataset4.setValue(_20_24,"Q1","20-23:59");


//         JFreeChart objChart4 = ChartFactory.createBarChart(
//                 "Reports per Time period",     //Chart title
//                 "Time Period",     //Domain axis label
//                 "Number of reports",         //Range axis label
//                 objDataset4,         //Chart Data
//                 PlotOrientation.VERTICAL, // orientation
//                 true,             // include legend?
//                 true,             // include tooltips?
//                 false             // include URLs?
//         );

//         ChartFrame frame4 = new ChartFrame("Demo", objChart4);
//         frame4.pack();
//         frame4.setVisible(true);


//         /////////////////////////////////////////////////////////////
//         //Heatmap

//         double[][] data = new double[][]{{3,2,3,4,5,6},
//                 {2,3,4,5,6,7},
//                 {3,4,5,6,7,6},
//                 {4,5,6,7,6,5}};


//         // Step 1: Create our heat map chart using our data.
//         HeatChart map = new HeatChart(CatTimeCount);

//         // Step 2: Customise the chart.
//         map.setTitle("This is my heat chart title");
//         map.setXAxisLabel("X Axis");
//         map.setYAxisLabel("Y Axis");
//         map.setLowValueColour(white);
//         map.setHighValueColour(blue);
//         map.setYValues(new Object[]{"Religious","Gender","Sexual","Class","Politics","Ethnicity","Nationality","Other"});
//         map.setXValuesHorizontal(false);
//         map.setXValues(new Object[]{"0-3:59","4-7:59","8-11:59","12-15:59","16-19:59","20-23:59"});
//         map.setXAxisLabel("Categories");
//         map.setYAxisLabel("Hate Speech Percentage");
//         Dimension dimension = new Dimension();
//         dimension.height = 50;
//         dimension.width=50;
//         map.setCellSize(dimension);


//         // Step 3: Output the chart to a file.
//         map.saveToFile(new File("java-heat-chart.png"));





//     }

    private static boolean isTimeBetween(LocalTime time, LocalTime start, LocalTime end){
        return !time.isBefore(start) && !time.isAfter(end);
    }



        final static String CONSUMER_KEY = "Nmax4RjcLVbXITiEoT9WUmghO";
        final static String CONSUMER_SECRET = "aC6DehGiIqptErsACUckjcJULRmktfnDvSeDtKgb9OIVOMhSeY";
        final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
        final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/lookup.json?id=963901732806782977";

   private static ArrayList<Tweet> getTweets(ArrayList<Tweet> tweets2) {

       ConfigurationBuilder cb = new ConfigurationBuilder();
       cb.setDebugEnabled(true).setApplicationOnlyAuthEnabled(true).setTweetModeExtended(true);
       TwitterFactory tf = new TwitterFactory(cb.build());
       Twitter twitter = tf.getInstance();
       twitter.setOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);

       ArrayList<Tweet> tweetsToRemove = new ArrayList<>();
       try {
           twitter.getOAuth2Token();
           String id="";
           int index=0;
           for(Tweet tweet : tweets2){
               index++;
               //if(index==5) break;
               String words[] = tweet.getUrl().split("status/");
               if(words.length>1){
                   String words2[] = words[1].split("\\?");
                   if(words2.length>1){
                       id = words2[0];
                   }
               }
               try {
                   Status status = twitter.showStatus(Long.parseLong(id));
                   tweet.setStatus(status);
               }catch (Exception e){
                   //e.printStackTrace();
                   tweetsToRemove.add(tweet);
                   alreadyRemovedTweets++;
               }

           }



       } catch (TwitterException te) {
           te.printStackTrace();
           System.out.println("Failed to search tweets: " + te.getMessage());
       }

       tweets2.removeAll(tweetsToRemove);

       return tweets2;
   }


}
