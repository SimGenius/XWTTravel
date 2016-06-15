package com.wt.xwttravel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.R;
import com.wt.xwttravel.util.ImageUtil;
import com.wt.xwttravel.util.QRUtil;

public class MyOrderDetailActivity extends Activity {

    ImageView imgQR;
    WebView webView;
    SimpleDraweeView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_detail);
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
        img = (SimpleDraweeView) findViewById(R.id.img);
        ImageUtil.loadImage(img,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg");
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://dl.simgenius.cn/xwt/webpage/orderdetail2.html");
        imgQR = (ImageView) findViewById(R.id.imgQR);
        imgQR.setImageBitmap(QRUtil.generateQRCode("http://zhidao.baidu.com/link?url=XQ_oWX-DNO366QTpGEHflelSEUAw2CSwrC0gQOO2ekRVLAkuAiTquvTFZv2hBrAJ8ep7os0UDLoCBSlT6R1su963nGQnCDAnH-cbxZIo6f_"));
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
}
