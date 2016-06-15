package com.wt.xwttravel.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.ChatListAdapter;
import com.wt.xwttravel.dao.MessageDao;
import com.wt.xwttravel.model.Message;
import com.wt.xwttravel.util.ObjectParser;
import com.wt.xwttravel.view.ResizableRelativeLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.simgenius.commons.internetutilities.JSONObjectGet;
import cn.simgenius.commons.internetutilities.JSONObjectPost;
import cn.simgenius.commons.internetutilities.ParamBuilder;


public class ChatActivity extends Activity {


    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;
    long targetUserId = 1;
//    long targetUserId = 2;


    ListView listView;
    ResizableRelativeLayout baseLayout;
    List<Message> messageList;
    long latestTime;

    MessageDao dao;
    Button btnSend;
    EditText txtInput;
    MessageArriveReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    void init() {
        initVariable();
        initComponent();
        setListeners();
        setListView();
//        setDatabaseData();
        setInternetData();
        setReceiver();
    }

    //用于初始化变量
    void initVariable() {
        messageList = new ArrayList<Message>();
        dao = new MessageDao(ChatActivity.this);
//        setDefaultData();
    }

    //用于初始化控件
    void initComponent() {
        listView = (ListView) findViewById(R.id.listView);
        baseLayout = (ResizableRelativeLayout) findViewById(R.id.baseLayout);
        btnSend = (Button) findViewById(R.id.btnSend);
        txtInput = (EditText) findViewById(R.id.txtInput);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners() {

        baseLayout.setOnSizeChangeListener(new ResizableRelativeLayout.OnSizeChangeListener() {
            @Override
            public void onSizeChange(int width, int height, int oldWidth, int oldHeight) {
                if (oldHeight > height)
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            moveToBottom();
                        }
                    }, 500);


            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtInput.getText().toString();
                if (!text.equals(""))
                    sendMessage(TYPE_TEXT, text);
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void setListView() {
        LinearLayout topPadding = new LinearLayout(ChatActivity.this);
        topPadding.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) (Constants.DIVICE_DENSITY * 30)));
        listView.addHeaderView(topPadding);

        LinearLayout bottomPadding = new LinearLayout(ChatActivity.this);
        bottomPadding.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) (Constants.DIVICE_DENSITY * 10)));
        listView.addHeaderView(topPadding);
        listView.addFooterView(bottomPadding);
        listView.setAdapter(new ChatListAdapter(messageList, this));
        moveToBottom();


    }

    void loadMessage() {
        messageList = dao.getLatest20Messages(Constants.USER_ID,targetUserId);
    }



//    void setDefaultData(){
//        messageList = new ArrayList<Message>();
//        messageList.add(new Message(1, 1, 2, 12345, 1, "content1a", 1, 0));
//        messageList.add(new Message(2, 2, 1, 12346, 1, "content2b", 1, 0));
//        messageList.add(new Message(3, 1, 2, 12347, 1, "content3a", 1, 0));
//        messageList.add(new Message(4, 1, 2, 12348, 1, "content4a", 1, 0));
//        messageList.add(new Message(5, 2, 1, 12349, 1, "content5b", 1, 0));
//        messageList.add(new Message(6, 2, 1, 12350, 1, "content6b", 1, 0));
//        messageList.add(new Message(7, 1, 2, 12351, 1, "content7a", 1, 0));
//        messageList.add(new Message(8, 2, 1, 12352, 1, "content8b", 1, 0));
//        messageList.add(new Message(9, 2, 1, 12353, 1, "content9b", 1, 0));
//        messageList.add(new Message(10,1, 2, 12354, 1, "content10a", 1, 0));
//        messageList.add(new Message(11,2, 1, 12355, 1, "content11b", 1, 0));
//    }

    void setInternetData(){



        new JSONObjectPost("http://" + Constants.SERVER_IP_IM + "/receive",
                new ParamBuilder()
                        .add("action", "receiveDialog")
                        .add("token", Constants.TOKEN)
                        .add("targetUserId", targetUserId)
                        .add("time", System.currentTimeMillis())
                        .build(), new JSONObjectPost.OnTaskFinishListener() {
            @Override
            public void onTaskFinish(JSONObject result) {
                try {
                    if (result.getString("status").equals("new message")){
                        List<Message> tempMessageList = ObjectParser.parseMessageList(result.getJSONArray("message"),true);
                        messageList.clear();
                        messageList.addAll(tempMessageList);
                        listView.deferNotifyDataSetChanged();
                        moveToBottom();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    void setDatabaseData(){
        messageList.clear();
        List<Message> list = dao.getLatest20Messages(Constants.USER_ID, targetUserId);
        messageList.addAll(list);
        Collections.reverse(messageList);
        listView.deferNotifyDataSetChanged();
        moveToBottom();
    }

    void sendMessage(int type, String content){
        final Message message = new Message(0, Constants.USER_ID, targetUserId, System.currentTimeMillis(), type, content, 0, 0);
        messageList.add(message);
        listView.deferNotifyDataSetChanged();

        Map<String,String> params = new ParamBuilder()
                .add("token", Constants.TOKEN)
                .add("message", ObjectParser.toJSONObjectString(message))
                .build();

        new JSONObjectPost("http://" + Constants.SERVER_IP_IM + "/send", params, new JSONObjectPost.OnTaskFinishListener() {
            @Override
            public void onTaskFinish(JSONObject result) {
                try {
                    if (result.getString("status").equals("sent")){
                        message.setId(result.getLong("msgId"));
                        dao.insert(message);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        moveToBottom();
        txtInput.setText("");
    }

    void confirmMessage(){

    }

    void moveToBottom(){
        listView.setSelection(listView.getAdapter().getCount() - 1);
    }

    public class MessageArriveReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("com.wt.xwttravel.ChatActivity")){
                if(intent.getStringExtra("action").equals("new message")){
                    Message message = ObjectParser.parseMessage(intent.getStringExtra("message"));
                    messageList.add(message);
                    listView.deferNotifyDataSetChanged();
                    moveToBottom();
                    new JSONObjectGet("http://simgenius.cn/xwtim/confirm?id=" + message.getId() + "&token=" + Constants.TOKEN,
                            new JSONObjectGet.OnTaskFinishListener() {
                        @Override
                        public void onTaskFinish(JSONObject result) {

                        }
                    });
                }

            }
        }
    }

    public void setReceiver() {
        try {
            receiver = new MessageArriveReceiver();
            registerReceiver(receiver, new IntentFilter("com.wt.xwttravel.ChatActivity"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
