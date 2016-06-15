package com.wt.xwttravel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wt.xwttravel.R;

public class PaySelectActivity extends Activity {

    LinearLayout btnAlipay,btnWechatPay,btnUnionPay;
    Button btnPay;
    int choice = 0;
    static final int ALIPAY = 1;
    static final int WECHAT = 2;
    static final int UNIONPAY = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_select);
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
        btnAlipay = (LinearLayout) findViewById(R.id.btnAlipay);
        btnUnionPay = (LinearLayout) findViewById(R.id.btnUnionPay);
        btnWechatPay = (LinearLayout) findViewById(R.id.btnWechatPay);
        btnPay = (Button) findViewById(R.id.btnPay);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWechatPay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnUnionPay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnAlipay.setBackgroundColor(Color.parseColor("#dddddd"));
                choice = ALIPAY;
            }
        });

        btnWechatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAlipay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnUnionPay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnWechatPay.setBackgroundColor(Color.parseColor("#dddddd"));
                choice = WECHAT;
            }
        });

        btnUnionPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWechatPay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnAlipay.setBackgroundColor(Color.parseColor("#ffffff"));
                btnUnionPay.setBackgroundColor(Color.parseColor("#dddddd"));
                choice = UNIONPAY;
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {

            boolean notified = false;

            @Override
            public void onClick(View v) {
                if(notified) {
                    switch (choice) {
                        case WECHAT:
                            Toast.makeText(PaySelectActivity.this, "正在使用微信支付", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), PaySuccessActivity.class));
                            break;
                        case ALIPAY:
                            Toast.makeText(PaySelectActivity.this, "正在使用支付宝支付", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), PaySuccessActivity.class));
                            break;
                        case UNIONPAY:
                            Toast.makeText(PaySelectActivity.this, "正在使用银联卡支付", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), PaySuccessActivity.class));
                            break;
                        case 0:
                            Toast.makeText(PaySelectActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }else {
                    notified = true;
                    Toast.makeText(PaySelectActivity.this, "这是演示版本,将直接跳转支付成功页面,请再次点击支付", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }

}
