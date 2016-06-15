package com.wt.xwttravel.activity;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;

import org.json.JSONException;
import org.json.JSONObject;

import cn.simgenius.commons.internetutilities.JSONObjectPost;
import cn.simgenius.commons.internetutilities.JSONObjectPost2;
import cn.simgenius.commons.internetutilities.StringPost;
import cn.simgenius.commons.shapes.RoundCorner;

public class LoginActivity extends Activity {


    Button btnLogin;
    EditText txtUsername,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setBackgroundDrawable(new RoundCorner("#d0584d","#c0483d", 5, Constants.DIVICE_DENSITY).getDrawable());

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        txtUsername.performClick();
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONObjectPost2("http://simgenius.cn/xwt/login")
                        .add("username",txtUsername.getText().toString())
                        .add("password",txtPassword.getText().toString())
                        .send(new JSONObjectPost2.OnTaskFinishListener() {
                            @Override
                            public void onTaskFinish(JSONObject result){
                                try {
                                    if (result.getString("status").equals("login success")) {
                                        String token = result.getString("token");
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

}
