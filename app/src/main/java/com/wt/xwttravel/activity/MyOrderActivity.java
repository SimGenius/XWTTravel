package com.wt.xwttravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.MyOrderListAdapter;
import com.wt.xwttravel.model.MyOrderItem;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends Activity {

    List<MyOrderItem> itemList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
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
        setDefaultData();
    }

    //用于初始化控件
    void initComponent(){
        listView = (ListView) findViewById(R.id.listView);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this,MyOrderDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void setListView(){
        LinearLayout padding = new LinearLayout(MyOrderActivity.this);
        padding.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) (50 * Constants.DIVICE_DENSITY)));
        listView.addHeaderView(padding);
        listView.setAdapter(new MyOrderListAdapter(MyOrderActivity.this,itemList));
    }

    void setDefaultData(){
        itemList = new ArrayList<MyOrderItem>();
        itemList.add(new MyOrderItem(1,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》1","人数:3人 出行日期:2016-12-12",1024,1,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        itemList.add(new MyOrderItem(2,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》2","人数:3人 出行日期:2016-12-13",2048,2,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemList.add(new MyOrderItem(3,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》3","人数:3人 出行日期:2016-12-14",4096,3,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemList.add(new MyOrderItem(4,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》4","人数:3人 出行日期:2016-12-15",8192,4,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        itemList.add(new MyOrderItem(5,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》5","人数:3人 出行日期:2016-12-16",16384,5,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        itemList.add(new MyOrderItem(6,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》6","人数:3人 出行日期:2016-12-17",32768,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));
        itemList.add(new MyOrderItem(7,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》7","人数:3人 出行日期:2016-12-18",65536,2,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        itemList.add(new MyOrderItem(8,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》8","人数:3人 出行日期:2016-12-19",512,1,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemList.add(new MyOrderItem(9,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》9","人数:3人 出行日期:2016-12-20",256,3,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemList.add(new MyOrderItem(10,"云栖社区在线培训《游族网络：如何运维千台以上游戏云服务器》10","人数:3人 出行日期:2016-12-21",128,1,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
    }

}
