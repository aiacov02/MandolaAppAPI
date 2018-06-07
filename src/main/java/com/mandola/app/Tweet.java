package com.mandola.app;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {

    private int ID;
    private String Url;
    private String Description;
    private ArrayList<String> Category;
    private ArrayList<String> Authority;
    private String Date;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<String> getCategory() {
        return Category;
    }

    public void setCategory(ArrayList<String> category) {
        Category = category;
    }

    public ArrayList<String> getAuthority() {
        return Authority;
    }

    public void setAuthority(ArrayList<String> authority) {
        Authority = authority;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public float getHateSpeechPercent() {
        return HateSpeechPercent;
    }

    public void setHateSpeechPercent(float hateSpeechPercent) {
        HateSpeechPercent = hateSpeechPercent;
    }

    private float HateSpeechPercent;


    public Tweet(int ID, String url, String description, String category, String authority, String date) {
        this.ID = ID;
        Url = url;
        Description = description;
        Category = new ArrayList<>(Arrays.asList(ArrayString.convertStringToArray(category)));
        Authority = new ArrayList<>(Arrays.asList(ArrayString.convertStringToArray(authority)));
        Date = date;
    }
}
