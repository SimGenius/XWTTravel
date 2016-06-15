package com.wt.xwttravel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.SuggestPagerAdapter;
import com.wt.xwttravel.model.DetailItem;
import com.wt.xwttravel.model.ItemPreview;
import com.wt.xwttravel.model.SuggestItem;

import java.util.ArrayList;
import java.util.List;

public class SuggestActivity extends Activity {

    List<View> viewList;
    ViewPager viewPager;
    List<SuggestItem> dataList;
    TextView txtTitle,txtSubtitle,txtPrice,txtSold;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        init();
    }

    void init(){
        initVariable();
        initComponent();
        setListeners();
        setHandler();

        setDefaultData();
        setViewPager();
//        ImageView imageView = (ImageView) findViewById(R.id.imgBackground);
//        ImageUtil.loadImage(imageView,"http://172.20.10.8/1280x1280.png");


    }

    //用于初始化变量
    void initVariable(){
        viewList = new ArrayList<View>();
        dataList = new ArrayList<SuggestItem>();

    }

    //用于初始化控件
    void initComponent(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtSubtitle = (TextView) findViewById(R.id.txtSubTitle);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtSold = (TextView) findViewById(R.id.txtSold);
        btnBack = (Button) findViewById(R.id.btnBack);


    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    Handler handler;
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

    void setOnePage(){
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0; i < dataList.size(); ++i){
            viewList.add(inflater.inflate(R.layout.pager_suggest,null));
        }
    }

    void setViewPager(){
        setOnePage();
        viewPager.setAdapter(new SuggestPagerAdapter(dataList,SuggestActivity.this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                ImageView imageView = (ImageView) viewList.get(position).findViewById(R.id.img);
                SuggestItem item = dataList.get(position);
                txtTitle.setText(item.getTitle());
                txtSubtitle.setText(item.getSubtitle());
                txtPrice.setText("￥"+Float.toString(item.getPrice()).replace(".0",""));


                txtSold.setText(Integer.toString(item.getSold())+"人已参加");

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    void setDefaultData(){
        dataList.add(new SuggestItem(1,"北京出发 5天4晚 A线","赠送清华大学拍照",128,200,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        dataList.add(new SuggestItem(2,"大同一日游","大同周边旅游专家",256,300,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        dataList.add(new SuggestItem(3,"北戴河火车双座3日游","逛历史名城山海关游秦皇求仙，享北戴河浪漫海滨风情",398,400,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        dataList.add(new SuggestItem(4,"山东青岛3天-往返高铁+挂三酒店+酒店双份早餐","本产品为3天价格，根据需求可自由加钱升级4天",1099,100,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        dataList.add(new SuggestItem(5,"四川成都-九寨沟黄龙双飞5日游","0自费0购物(进店推自费赔付1000元RMB）",2204,50,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        dataList.add(new SuggestItem(6,"仙境家园|4日机票+2晚桂林喜来登饭店+1晚阳朔万丽花园大酒店+自助早餐","五星酒店尊享，往返机票，赠送当地旅游管家。",4096,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));


        txtTitle.setText(dataList.get(0).getTitle());
        txtSubtitle.setText(dataList.get(0).getSubtitle());
        txtPrice.setText("￥" + Float.toString(dataList.get(0).getPrice()).replace(".0", ""));


        txtSold.setText(Integer.toString(dataList.get(0).getSold())+"人已参加");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
