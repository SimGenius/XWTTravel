package com.wt.xwttravel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.PersonalCenterListAdapter;
import com.wt.xwttravel.util.CircleImageView;
import com.wt.xwttravel.util.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalCenterActivity extends Activity {


    ListView listView;
    List<Map<String,Object>> mapList;
    FrameLayout topArea;
    CircleImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        init();
    }

    void init(){
        initVariable();
        initComponent();
        setListeners();
        setListView();
    }

    //用于初始化变量
    void initVariable(){
        mapList = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id","1");
        map.put("txt","我的账号");
        map.put("txtRight","未绑定手机");
        map.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("id","2");
        map2.put("txt","我的订单");
        map2.put("txtRight","2个未出行");
        map2.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map3 = new HashMap<String, Object>();
        map3.put("id","3");
        map3.put("txt","我的收藏");
        map3.put("txtRight","13");
        map3.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map4 = new HashMap<String, Object>();
        map4.put("id","4");
        map4.put("txt","我的伙伴");
        map4.put("txtRight","4");
        map4.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map5 = new HashMap<String, Object>();
        map5.put("id","5");
        map5.put("txt","关于");
        map5.put("txtRight","目前是最新版本");
        map5.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map6 = new HashMap<String, Object>();
        map6.put("id","6");
        map6.put("txt","我的账号");
        map6.put("txtRight","未绑定手机");
        map6.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map7 = new HashMap<String, Object>();
        map7.put("id","7");
        map7.put("txt","我的账号");
        map7.put("txtRight","未绑定手机");
        map7.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        Map<String,Object> map8 = new HashMap<String, Object>();
        map8.put("id","8");
        map8.put("txt","我的账号");
        map8.put("txtRight","未绑定手机");
        map8.put("img", BitmapFactory.decodeResource(getResources(),R.drawable.default_pic));

        mapList.add(map);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
//        mapList.add(map6);
//        mapList.add(map7);
//        mapList.add(map8);

    }

    //用于初始化控件
    void initComponent(){
        listView = (ListView) findViewById(R.id.listView);
        topArea = (FrameLayout) findViewById(R.id.topArea);
        imgPhoto = (CircleImageView) findViewById(R.id.imgPhoto);

        imgPhoto.setImageResource(R.drawable.simgenius2);
        try {
            topArea.setBackgroundColor(ImageUtil.getImageAverageColor(PersonalCenterActivity.this,R.drawable.simgenius2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id){
                    case 1:
                        startActivity(new Intent(PersonalCenterActivity.this,LoginActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(PersonalCenterActivity.this,MyOrderActivity.class));
                        break;
                }
            }
        });
    }

    void setListView(){
        listView.setAdapter(new PersonalCenterListAdapter(PersonalCenterActivity.this,mapList));
    }


}
