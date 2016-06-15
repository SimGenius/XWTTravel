package com.wt.xwttravel.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wt.xwttravel.R;
import com.wt.xwttravel.activity.ChatActivity;
import com.wt.xwttravel.activity.DetailHomeActivity;
import com.wt.xwttravel.adapter.FriendListAdapter;
import com.wt.xwttravel.model.FriendItem;
import com.wt.xwttravel.model.ItemPreview;
import com.wt.xwttravel.util.MessageFragment;

import java.util.ArrayList;
import java.util.List;


public class FriendFragment extends Fragment {

    RelativeLayout baseLayout;
    List<FriendItem> friendItemList;
    ListView listView;

    public FriendFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        baseLayout = (RelativeLayout) view.findViewById(R.id.baseLayout);
        initComponent(view);
        init();
        setListView();
        return view;
    }


    void init(){
        initVariable();
        setListeners();

    }

    //用于初始化变量
    void initVariable(){

    }

    //用于初始化控件
    void initComponent(View view){
        listView = (ListView) view.findViewById(R.id.listView);
    }

    //设定监听（监听按组件名字母顺序排序）
    void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("targetUserId",id);
                startActivity(intent);
            }
        });

    }

    void setDefaultData(){
        friendItemList = new ArrayList<FriendItem>();
        friendItemList.add(new FriendItem(1,"SimGenius","我觉得",System.currentTimeMillis(),"http://dl.simgenius.cn/xwt/img/userphoto/IMG_0598.jpg"));
        friendItemList.add(new FriendItem(2,"!","票买好了,收拾东西后天出发",System.currentTimeMillis(),"http://dl.simgenius.cn/xwt/img/userphoto/C58E47680D1187237B9E655F5EF30840.jpg"));
        friendItemList.add(new FriendItem(3,"DaisySu","我上个月刚带着孩子去过,感觉还不错",System.currentTimeMillis(),"http://dl.simgenius.cn/xwt/img/userphoto/IMG_9978.jpg"));
        friendItemList.add(new FriendItem(4,"LUCY_ZF","message",System.currentTimeMillis(),"http://dl.simgenius.cn/xwt/img/userphoto/IMG_0443.jpg"));
    }

    void setListView(){
        setDefaultData();
        listView.setAdapter(new FriendListAdapter(getActivity(),friendItemList));
    }
}
