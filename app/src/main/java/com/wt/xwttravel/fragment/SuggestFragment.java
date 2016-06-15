package com.wt.xwttravel.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wt.xwttravel.R;
import com.wt.xwttravel.activity.CitySelectActivity;
import com.wt.xwttravel.activity.DateSelectActivity;
import com.wt.xwttravel.activity.DetailHomeActivity;
import com.wt.xwttravel.activity.PersonalCenterActivity;
import com.wt.xwttravel.adapter.IndexListViewAdapter;
import com.wt.xwttravel.adapter.SuggestListAdapter;
import com.wt.xwttravel.model.CategorySelectItem;
import com.wt.xwttravel.model.ItemPreview;
import com.wt.xwttravel.util.MessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestFragment extends Fragment {
    RelativeLayout baseLayout;
    ListView listView;
    List<ItemPreview> itemPreviewList;
    Button btnCategory,btnDate,btnSort;

    CategorySelectFragment categorySelectFragment;

    public static int DATE_SELECT = 1001;


    public SuggestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggest, container, false);
        baseLayout = (RelativeLayout) view.findViewById(R.id.baseLayout);
        initComponent(view);
        init();

        return view;
    }


    void init(){
        initVariable();
        setListeners();
        setDefaultData();
        setListView();

    }

    //用于初始化变量
    void initVariable(){
        itemPreviewList = new ArrayList<ItemPreview>();
        categorySelectFragment = new CategorySelectFragment();
    }

    //用于初始化控件
    void initComponent(View view){
        listView = (ListView) view.findViewById(R.id.listView);
        btnDate = (Button) view.findViewById(R.id.btnDate);
        btnSort = (Button) view.findViewById(R.id.btnSort);
        btnCategory = (Button) view.findViewById(R.id.btnCategory);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategorySelectFragment fragment = new CategorySelectFragment();
                fragment.setCallbackListener(new MessageFragment.OnMessageCallbackListener() {
                    @Override
                    public void onMessageCallback(Intent message) {
                        System.out.println(message.getIntExtra("categoryId", -1));
                    }
                });

                getFragmentManager().beginTransaction().replace(R.id.addArea, fragment).commit();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DateSelectActivity.class);
