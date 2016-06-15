package com.wt.xwttravel.adapter;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.model.Message;
import com.wt.xwttravel.util.CircleImageView;
import com.wt.xwttravel.util.ImageUtil;

import java.util.List;

/**
 * Created by Genius on 3/26/16.
 */
public class ChatListAdapter extends BaseAdapter {

    List<Message> list;
    LayoutInflater inflater;

    public ChatListAdapter(List<Message> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message item = list.get(position);
        if(item.getUserId() == Constants.USER_ID) {
            convertView = inflater.inflate(R.layout.list_item_chat_my, null);
            SimpleDraweeView photo = (SimpleDraweeView) convertView.findViewById(R.id.photo);
            ImageUtil.loadImage(photo,"http://dl.simgenius.cn/xwt/img/userphoto/IMG_0598.jpg");
        }else {
            convertView = inflater.inflate(R.layout.list_item_chat_other, null);
            SimpleDraweeView photo = (SimpleDraweeView) convertView.findViewById(R.id.photo);
            ImageUtil.loadImage(photo,"http://dl.simgenius.cn/xwt/img/userphoto/C58E47680D1187237B9E655F5EF30840.jpg");
        }
//        SimpleDraweeView photo = (SimpleDraweeView) convertView.findViewById(R.id.photo);
//        ImageUtil.loadImage(photo,"http://dl.simgenius.cn/xwt/img/userphoto/IMG_0598.jpg");
        TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
        txtMessage.setAutoLinkMask(Linkify.ALL);
        txtMessage.setMovementMethod(LinkMovementMethod.getInstance());
        txtMessage.setText(item.getContent());
        return convertView;
    }


}
