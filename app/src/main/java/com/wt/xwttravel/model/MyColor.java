package com.wt.xwttravel.model;

/**
 * Created by Genius on 3/23/16.
 */
public class MyColor implements Comparable<MyColor> {

    String name;
    int value;
    int position;

    @Override
    public int compareTo(MyColor another) {
        return value-another.getValue();
    }

    public MyColor(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
