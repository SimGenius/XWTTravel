package com.wt.xwttravel.model;

/**
 * Created by Genius on 4/12/16.
 */
public class FriendItem {
    long userId;
    String nickname;
    String lastMessage;
    long lastTime;
    String picUrl;

    public FriendItem(long userId, String nickname, String lastMessage, long lastTime, String picUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.picUrl = picUrl;
    }

    public FriendItem() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
