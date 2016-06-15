package com.wt.xwttravel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.DetailHomeListAdapter;
import com.wt.xwttravel.adapter.DetailPagerAdapter;
import com.wt.xwttravel.model.CommentItem;
import com.wt.xwttravel.model.DetailItem;
import com.wt.xwttravel.util.TextUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailHomeActivity extends Activity {



    public static final int BUY_BUTTON_DELAYED = 1001;

    ListView listView;
    ViewPager viewPager;
    DetailItem detailItem;
    View header;
    List<CommentItem> commentItemList;
    Button btnDetail,btnComment,btnBuy,btnLocation;
    List<ImageView> dotList;
    LinearLayout dotArea;
    FrameLayout navigationbar;
    TextView txtTitle,txtDate,txtSold,txtImportant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);
        init();
    }


    void init(){
        initVariable();
        initComponent();
        setListeners();
        setHandler();
        setViewPager();
        setListView();
        setOtherInfo();
    }

    //用于初始化变量
    void initVariable(){
        commentItemList = new ArrayList<CommentItem>();
        setDefaultData();
    }

    //用于初始化控件
    void initComponent(){

        header = getLayoutInflater().inflate(R.layout.list_item_detail_header,null);
        dotArea = (LinearLayout) header.findViewById(R.id.dots);
        viewPager = (ViewPager) header.findViewById(R.id.viewPager);
        listView = (ListView) findViewById(R.id.listView);
        listView.addHeaderView(header);
        btnDetail = (Button) header.findViewById(R.id.btnDetail);
        btnComment = (Button) getLayoutInflater().inflate(R.layout.list_item_detail_footer, null);
        listView.addFooterView(btnComment);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        txtDate = (TextView) header.findViewById(R.id.txtDate);
        txtImportant = (TextView) header.findViewById(R.id.txtImportant);
        txtSold = (TextView) header.findViewById(R.id.txtSold);
        txtTitle = (TextView) header.findViewById(R.id.txtTitle);
        btnLocation = (Button) header.findViewById(R.id.btnLocation);


        navigationbar = (FrameLayout) getLayoutInflater().inflate(R.layout.element_navigationbar, null);
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.baseLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) (50*Constants.DIVICE_DENSITY));
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        baseLayout.addView(navigationbar,params);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        btnBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(DetailHomeActivity.this,PayConfirmActivity.class));

            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailHomeActivity.this,DetailMoreActivity.class);
                startActivity(intent);
            }
        });

        navigationbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailHomeActivity.this,MapActivity.class);
                intent.putExtra("latitude",39.750346);
                intent.putExtra("longitude",116.33571);
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

    Handler handler;
    void setHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case BUY_BUTTON_DELAYED:
                        btnBuy.setText(TextUtil.getPriceText(detailItem.getPrice()));
                        break;
                }
            }
        };


    }

    void setViewPager(){


        int size = detailItem.getPicUrl().size();
        dotList = new ArrayList<ImageView>();
        int widthSum = 0;
//        dotList.add((ImageView) findViewById(R.id.dot1));
        float density = Constants.DIVICE_DENSITY;
            for(int i = 0; i < size; ++i){
//                ImageView imageView2 = (ImageView) getLayoutInflater().inflate(R.layout.element_dot_normal,null);
                ImageView imageView = new ImageView(DetailHomeActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(density*10f),(int)(density*10f));
                if(i != 0)
                    imageView.setX(Constants.DIVICE_DENSITY*(float)i*(4f));
//                params.setMargins((int)(density*2),(int)(density*2),(int)(density*2),(int)(density*2));
                params.width = (int) (Constants.DIVICE_DENSITY*10);
                params.height = (int) (Constants.DIVICE_DENSITY*10);
                widthSum += params.width;
                imageView.setLayoutParams(params);
                if(i == 0){
                    imageView.setImageResource(R.drawable.dot_white_selected);
                }else {
                    imageView.setImageResource(R.drawable.dot_white_normal);
                }
                dotList.add(imageView);
                dotArea.addView(imageView);
            }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) (size*(density*12f)-density*2f),(int)(density*14f));
//        params.width = (int) (size*(density*12f)-density*2f);
//        dotArea.setLayoutParams(params);

