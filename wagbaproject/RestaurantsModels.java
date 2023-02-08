package com.example.wagbaproject;
//Model class
public class RestaurantsModels {
    //hold data representing one of the items - the restaurant - the image and the ame of it as text
    String RestImg;
    String RestName;

    RestaurantsModels() {

    }

    public RestaurantsModels(String restImg, String restName) {
        RestImg = restImg;
        RestName = restName;

    }

    public String getRestImg() {
        return RestImg;
    }

    public void setRestImg(String restImg) {
        RestImg = restImg;
    }

    public String getRestName() {
        return RestName;
    }

    public void setRestName(String restName) {
        RestName = restName;
    }
}

//    public RestaurantsModels(String restImg, String restName, ) {
//        RestImg = restImg;
//        RestName = restName;
//    }
//
//    public String getRestImg() {
//        return RestImg;
//    }
//
//    public void setRestImg(String restImg) {
//        RestImg = restImg;
//    }
//
//    public String getRestName() {
//        return RestName;
//    }
//
//    public void setRestName(String restName) {
//        RestName = restName;
//    }

