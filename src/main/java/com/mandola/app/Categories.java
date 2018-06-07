package com.mandola.app;

public class Categories {

    public static final int Religious = 0;
    public static final int Gender = 1;
    public static final int Sexual=2;
    public static final int Class = 3;
    public static final int Politics=4;
    public static final int Ethnicity = 5;
    public static final int Nationality = 6;
    public static final int Other = 7;
    public static int Number = 8;

    public static int getCatNumber(String mystring){
        switch (mystring) {
            case "Religious": {
                return Religious;
            }
            case "Gender": {
                return Gender;
            }
            case "Sexual": {
                return Sexual;
            }
            case "Class": {
                return Class;
            }
            case "Politics": {
                return Politics;
            }
            case "Ethnicity": {
                return Ethnicity;
            }
            case "Nationality": {
                return Nationality;
            }
            default: {
                return Other;
            }

        }
    }

}
