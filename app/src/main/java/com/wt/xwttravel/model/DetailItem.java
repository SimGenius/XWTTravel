package com.wt.xwttravel.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Genius on 2/2/16.
 */
public class DetailItem {

    private int id;
    private String title;
    private String subtitle;
    private String important;
    private Date fromDate;
    private Date toDate;
    private int sold;
    private float price;
    private List<String> picUrl;

    public DetailItem(int id, String title, String subtitle, String important, Date fromDate, Date toDate, int sold, float price, List<String> picUrl) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.important = important;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.sold = sold;
        this.price = price;
        this.picUrl = picUrl;
    }

    public DetailItem() {
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<String> picUrl) {
        this.picUrl = picUrl;
    }
}
