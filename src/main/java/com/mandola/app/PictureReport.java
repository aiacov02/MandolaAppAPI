package com.mandola.app;

public class PictureReport {

    private String Image;
    private String Location;
    private String Description;
    private String Category;
    private String Authority;
    private String Date;
    private static boolean already;


    public byte[] getImage() {
        return DbBitmapUtility.decodeBase64(Image);
    }

    public String getLocation() {
        return Location;
    }


    public String getAuthority() {
        return Authority;
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
}
