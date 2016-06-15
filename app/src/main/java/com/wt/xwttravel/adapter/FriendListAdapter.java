package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.R;
import com.wt.xwttravel.model.FriendItem;
import com.wt.xwttravel.util.ImageUtil;
import com.wt.xwttravel.util.TextUtil;

import java.util.List;

/**
 * Created by Genius on 4/12/16.
 */
public class FriendListAdapter extends BaseAdapter {

    List<FriendItem> list;
    LayoutInflater inflater;


    public FriendListAdapter(Context context, List<FriendItem> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        return list.get(position).getUserId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FriendItem item = list.get(position);
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_friend,null);
            holder = new ViewHolder();
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
            holder.txtNickname = (TextView) convertView.findViewById(R.id.txtNickname);
            holder.txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
            holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtNickname.setText(item.getNickname());
        holder.txtMessage.setText(item.getLastMessage());
        holder.txtTime.setText(TextUtil.getDateString(item.getLastTime()));
        ImageUtil.loadImage(holder.img,item.getPicUrl());
        return convertView;
    }

    private static class ViewHolder{
        SimpleDraweeView img;
        TextView txtNickname,txtMessage,txtTime;
    }
}