//                startActivityForResult(intent, DATE_SELECT);
//                LinearLayout linearLayout = new LinearLayout(getActivity());
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//                params.addRule(RelativeLayout.BELOW, baseLayout.findViewById(R.id.optionBar).getId());
//                baseLayout.addView(linearLayout, params);
//                baseLayout.removeView();
                getFragmentManager().beginTransaction().replace(R.id.addArea, new DateSelectFragment()).commit();

            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.addArea, new SortbySelectFragment()).commit();
            }
        });

        categorySelectFragment.setCallbackListener(new MessageFragment.OnMessageCallbackListener() {
            @Override
            public void onMessageCallback(Intent message) {
                System.out.println(message.getIntExtra("categoryId", -1));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), DetailHomeActivity.class));
            }
        });


    }



    void setListView(){
        listView.setAdapter(new SuggestListAdapter(getActivity(), itemPreviewList));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1001:
                if(resultCode == Activity.RESULT_OK){
                    System.out.println("date selected");
                }
                break;
        }
    }

    public void setDefaultData(){
        itemPreviewList.add(new ItemPreview(1,"北京出发 舒适超值纯玩5天4晚 A线","赠送清华大学拍照 天安门集体照（以家庭为单位）等",128,200,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        itemPreviewList.add(new ItemPreview(2,"大同出发----悬空寺、云冈石窟一日游","大同周边旅游专家，24小时贴心服务，超优品质  【赠送旅游意外险】",256,300,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemPreviewList.add(new ItemPreview(3,"北戴河看大海+山海关+秦皇求仙+老龙头外景火车双座3日游","逛历史名城山海关游秦皇求仙，享北戴河浪漫海滨风情",398,400,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemPreviewList.add(new ItemPreview(4,"山东青岛3天-往返高铁+挂三酒店+酒店双份早餐","本产品为3天价格，根据需求可自由加钱升级4天",1099,100,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        itemPreviewList.add(new ItemPreview(5,"四川成都-九寨沟黄龙双飞5日游","0自费0购物(进店推自费赔付1000元RMB）",2204,50,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        itemPreviewList.add(new ItemPreview(6,"仙境家园|4日机票+2晚桂林喜来登饭店+1晚阳朔万丽花园大酒店+自助早餐","五星酒店尊享，往返机票，赠送当地旅游管家。",4096,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));
        itemPreviewList.add(new ItemPreview(7,"北京出发 舒适超值纯玩5天4晚 A线","赠送清华大学拍照 天安门集体照（以家庭为单位）等",128,200,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        itemPreviewList.add(new ItemPreview(8,"大同出发----悬空寺、云冈石窟一日游","大同周边旅游专家，24小时贴心服务，超优品质  【赠送旅游意外险】",256,300,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemPreviewList.add(new ItemPreview(9,"北戴河看大海+山海关+秦皇求仙+老龙头外景火车双座3日游","逛历史名城山海关游秦皇求仙，享北戴河浪漫海滨风情",398,400,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemPreviewList.add(new ItemPreview(10,"山东青岛3天-往返高铁+挂三酒店+酒店双份早餐","本产品为3天价格，根据需求可自由加钱升级4天",1099,100,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        itemPreviewList.add(new ItemPreview(11,"四川成都-九寨沟黄龙双飞5日游","0自费0购物(进店推自费赔付1000元RMB）",2204,50,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        itemPreviewList.add(new ItemPreview(12,"仙境家园|4日机票+2晚桂林喜来登饭店+1晚阳朔万丽花园大酒店+自助早餐","五星酒店尊享，往返机票，赠送当地旅游管家。",4096,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));
        itemPreviewList.add(new ItemPreview(13,"北京出发 舒适超值纯玩5天4晚 A线","赠送清华大学拍照 天安门集体照（以家庭为单位）等",128,200,"http://imgs.qunarzz.com/p/p1/1504/b7/16f11cc671b513.jpg_304x201_2cdb4491.jpg"));
        itemPreviewList.add(new ItemPreview(14,"大同出发----悬空寺、云冈石窟一日游","大同周边旅游专家，24小时贴心服务，超优品质  【赠送旅游意外险】",256,300,"http://imgs.qunarzz.com/p/p41/1508/1d/16548e5be02845.jpg_304x201_37805aeb.jpg"));
        itemPreviewList.add(new ItemPreview(15,"北戴河看大海+山海关+秦皇求仙+老龙头外景火车双座3日游","逛历史名城山海关游秦皇求仙，享北戴河浪漫海滨风情",398,400,"http://img1.qunarzz.com/p/tts5/1505/f3/4d3da0cbb0b3f3.jpg_r_390x260x90_08918b4e.jpg"));
        itemPreviewList.add(new ItemPreview(16,"山东青岛3天-往返高铁+挂三酒店+酒店双份早餐","本产品为3天价格，根据需求可自由加钱升级4天",1099,100,"http://img1.qunarzz.com/p/tts4/1506/37/b2fcebdedfb8e3.jpg_r_390x260x90_30154d70.jpg"));
        itemPreviewList.add(new ItemPreview(17,"四川成都-九寨沟黄龙双飞5日游","0自费0购物(进店推自费赔付1000元RMB）",2204,50,"http://img1.qunarzz.com/p/tts6/1601/66/8f2cdec08f25e3f7.jpg_r_390x260x90_7cfd219f.jpg"));
        itemPreviewList.add(new ItemPreview(18,"仙境家园|4日机票+2晚桂林喜来登饭店+1晚阳朔万丽花园大酒店+自助早餐","五星酒店尊享，往返机票，赠送当地旅游管家。",4096,2,"http://img1.qunarzz.com/p/tts9/201404/22/e6ccfad0eb007e4ec8d65eac.jpg_r_390x260x90_54a82807.jpg"));

    }






}
