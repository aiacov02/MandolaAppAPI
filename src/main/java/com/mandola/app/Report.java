package com.mandola.app;

public class Report {

    private int ID;
    private String App;
    private String Url;
    private String Description;
    private String Category;
    private String Authority;
    private String Date;


    public String getApp(){
        return App;
    }

    public String getAuthority() {
        return Authority;
    }


    public String getUrl() {
        return Url;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public String getDate() {
        return Date;
    }

    public Report(int id,String app, String Url, String Description, String Category, String Authority, String Date){
        this.ID = id;
        this.Url = Url;
        this.App = app;
        this.Authority = Authority;
        this.Category = Category;
        this.Description = Description;
        this.Date = Date;
    }

}
