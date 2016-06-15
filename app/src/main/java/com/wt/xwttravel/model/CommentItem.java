package com.wt.xwttravel.model;

import java.util.Date;

/**
 * Created by Genius on 2/2/16.
 */
public class CommentItem {

    int id;
    int userId;
    String nickName;
    String picUrl;
    Date submitTime;
    String comment;

    public CommentItem() {
    }

    public CommentItem(int id, int userId, String nickName, String picUrl, Date submitTime, String comment) {
        this.id = id;
        this.userId = userId;
        this.nickName = nickName;
        this.picUrl = picUrl;
        this.submitTime = submitTime;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
