package com.wt.xwttravel.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v13.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.CoreService;
import com.wt.xwttravel.R;
import com.wt.xwttravel.fragment.DateSelectFragment;
import com.wt.xwttravel.fragment.FriendFragment;
import com.wt.xwttravel.fragment.IndexFragment;
import com.wt.xwttravel.fragment.SuggestFragment;

import java.util.ArrayList;
import java.util.List;

import cn.simgenius.commons.internetutilities.StringGet;

public class MainActivity extends Activity {

    LinearLayout btnA,btnB,btnC,btnD;
    Button btnPersonalCenter,btnSearch;
    Handler handler;
    IndexFragment indexFragment;
    SuggestFragment suggestFragment;
    FriendFragment friendFragment;
    ViewPager contentArea;
    FragmentPagerAdapter pagerAdapter;
    LinearLayout btnCitySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
    void init(){
        initVariable();
        initComponent();
        setListeners();
        setHandler();
        indexFragment = new IndexFragment();
        suggestFragment = new SuggestFragment();
        friendFragment = new FriendFragment();
        final List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(friendFragment);
        fragmentList.add(indexFragment);
        fragmentList.add(suggestFragment);

        pagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        contentArea.setAdapter(pagerAdapter);
        contentArea.setCurrentItem(1);
//        getFragmentManager().beginTransaction().replace(R.id.contentArea, indexFragment).commit();

    }

    //用于初始化变量
    void initVariable(){

    }

    //用于初始化控件
    void initComponent(){
        btnA = (LinearLayout) findViewById(R.id.btnA);
        btnB = (LinearLayout) findViewById(R.id.btnB);
        btnC = (LinearLayout) findViewById(R.id.btnC);
        btnD = (LinearLayout) findViewById(R.id.btnD);
        contentArea = (ViewPager) findViewById(R.id.contentArea);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnPersonalCenter = (Button) findViewById(R.id.btnPersonalCenter);
        btnCitySelect = (LinearLayout) findViewById(R.id.btnCitySelect);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(indexFragment == null){
//                    indexFragment = new IndexFragment();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.contentArea, indexFragment).commit();
                startActivity(new Intent(getApplicationContext(), ChatActivity.class));
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suggestFragment == null){
                    suggestFragment = new SuggestFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.contentArea, suggestFragment).commit();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),SuggestActivity.class));
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DetailHomeActivity.class));
            }
        });



        btnCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CitySelectActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        btnPersonalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PersonalCenterActivity.class);
                startActivity(intent);
            }
        });

//
//        btnC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(fragment == null){
//                    fragment = new IndexFragment();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.contentArea, fragment).commit();
//            }
//        });
//
//        btnD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(fragment == null){
//                    fragment = new IndexFragment();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.contentArea, fragment).commit();
//            }
//        });

        findViewById(R.id.btnIndex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentArea.setCurrentItem(1);
            }
        });
        findViewById(R.id.btnDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentArea.setCurrentItem(2);
            }
        });

        findViewById(R.id.btnFriends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentArea.setCurrentItem(0);
            }
        });

        contentArea.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            LinearLayout selectBar = (LinearLayout) findViewById(R.id.selectBar);
            int eachWidth = Constants.DIVICE_WIDTH_PX/3;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                selectBar.setX(position*eachWidth+eachWidth*positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == -1){
                System.out.println(data.getLongExtra("cityId", -1));
            }
        }
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

    long lastTime = 0;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()-lastTime > 2000){
            lastTime = System.currentTimeMillis();
            Toast.makeText(getBaseContext(),"再按一次退出", Toast.LENGTH_SHORT).show();
        }else {
            stopService(new Intent(getBaseContext(),CoreService.class));
            finish();
        }

    }


}