//        for (int i = 0; i < size; i++){
//            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.element_dot_normal,null);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(density*10f),(int)(density*10f));
//
//            params.setMargins((int)(density*2),(int)(density*2),(int)(density*2),(int)(density*2));
//            imageView.setLayoutParams(params);
//            dotList.add(imageView);
//            dotArea.addView(imageView);
//        }
//

        viewPager.setAdapter(new DetailPagerAdapter(detailItem.getPicUrl(),DetailHomeActivity.this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int last = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotList.get(position).setImageResource(R.drawable.dot_white_selected);
                dotList.get(last).setImageResource(R.drawable.dot_white_normal);
                last = position;
//                for(int i = 0; i < dotList.size(); ++i){
//                    ImageView imageView = dotList.get(position);
////                    if(position == i){
//                        imageView.setImageResource(R.drawable.dot_white_selected);
////                    }else {
////                        imageView.setImageResource(R.drawable.dot_white_normal);
////                    }
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        listView.setAdapter(new DetailHomeListAdapter(commentItemList,DetailHomeActivity.this));

    }

    void setOtherInfo(){
        txtTitle.setText(detailItem.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");

        int duration = (int) (1+(detailItem.getToDate().getTime() - detailItem.getFromDate().getTime())/3600*24*1000);
        StringBuilder builder = new StringBuilder();
        builder.append(simpleDateFormat.format(detailItem.getFromDate()));
        builder.append(" - ");
        builder.append(simpleDateFormat.format(detailItem.getToDate()));
        builder.append(" 共");
        builder.append(duration);
        builder.append("天");
        txtDate.setText(builder.toString());
        builder.delete(0,builder.length());
        builder.append(detailItem.getSold());
        builder.append("人已参加");
        txtSold.setText(builder.toString());
        txtImportant.setText(detailItem.getImportant());
        btnBuy.setText(TextUtil.getPriceText(detailItem.getPrice())+" 购买");
    }

    void setDefaultData(){
        List<String> strings = new ArrayList<String>();
        strings.add("http://img1.qunarzz.com/p/tts4/1603/1f/1dee00d909abe9f7.jpg_r_390x260x90_245350bb.jpg");
        strings.add("http://img1.qunarzz.com/p/tts5/1603/3e/30eed30a31683af7.jpg_r_390x260x90_74cb6bb9.jpg");
        strings.add("http://img1.qunarzz.com/p/tts1/1603/d2/316046ede2057af7.jpg_r_390x260x90_6da144d8.jpg");
        detailItem = new DetailItem(1,"【自由行】【密云古北水镇-情侣双人价】入住水镇大酒店豪华房／特色客栈大床房＋双人早餐", "浪漫晚餐＋水镇门票两张＋长城门票2张＋ 调酒体验", "【住宿】\n" +
                "水镇酒店豪华房（价值2580元）/特色客栈温馨家庭房（价值1580元）【二选一】一间一晚\n" +
                "【用餐】\n" +
                "1、酒店含早餐2份/房/天\n" +
                "2、价值299元 望京楼浪漫晚餐\n" +
                "【门票】\n" +
                "价值160元 古北水镇门票2张 价值80元 长城门票2张\n" +
                "【赠送】\n" +
                "价值98元 双人特色调酒体验 价值10元 月老祠 甜蜜祈福", new Date(), new Date(), 100, 1139f, strings);
        commentItemList.add(new CommentItem(1,1,"nick","",new Date(),"【饿了么】亲爱的会员用户，感谢您对饿了么的支持，春节将至，饿骑士们也要和家人团聚了，饿配送将在2月6日至2月15日停止运营，为了保障亲们的权益，特将您的会员卡有效期延长10天，有效期至2016年3月22日，来年饿了么继续和您一起拼！"));
        commentItemList.add(new CommentItem(1,1,"nick","",new Date(),"【饿了么】亲爱的会员用户，感谢您对饿了么的支持，春节将至，饿骑士们也要和家人团聚了，饿配送将在2月6日至2月15日停止运营，为了保障亲们的权益，特将您的会员卡有效期延长10天，有效期至2016年3月22日，来年饿了么继续和您一起拼！"));
        commentItemList.add(new CommentItem(1,1,"nick","",new Date(),"【饿了么】亲爱的会员用户，感谢您对饿了么的支持，春节将至，饿骑士们也要和家人团聚了，饿配送将在2月6日至2月15日停止运营，为了保障亲们的权益，特将您的会员卡有效期延长10天，有效期至2016年3月22日，来年饿了么继续和您一起拼！"));
        commentItemList.add(new CommentItem(1,1,"nick","",new Date(),"【饿了么】亲爱的会员用户，感谢您对饿了么的支持，春节将至，饿骑士们也要和家人团聚了，饿配送将在2月6日至2月15日停止运营，为了保障亲们的权益，特将您的会员卡有效期延长10天，有效期至2016年3月22日，来年饿了么继续和您一起拼！"));
        commentItemList.add(new CommentItem(1,1,"nick","",new Date(),"【饿了么】亲爱的会员用户，感谢您对饿了么的支持，春节将至，饿骑士们也要和家人团聚了，饿配送将在2月6日至2月15日停止运营，为了保障亲们的权益，特将您的会员卡有效期延长10天，有效期至2016年3月22日，来年饿了么继续和您一起拼！"));


        
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
