package com.wt.xwttravel.model;

import java.util.Date;

/**
 * Created by Genius on 2/15/16.
 */
public class DateCube {

    Date date;
    String Text;

    int color;

    public DateCube(Date date, String text, int color) {
        this.date = date;
        Text = text;
        this.color = color;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }



    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
