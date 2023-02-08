package com.example.wagbaproject;

import java.util.ArrayList;

public class OrderHistoryModel {
    private String status, date, clientUid, gate, time;
    private ArrayList<CartModel> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getClientUid() {
        return clientUid;
    }

    public void setClientUid(String clientUid) {
        this.clientUid = clientUid;
    }

    public ArrayList<CartModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartModel> items) {
        this.items = items;
    }

    public OrderHistoryModel(String status, String date, String clientUid, String gate, String time, ArrayList<CartModel> items) {
        this.status = status;
        this.date = date;
        this.clientUid = clientUid;
        this.gate = gate;
        this.time = time;
        this.items = items;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
