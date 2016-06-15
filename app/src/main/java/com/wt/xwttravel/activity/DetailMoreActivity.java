package com.wt.xwttravel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;

import java.util.HashMap;
import java.util.Map;

public class DetailMoreActivity extends Activity {

    Handler handler;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_more);
        init();

    }

    void init(){
        initVariable();
        initComponent();
        setListeners();
        setHandler();
    }

    //用于初始化变量
    void initVariable(){
        
    }

    //用于初始化控件
    void initComponent(){
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://dl.simgenius.cn/xwt/img/untitled.html");

    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    void setHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){

                }
            }
        };
    }

}
