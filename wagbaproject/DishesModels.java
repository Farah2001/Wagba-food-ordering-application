package com.example.wagbaproject;

import android.widget.Button;

public class DishesModels {
    //hold data representing one of the items - the Dish - the image and the name of it as text, price and button add to cart
    String DishName;
    String DishPrice;
    // Button addtocart;
    String DishImg;
    String key;


    DishesModels() {

    }

    public DishesModels(String dishName, String dishPrice, String dishImg, String key) {
        DishName = dishName;
        DishPrice = dishPrice;
        DishImg = dishImg;
        this.key = key;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDishPrice() {
        return DishPrice;
    }

    public void setDishPrice(String dishPrice) {
        DishPrice = dishPrice;
    }

    public String getDishImg() {
        return DishImg;
    }

    public void setDishImg(String dishImg) {
        DishImg = dishImg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    //    public DishesModels(String dishName, String dishPrice, String dishImg, String logoImg) {
//        DishName = dishName;
//        DishPrice = dishPrice;
//        DishImg = dishImg;
//
//    }
//
//
//    public String getDishName() {
//        return DishName;
//    }
//
//    public void setDishName(String dishName) {
//        DishName = dishName;
//    }
//
//    public String getDishPrice() {
//        return DishPrice;
//    }
//
//    public void setDishPrice(String dishPrice) {
//        DishPrice = dishPrice;
//    }
//
//    public String getDishImg() {
//        return DishImg;
//    }
//
//    public void setDishImg(String dishImg) {
//        DishImg = dishImg;
//    }
}
//    public DishesModels(String dishName, String dishPrice, Button addtocart, int imageDish) {
//        DishName = dishName;
//        DishPrice = dishPrice;
//        this.addtocart = addtocart;
//        this.imageDish = imageDish;
//    }
//
//    public String getDishName() {
//        return DishName;
//    }
//
//    public String getDishPrice() {
//        return DishPrice;
//    }
//
//    public Button getAddtocart() {
//        return addtocart;
//    }
//
//    public int getImageDish() {
//        return imageDish;
//    }


