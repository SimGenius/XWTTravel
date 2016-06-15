package com.wt.xwttravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.wt.xwttravel.R;

public class PayConfirmActivity extends Activity {


    Button btnPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_confirm);
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
        btnPay = (Button) findViewById(R.id.btnPay);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayConfirmActivity.this,PaySelectActivity.class));
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
