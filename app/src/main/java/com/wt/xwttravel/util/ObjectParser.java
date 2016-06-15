package com.wt.xwttravel.util;

import com.wt.xwttravel.model.FriendItem;
import com.wt.xwttravel.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Genius on 4/6/16.
 */
public class ObjectParser {

    public static Message parseMessage(JSONObject json) {
        try {
            return new Message(
                    json.getLong("id"),
                    json.getLong("userId"),
                    json.getLong("targetUserId"),
                    json.getLong("sendTime"),
                    json.getInt("type"),
                    json.getString("content"),
                    json.getInt("sent"),
                    json.getLong("baidupushRequestId")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Message parseMessage(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return new Message(
                    jsonObject.getLong("id"),
                    jsonObject.getLong("userId"),
                    jsonObject.getLong("targetUserId"),
                    jsonObject.getLong("sendTime"),
                    jsonObject.getInt("type"),
                    jsonObject.getString("content"),
                    jsonObject.getInt("sent"),
                    jsonObject.getLong("baidupushRequestId")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Message> parseMessageList(JSONArray newMessageList, boolean reverse) {

        try {
            List<Message> messages = new ArrayList<Message>();
            for (int i = 0; i < newMessageList.length(); ++i) {
                JSONObject message = newMessageList.getJSONObject(i);
                messages.add(new Message(
                        message.getLong("id"),
                        message.getLong("userId"),
                        message.getLong("targetUserId"),
                        message.getLong("sendTime"),
                        message.getInt("type"),
                        message.getString("content"),
                        message.getInt("sent"),
                        message.getLong("baidupushRequestId")
                ));
            }
            if (reverse) {
                Collections.reverse(messages);
            }
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    {"baidupushRequestId":0,"content":"This is a test message.","id":2,"sendTime":1459132094253,"sent":1,"targetUserId":1,"type":1,"userId":2}
    public static JSONObject toJSONObject(Message message) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("baidupushRequestId", 0);
            jsonObject.put("content", message.getContent());
            jsonObject.put("id", 0);
            jsonObject.put("sendTime", message.getSendTime());
            jsonObject.put("sent", 0);
            jsonObject.put("type", message.getType());
            jsonObject.put("targetUserId", message.getTargetUserId());
            jsonObject.put("userId", message.getUserId());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJSONObjectString(Message message) {
        return toJSONObject(message).toString();
    }

    public static List<FriendItem> parseFriendItemList(JSONArray json) {
        List<FriendItem> list = new ArrayList<FriendItem>();

        try {
            for (int i = 0; i < json.length(); ++i) {
                JSONObject jsonObject = json.getJSONObject(i);
                list.add(new FriendItem(
                        jsonObject.getLong("userId"),
                        jsonObject.getString("nickname"),
                        jsonObject.getString("lastMessage"),
                        jsonObject.getLong("lastTime"),
                        jsonObject.getString("picUrl")
                ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
