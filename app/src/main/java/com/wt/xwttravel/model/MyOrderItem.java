package com.wt.xwttravel.model;

/**
 * Created by Genius on 3/8/16.
 */
public class MyOrderItem {

    int id;
    String title;
    String info;
    float price;
    int status;
    String picUrl;

    public MyOrderItem(int id, String title, String info, float price, int status, String picUrl) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.price = price;
        this.status = status;
        this.picUrl = picUrl;
    }

    public MyOrderItem(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
