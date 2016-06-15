package com.wt.xwttravel.dao;

import android.content.ContentProvider;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wt.xwttravel.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genius on 3/29/16.
 */

//"CREATE TABLE qt_im" +
//        "(" +
//        "    id BIGINT(20) PRIMARY KEY NOT NULL," +
//        "    user_id INTEGER(20) NOT NULL," +
//        "    target_user_id INTEGER(20) NOT NULL," +
//        "    send_time BIGINT(20) NOT NULL," +
//        "    type INT(11) NOT NULL," +
//        "    content TEXT NOT NULL," +
//        "    sent INT(11) DEFAULT '0' NOT NULL," +
//        "    baidupush_request_id INTEGER(20) DEFAULT '0' NOT NULL" +
//        ")" ;

public class MessageDao {
    DBHelper helper;

    public MessageDao(Context context) {
        helper = new DBHelper(context);
    }


    public void insert(Message message) {
        String sql = "insert into qt_im(id,user_id,target_user_id,send_time,type,content,sent,baidupush_request_id) values(?,?,?,?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{message.getId(), message.getUserId(), message.getTargetUserId(), message.getSendTime(), message.getType(), message.getContent(), message.getSent(), message.getBaidupushRequestId()});
    }

    public void update(Message message) {
        String sql = "update qt_im set user_id=?,target_user_id=?,send_time=?,type=?,content=?,sent=?,baidupush_request_id=? where id=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{message.getUserId(), message.getTargetUserId(), message.getSendTime(), message.getType(), message.getContent(), message.getSent(), message.getBaidupushRequestId(), message.getId()});
    }


    public void delete(int messageId) {
        String sql = "delete from qt_im where id = ?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{messageId});
    }

    public Message findOne(int id) {
        List<Message> list = new ArrayList<Message>();
        Message message = null;
        String sql = "select * from alex_contact where id=" + Integer.toString(id);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return parseMessage(cursor);
    }

    public List<Message> findAll() {
        List<Message> list = new ArrayList<Message>();
        String sql = "select * from qt_im";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            list.add(new Message(
                    cursor.getLong(1),
                    cursor.getLong(2),
                    cursor.getLong(3),
                    cursor.getLong(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getLong(8)
            ));
        }
        cursor.close();
        return list;
    }

    public List<Message> search(String keyword) {
        List<Message> list = new ArrayList<Message>();
        String sql = "select * from qt_im where content like '%" + keyword + "%'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return parseMessageList(cursor);
    }

    public List<Message> getLatest20Messages(long user_id, long target_user_id) {


        String sql = "select * from qt_im where user_id=" + Long.toString(user_id) + " and target_user_id=" + Long.toString(target_user_id) + " order by send_time desc limit 20";
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(sql, null);
            return parseMessageList(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Message> getMessageBefore(long user_id, long target_user_id, long beforeTime) {
        List<Message> list = new ArrayList<Message>();
        String sql = "select * from qt_im where user_id=" + Long.toString(user_id) + " and target_user_id=" + Long.toString(target_user_id) + " and send_time<" + Long.toString(beforeTime) + " order by send_time desc limit 20";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return parseMessageList(cursor);
    }


    //"CREATE TABLE qt_im" +
//        "(" +
//        "    id BIGINT(20) PRIMARY KEY NOT NULL," +
//        "    user_id INTEGER(20) NOT NULL," +
//        "    target_user_id INTEGER(20) NOT NULL," +
//        "    send_time BIGINT(20) NOT NULL," +
//        "    type INT(11) NOT NULL," +
//        "    content TEXT NOT NULL," +
//        "    sent INT(11) DEFAULT '0' NOT NULL," +
//        "    baidupush_request_id INTEGER(20) DEFAULT '0' NOT NULL" +
//        ")" ;
    private List<Message> parseMessageList(Cursor cursor) {

        int id = cursor.getColumnIndex("id");
        int user_id = cursor.getColumnIndex("user_id");
        int target_user_id = cursor.getColumnIndex("target_user_id");
        int send_time = cursor.getColumnIndex("send_time");
        int type = cursor.getColumnIndex("type");
        int content = cursor.getColumnIndex("content");
        int sent = cursor.getColumnIndex("sent");


        try {
            List<Message> list = new ArrayList<Message>();
            while (cursor.moveToNext()) {
                list.add(new Message(
                        cursor.getLong(id),
                        cursor.getLong(user_id),
                        cursor.getLong(target_user_id),
                        cursor.getLong(send_time),
                        cursor.getInt(type),
                        cursor.getString(content),
                        cursor.getInt(sent),
                        0
                ));
            }
            cursor.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Message>();
    }

    private Message parseMessage(Cursor cursor) {
        Message message = null;
        int id = cursor.getColumnIndex("id");
        int user_id = cursor.getColumnIndex("user_id");
        int target_user_id = cursor.getColumnIndex("target_user_id");
        int send_time = cursor.getColumnIndex("send_time");
        int type = cursor.getColumnIndex("type");
        int content = cursor.getColumnIndex("content");
        int sent = cursor.getColumnIndex("sent");
        while (cursor.moveToNext()) {
            message = new Message(
                    cursor.getLong(id),
                    cursor.getLong(user_id),
                    cursor.getLong(target_user_id),
                    cursor.getLong(send_time),
                    cursor.getInt(type),
                    cursor.getString(content),
                    cursor.getInt(sent),
                    0
            );
        }
        cursor.close();
        return message;
    }

}
