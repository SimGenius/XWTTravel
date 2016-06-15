package com.wt.xwttravel.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.R;
import com.wt.xwttravel.activity.CitySelectActivity;
import com.wt.xwttravel.activity.PersonalCenterActivity;
import com.wt.xwttravel.activity.SuggestActivity;
import com.wt.xwttravel.adapter.IndexListViewAdapter;
import com.wt.xwttravel.model.ItemPreview;
import com.wt.xwttravel.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {

    LayoutInflater inflater;

    ListView listView;
    LinearLayout btnCitySelect;
    Handler handler;
    List<ItemPreview> itemPreviewList;
    ImageButton btnPersonalCenter;

    SimpleDraweeView adA,adB,adC,imgSuggest;
    TextView txtAA,txtAB,txtBA,txtBB,txtCA,txtCB;


    LinearLayout headerView;

    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        initComponent(view, inflater);
        init();
        return view;
    }


    void init(){
        initVariable();

        setListeners();
        setHandler();
        setDefaultData();
        setListView();
    }

    //用于初始化变量
    void initVariable(){
    itemPreviewList = new ArrayList<ItemPreview>();
    }

    //用于初始化控件
    void initComponent(View view, LayoutInflater inflater){
        listView = (ListView) view.findViewById(R.id.listView);
        headerView = (LinearLayout) inflater.inflate(R.layout.list_item_index_header, null);
        adA = (SimpleDraweeView) headerView.findViewById(R.id.adA);
        adB = (SimpleDraweeView) headerView.findViewById(R.id.adB);
        adC = (SimpleDraweeView) headerView.findViewById(R.id.adC);
        imgSuggest = (SimpleDraweeView) headerView.findViewById(R.id.imgSuggest);
        txtAA = (TextView) headerView.findViewById(R.id.txtAA);
        txtBA = (TextView) headerView.findViewById(R.id.txtBA);
        txtCA = (TextView) headerView.findViewById(R.id.txtCA);
        txtAB = (TextView) headerView.findViewById(R.id.txtAB);
        txtBB = (TextView) headerView.findViewById(R.id.txtBB);
        txtCB = (TextView) headerView.findViewById(R.id.txtCB);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){


        imgSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SuggestActivity.class));
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

        listView.addHeaderView(headerView);
        listView.setAdapter(new IndexListViewAdapter(getActivity(), itemPreviewList));

    }



    public void setDefaultData(){
        ImageUtil.loadImage(adA, "http://dimg04.c-ctrip.com/images/vacations/images2/1/1/1_4747_s29867_C_500_280.jpg");
        txtAA.setText("颐和园");
        txtAB.setText("特别活动正在招募");
        ImageUtil.loadImage(adB, "http://dimg04.c-ctrip.com/images/t1/vacations/210000/209140/c1c7a099333f4df8a377d333481e609d_C_500_280.jpg");
        txtBA.setText("八达岭长城");
        txtBB.setText("万里长城的精华");
        ImageUtil.loadImage(adC, "http://dimg04.c-ctrip.com/images/vacations/images2/1/1/1_6257_b03547_C_500_280.jpg");
        txtCA.setText("天文馆");
        txtCB.setText("探索宇宙的奥秘");
        ImageUtil.loadImage(imgSuggest,"http://dimg04.c-ctrip.com/images/vacations/images2/1/1/1_6834_b03521_C_500_280.jpg");

        itemPreviewList.add(new ItemPreview(1,"北京出发 舒适超值纯玩5天4晚 A线","赠送清华大学拍照 天安门集体照（以家庭为单位）等",128,200,"http://h.hiphotos.baidu.com/baike/pic/item/d000baa1cd11728b889f4c21cafcc3cec3fd2c07.jpg"));
        itemPreviewList.add(new ItemPreview(2,"悬空寺、云冈石窟一日游","大同周边旅游专家，24小时贴心服务，超优品质  【赠送旅游意外险】",256,300,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemPreviewList.add(new ItemPreview(3,"北戴河看大海+山海关+秦皇求仙+老龙头外景火车双座3日游","逛历史名城山海关游秦皇求仙，享北戴河浪漫海滨风情",398,400,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemPreviewList.add(new ItemPreview(4,"山东青岛3天-往返高铁+挂三酒店+酒店双份早餐","本产品为3天价格，根据需求可自由加钱升级4天",1099,100,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        itemPreviewList.add(new ItemPreview(5,"四川成都-九寨沟黄龙双飞5日游","0自费0购物(进店推自费赔付1000元RMB）",2204,50,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        itemPreviewList.add(new ItemPreview(6,"仙境家园|4日机票+2晚桂林喜来登饭店+1晚阳朔万丽花园大酒店+自助早餐","五星酒店尊享，往返机票，赠送当地旅游管家。",4096,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));
    }






}
