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
import com.wt.xwttravel.model.ItemPreview;
import com.wt.xwttravel.util.ImageUtil;

import java.util.List;

/**
 * Created by Genius on 12/9/15.
 */
public class SuggestListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<ItemPreview> list;

    public SuggestListAdapter(Context context, List<ItemPreview> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
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
        ItemPreview item = list.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_preview_item, null);
            holder = new ViewHolder();
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtSubTitle = (TextView) convertView.findViewById(R.id.txtSubTitle);
            holder.txtSold = (TextView) convertView.findViewById(R.id.txtSold);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(item.getTitle());
        holder.txtSubTitle.setText(item.getSubTitle());
        StringBuilder builder = new StringBuilder();
        builder.append(item.getSold());
        builder.append("人已报名");
        holder.txtSold.setText(builder.toString());
        ImageUtil.loadImage(holder.img,item.getPicUrl());
        if (item.getPrice() == -1) {
            holder.txtPrice.setText(" 已结束 ");
        } else {
            builder.setLength(0);
            builder.append("￥");
            int temp = (int) item.getPrice();
            if ((float) temp != item.getPrice()) {
                builder.append(item.getPrice());
            } else {
                builder.append(temp);
            }
            holder.txtPrice.setText(builder.toString());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView txtTitle, txtSubTitle, txtPrice, txtSold;
        SimpleDraweeView img;
    }
}
