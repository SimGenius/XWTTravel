package com.wt.xwttravel.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wt.xwttravel.R;
import com.wt.xwttravel.activity.DateSelectActivity;
import com.wt.xwttravel.adapter.DateSelectGridAdapter;
import com.wt.xwttravel.model.DateCube;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateSelectFragment extends Fragment {

    View view;

    List<DateCube> dateCubeList;
    boolean animating = false;
    GridView gridView;
    Button btnLastMonth,btnNextMonth;
    int monthDelta = 0;
    TextView txtYearMonth;


    public DateSelectFragment() {



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_date_select,null);
        init();

        return view;
    }


    void init(){
        initVariable();
        initComponent();
        setListeners();
        setDateCubeList(0);
        gridView.setAdapter(new DateSelectGridAdapter(getActivity(), dateCubeList));
        openAnim();
    }

    //用于初始化变量
    void initVariable(){
        dateCubeList = new ArrayList<DateCube>();
    }

    //用于初始化控件
    void initComponent(){
        gridView = (GridView) view.findViewById(R.id.gridView);

        btnLastMonth = (Button) view.findViewById(R.id.btnLastMonth);
        btnNextMonth = (Button) view.findViewById(R.id.btnNextMonth);
        txtYearMonth = (TextView) view.findViewById(R.id.txtYearMonth);

        view.findViewById(R.id.bottomSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animating)
                    endAnim();
            }
        });


    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){

        view.findViewById(R.id.dateBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.weekBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.bottomSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animating)
                    endAnim();
            }
        });


        btnLastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCubeList.clear();
                setDateCubeList(--monthDelta);
                gridView.setAdapter(new DateSelectGridAdapter(getActivity(), dateCubeList));
            }
        });

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateCubeList.clear();
                setDateCubeList(++monthDelta);
                gridView.setAdapter(new DateSelectGridAdapter(getActivity(), dateCubeList));
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

        setTitleText(calendar);

    }

    void setTitleText(Calendar calendar){
        Calendar thisMonth = Calendar.getInstance();
        Calendar nextMonth = Calendar.getInstance();
        Calendar lastMonth = Calendar.getInstance();
        thisMonth.setTime(calendar.getTime());
        nextMonth.setTime(calendar.getTime());
        lastMonth.setTime(calendar.getTime());
        nextMonth.add(Calendar.MONTH, 1);
        lastMonth.add(Calendar.MONTH,-1);

        btnLastMonth.setText(Integer.toString(lastMonth.get(Calendar.MONTH)+1) + "月");
        btnNextMonth.setText(Integer.toString(nextMonth.get(Calendar.MONTH)+1) + "月");
        txtYearMonth.setText(Integer.toString(thisMonth.get(Calendar.YEAR))+"年"+Integer.toString(thisMonth.get(Calendar.MONTH)+1)+"月");

    }

    void openAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        final RelativeLayout calendar = (RelativeLayout) view.findViewById(R.id.contentArea);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.in_from_top);
        Animation backgroundAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

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

        calendarAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animating = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        background.startAnimation(backgroundAnimation);

    }

    void endAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        final RelativeLayout calendar = (RelativeLayout) view.findViewById(R.id.contentArea);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.out_from_top);
        final Animation backgroundAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

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
                view.findViewById(R.id.baseLayout).setVisibility(View.INVISIBLE);
                animating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        calendar.startAnimation(calendarAnimation);

    }


}
