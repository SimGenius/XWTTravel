package com.wt.xwttravel.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;

import java.io.File;

import cn.simgenius.commons.internetutilities.StringGet;

public class PaySuccessActivity extends Activity {

    WebView webView;
    StringGet get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        init();
    }


    void init(){
        initVariable();
        initComponent();
        setListeners();
    }

    //用于初始化变量
    void initVariable(){

    }

    //用于初始化控件
    void initComponent(){
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://dl.simgenius.cn/xwt/webpage/orderdetail2.html");
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("+8618600000000");
            }
        });

        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(39.750346, 116.33571);
            }
        });

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share("分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容分享内容");
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void openMap(double latitude, double longitude){
        Intent intent = new Intent(PaySuccessActivity.this,MapActivity.class);
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude",longitude);
        startActivity(intent);
    }

    void share(String content){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享到社交网络");
        intent.putExtra(Intent.EXTRA_TEXT,content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    void call(String phone){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);   //android.intent.action.DIAL
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }


}
