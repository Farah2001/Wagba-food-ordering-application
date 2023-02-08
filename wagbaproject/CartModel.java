package com.example.wagbaproject;

import android.widget.Button;

public class CartModel {
    //hold data representing one of the items - the Dish - the image and the name of it as text, price and button add to cart
    String itemname;
    String itemprice;
    String itemimg;
    String key;

    int quantity;

    float totalPrice;

    public CartModel() {
    }

    public CartModel(String itemname, String itemprice, String itemimg, String key, int quantity, float totalPrice) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemimg = itemimg;
        this.key = key;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemimg() {
        return itemimg;
    }

    public void setItemimg(String itemimg) {
        this.itemimg = itemimg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    //    public CartModel(String itemname, String itemprice, int itemimg, int quantity) {
//        this.itemname = itemname;
//        this.itemprice = itemprice;
//        this.itemimg = itemimg;
//        this.quantity = quantity;
//    }
//
//    public String getItemname() {
//        return itemname;
//    }
//
//    public String getItemprice() {
//        return itemprice;
//    }
//
//    public int getItemimg() {
//        return itemimg;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
}

