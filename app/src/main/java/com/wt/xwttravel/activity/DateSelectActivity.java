package com.wt.xwttravel.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.DateSelectGridAdapter;
import com.wt.xwttravel.model.DateCube;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateSelectActivity extends Activity {

    List<DateCube> dateCubeList;
    boolean animating = false;
    GridView gridView;
    Button btnLastMonth,btnNextMonth;
    int monthDelta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_date_select);
        init();
    }

    void init(){
        initVariable();
        initComponent();
        setListeners();
        setDateCubeList(0);
        gridView.setAdapter(new DateSelectGridAdapter(DateSelectActivity.this,dateCubeList));
        openAnim();
    }

    //用于初始化变量
    void initVariable(){
        dateCubeList = new ArrayList<DateCube>();
    }

    //用于初始化控件
    void initComponent(){
        gridView = (GridView) findViewById(R.id.gridView);
        btnLastMonth = (Button) findViewById(R.id.btnLastMonth);
        btnNextMonth = (Button) findViewById(R.id.btnNextMonth);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        findViewById(R.id.bottomSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animating)
                    endAnim();
            }
        });

        btnLastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setDateCubeList();
            }
        });
    }


    void setDateCubeList(int monthDelta){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,monthDelta);


        //获得当月的第一天是星期几，以此来决定之前空几个格
        calendar.set(Calendar.DAY_OF_MONTH,1);
        int blank = calendar.get(Calendar.DAY_OF_WEEK)-1;


        //先生成空格
        for(int i = 0; i < blank; i++){
            Date date = new Date();

            int color = Color.parseColor("#aaaaaa");
            date.setDate(i+1);
            dateCubeList.add(
                    new DateCube(date, "", color));
        }


        //时间转移到下个月的第一天，再往回腿一天，就知道这个月一共有多少天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int black = calendar.get(Calendar.DAY_OF_MONTH);


        //生成有日期的格子
        for(int i = 0; i < black; i++){
            Date date = new Date();
            int color = Color.parseColor("#555555");
            date.setDate(i+1);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            dateCubeList.add(
                    new DateCube(date, Integer.toString(i+1), color));
        }


    }

    void openAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) findViewById(R.id.bottomSpace);
        final RelativeLayout calendar = (RelativeLayout) findViewById(R.id.contentArea);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(DateSelectActivity.this, R.anim.in_from_top);
        Animation backgroundAnimation = AnimationUtils.loadAnimation(DateSelectActivity.this, R.anim.fade_in);
        final Animation buttonAnimation = AnimationUtils.loadAnimation(DateSelectActivity.this, R.anim.fade_alpha_100_20);

        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                calendar.setVisibility(View.VISIBLE);
                calendar.startAnimation(calendarAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        buttonAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.btnA).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnB).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnA).setAlpha(0);
                findViewById(R.id.btnB).setAlpha(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        calendarAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animating = false;
                findViewById(R.id.btnA).startAnimation(buttonAnimation);
                findViewById(R.id.btnB).startAnimation(buttonAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        background.startAnimation(backgroundAnimation);

    }

    void endAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) findViewById(R.id.bottomSpace);
        final RelativeLayout calendar = (RelativeLayout) findViewById(R.id.contentArea);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(DateSelectActivity.this, R.anim.out_from_top);
        final Animation backgroundAnimation = AnimationUtils.loadAnimation(DateSelectActivity.this, R.anim.fade_out);

        calendarAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                calendar.setVisibility(View.INVISIBLE);
                background.startAnimation(backgroundAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.baseLayout).setVisibility(View.INVISIBLE);
                animating = false;
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        calendar.startAnimation(calendarAnimation);

    }

    @Override
    public void onBackPressed() {
        if(!animating)
            endAnim();
    }




}
