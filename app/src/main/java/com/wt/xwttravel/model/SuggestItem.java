package com.wt.xwttravel.model;

/**
 * Created by Genius on 1/24/16.
 */
public class SuggestItem {

    int id;
    String title;
    String subtitle;
    float price;
    int sold;
    String pic;

    public SuggestItem(int id, String title, String subtitle, float price, int sold, String pic) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.sold = sold;
        this.pic = pic;
    }

    public SuggestItem() {
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
