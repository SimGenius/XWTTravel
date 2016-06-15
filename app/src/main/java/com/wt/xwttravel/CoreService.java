package com.wt.xwttravel;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.wt.xwttravel.dao.MessageDao;
import com.wt.xwttravel.model.Message;
import com.wt.xwttravel.util.ObjectParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.simgenius.commons.internetutilities.JSONObjectGet;
import cn.simgenius.commons.internetutilities.JSONObjectPost;
import cn.simgenius.commons.internetutilities.ParamBuilder;

public class CoreService extends Service {

    MessageDao messageDao;
//    CoreServerReceiver receiver;


    public CoreService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if(receiver == null){
//            receiver = new CoreServerReceiver();
//            registerReceiver(receiver,new IntentFilter("com.wt.xwttravel.CoreServiceReceiver"));
//        }

        try {
            String msg = intent.getStringExtra("msg");
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getString("action").equals("receive message")) {
                int msgId = jsonObject.getInt("msgId");
                new JSONObjectPost("http://"+Constants.SERVER_IP_IM +"/receive",
                        new ParamBuilder().add("token", Constants.TOKEN).add("action", "receiveSingle").add("msgId", msgId).build(),
                        new JSONObjectPost.OnTaskFinishListener() {
                            @Override
                            public void onTaskFinish(JSONObject result) {
                                try {
                                    if (result.getString("status").equals("new single message")){
                                        Message message = ObjectParser.parseMessage(result.getJSONObject("message"));
                                        new MessageDao(CoreService.this).insert(message);
                                        Intent intent2 = new Intent("com.wt.xwttravel.ChatActivity");
                                        intent2.putExtra("action", "new message");
                                        intent2.putExtra("message",result.getJSONObject("message").toString());
                                        sendBroadcast(intent2);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);

    }



    private void getDialogMessageFromServer(String token, long userId, long targetUserId){
        StringBuilder builder = new StringBuilder();
        builder.append("http://").append(Constants.SERVER_IP).append(":8080/receive?")
                .append("token=").append(token)
                .append("&userId").append(userId)
                .append("&targetUserId").append(targetUserId);
        new JSONObjectGet(builder.toString(), new JSONObjectGet.OnTaskFinishListener() {
            @Override
            public void onTaskFinish(JSONObject result) {
                try {
                    if (result.getString("status").equals("login failed")){
                        requestLogin();
                    }else {
                        saveMessagesToDatabase(parseMessageList(result.getJSONArray("message")));
                        notifyMessageUpdated();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


    private void requestLogin(){

    }

    private void notifyMessageUpdated() {
        Intent intent = new Intent("com.wt.xwttravel.MessageReceiver");
        intent.putExtra("msg","MessageUpdated");
        sendBroadcast(intent);
    }

    List<Message> parseMessageList(JSONArray newMessageList) {

        List<Message> messages = new ArrayList<Message>();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }


    void saveMessagesToDatabase(List<Message> savingMessageList) {
        for (Message message : savingMessageList) {
            saveMessageToDatabase(message);
        }
    }

    private void saveMessageToDatabase(Message message) {
        try {
            messageDao.insert(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class CoreServerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


}
