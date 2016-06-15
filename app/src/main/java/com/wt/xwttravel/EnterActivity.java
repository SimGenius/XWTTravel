package com.wt.xwttravel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.wt.xwttravel.activity.MainActivity;
import com.wt.xwttravel.util.ImageUtil;


public class EnterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        initScreenProperties();
        setUserInfo();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        }, 0);



    }




    void initScreenProperties(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constants.DIVICE_DENSITY = displayMetrics.density;
        Constants.DIVICE_HEIGHT_PX = displayMetrics.heightPixels;
        Constants.DIVICE_WIDTH_PX = displayMetrics.widthPixels;
        Constants.DIVICE_HEIGHT_DP = (int)((float)displayMetrics.heightPixels/displayMetrics.density);
        Constants.DIVICE_WIDTH_DP = (int)((float)displayMetrics.widthPixels/displayMetrics.density);
    }

    void setUserInfo(){
        Constants.TOKEN = "testtoken";
        Constants.USER_ID = 2;

//        Constants.TOKEN = "supertoken";
//        Constants.USER_ID = 1;
    }


}
