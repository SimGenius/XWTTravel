package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.R;
import com.wt.xwttravel.model.MyOrderItem;
import com.wt.xwttravel.util.ImageUtil;
import com.wt.xwttravel.util.TextUtil;

import java.util.List;

/**
 * Created by Genius on 3/8/16.
 */
public class MyOrderListAdapter extends BaseAdapter {

    List<MyOrderItem> list;
    LayoutInflater inflater;

    public MyOrderListAdapter(Context context, List<MyOrderItem> list) {
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
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyOrderItem item = list.get(position);
        ViewHolder holder = null;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_my_order2,null);
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
            holder.txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
            holder.txtOrderStatus = (TextView) convertView.findViewById(R.id.txtOrderStatus);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageUtil.loadImage(holder.img,item.getPicUrl());
        holder.txtTitle.setText(item.getTitle());
        holder.txtPrice.setText(TextUtil.getPriceText(item.getPrice()));
        holder.txtOrderStatus.setText(TextUtil.getOrderStatusText(item.getStatus()));
        holder.txtInfo.setText(item.getInfo());

        return convertView;
    }


    private static class ViewHolder {
        SimpleDraweeView img;
        TextView txtTitle, txtOrderStatus, txtPrice, txtInfo;
    }
}
