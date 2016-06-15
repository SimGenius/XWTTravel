package com.wt.xwttravel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.CitySelectListViewAdapter;
import com.wt.xwttravel.model.City;

import java.util.ArrayList;
import java.util.List;

import cn.simgenius.commons.internetutilities.StringGet;

public class CitySelectActivity extends Activity {

    Handler handler;
    ListView listView;
    Button btnCancel;
    List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        init();
//        new StringGet("http://www.baidu.com", new StringGet.OnTaskFinishListener() {
//            @Override
//            public void onTaskFinish(String result) {
//                System.out.println(result);
//            }
//        });
    }
    void init(){
        initVariable();
        initComponent();
        setListeners();
        setHandler();
        setListView();
    }

    //用于初始化变量
    void initVariable(){
        cityList = new ArrayList<City>();

        setDefaultData();
    }

    //用于初始化控件
    void initComponent(){
        listView = (ListView) findViewById(R.id.listView);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("cityId",id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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

    void setListView(){
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.list_item_city_select_header,null));
        listView.addFooterView(getLayoutInflater().inflate(R.layout.list_item_city_select_footer, null));
        listView.setAdapter(new CitySelectListViewAdapter(CitySelectActivity.this,cityList));
    }

    void setDefaultData(){
        cityList.add(new City(1,"北京"));
        cityList.add(new City(2,"上海"));
        cityList.add(new City(3,"天津"));
        cityList.add(new City(4,"重庆"));
        cityList.add(new City(5,"广州"));
        cityList.add(new City(6,"哈尔滨"));
        cityList.add(new City(7,"长春"));
        cityList.add(new City(8,"沈阳"));
        cityList.add(new City(9,"呼和浩特"));
        cityList.add(new City(10,"石家庄"));
        cityList.add(new City(5,"乌鲁木齐"));
        cityList.add(new City(6,"兰州"));
        cityList.add(new City(1,"西宁"));
        cityList.add(new City(2,"西安"));
        cityList.add(new City(3,"银川"));
        cityList.add(new City(4,"郑州"));
        cityList.add(new City(5,"济南"));
        cityList.add(new City(6,"太原"));
        cityList.add(new City(1,"合肥"));
        cityList.add(new City(2,"武汉"));
        cityList.add(new City(3,"长沙"));
        cityList.add(new City(4,"南京"));
        cityList.add(new City(5,"成都"));
        cityList.add(new City(6,"贵阳"));
        cityList.add(new City(1,"昆明"));
        cityList.add(new City(2,"南宁"));
        cityList.add(new City(3,"拉萨"));
        cityList.add(new City(4,"杭州"));
        cityList.add(new City(5,"南昌"));
        cityList.add(new City(6,"福州"));
        cityList.add(new City(2,"海口"));

    }

}
