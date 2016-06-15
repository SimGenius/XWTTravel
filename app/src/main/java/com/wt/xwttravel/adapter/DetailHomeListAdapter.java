package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wt.xwttravel.R;
import com.wt.xwttravel.model.CommentItem;
import com.wt.xwttravel.util.CircleImageView;
import com.wt.xwttravel.util.ImageUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Genius on 2/2/16.
 */
public class DetailHomeListAdapter extends BaseAdapter {

    List<CommentItem> list;
    LayoutInflater inflater;
    SimpleDateFormat simpleDateFormat;

    public DetailHomeListAdapter(List<CommentItem> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
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
        ViewHolder holder = null;
        CommentItem item = list.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_detail,null);
            holder = new ViewHolder();
            holder.photo = (CircleImageView) convertView.findViewById(R.id.photo);
            holder.txtComment = (TextView) convertView.findViewById(R.id.txtComment);
            holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            holder.txtNickname = (TextView) convertView.findViewById(R.id.txtNickname);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtComment.setText(item.getComment());
        holder.txtNickname.setText(item.getNickName());
        holder.txtDate.setText(simpleDateFormat.format(item.getSubmitTime()));
//        ImageUtil.loadImage(holder.photo,item.getPicUrl());
        holder.photo.setImageResource(R.drawable.simgenius);

        return convertView;
    }

    private static class ViewHolder{
        CircleImageView photo;
        TextView txtNickname;
        TextView txtDate;
        TextView txtComment;
    }
}

