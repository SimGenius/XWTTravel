package com.wt.xwttravel.model;

import android.os.Parcelable;

/**
 * Created by Genius on 3/25/16.
 */
public class Message implements Comparable<Message>{

    long id;
    long userId;
    long targetUserId;
    long sendTime;
    int type;
    String content;
    int sent;      //0:未发送, 1:已上传到本服务器但没有推送成功, 2:已发送到百度云推送服务器但未确认收到信息 -1:已推送到对方设备
    long baidupushRequestId;

    public Message(long id, long userId, long targetUserId, long sendTime, int type, String content, int sent, long baidupushRequestId) {
        this.id = id;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.sendTime = sendTime;
        this.type = type;
        this.content = content;
        this.sent = sent;
        this.baidupushRequestId = baidupushRequestId;
    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public long getBaidupushRequestId() {
        return baidupushRequestId;
    }

    public void setBaidupushRequestId(long baidupushRequestId) {
        this.baidupushRequestId = baidupushRequestId;
    }

    @Override
    public int compareTo(Message another) {
        return (int) (this.sendTime - another.sendTime);
    }
}
