package com.wt.xwttravel.model;

/**
 * Created by Genius on 10/30/15.
 */
public class ItemPreview {
    long id;
    String title;
    String subTitle;
    float price;
    int sold;
    String picUrl;

    public ItemPreview(long id, String title, String subTitle, float price, int sold, String picUrl) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
        this.sold = sold;
        this.picUrl = picUrl;
    }

    public ItemPreview(long id, String title, String subTitle) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }

    public ItemPreview(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public ItemPreview() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

}
