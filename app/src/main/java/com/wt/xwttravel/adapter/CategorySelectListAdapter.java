package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wt.xwttravel.R;
import com.wt.xwttravel.model.CategorySelectItem;

import java.util.List;

/**
 * Created by Genius on 2/23/16.
 */
public class CategorySelectListAdapter extends BaseAdapter {

    List<CategorySelectItem> list;
    LayoutInflater inflater;

    public CategorySelectListAdapter(Context context, List<CategorySelectItem> list) {
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
        CategorySelectItem item = list.get(position);
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_category_select,null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtCount = (TextView) convertView.findViewById(R.id.txtCount);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(item.getName());
        if(item.getCount() == 0){
            holder.txtCount.setVisibility(View.INVISIBLE);
        }else {
            holder.txtCount.setText(Integer.toString(item.getCount()));
            holder.txtCount.setVisibility(View.VISIBLE);
        }
        if(position == list.size()-1){
            convertView.findViewById(R.id.divider).setVisibility(View.INVISIBLE);
        }else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    private static class ViewHolder{
        TextView txtName;
        TextView txtCount;
    }
}
