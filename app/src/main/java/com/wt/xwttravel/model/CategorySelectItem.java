package com.wt.xwttravel.model;

/**
 * Created by Genius on 2/26/16.
 */
public class CategorySelectItem {

    int id;
    String name;
    int count;

    public CategorySelectItem(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public CategorySelectItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
