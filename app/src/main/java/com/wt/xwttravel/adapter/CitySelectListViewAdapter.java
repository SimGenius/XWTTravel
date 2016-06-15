package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wt.xwttravel.R;
import com.wt.xwttravel.model.City;

import java.util.List;

/**
 * Created by Genius on 10/30/15.
 */
public class CitySelectListViewAdapter extends BaseAdapter{

    LayoutInflater inflater;
    List<City> list;

    public CitySelectListViewAdapter(Context context, List<City> list) {
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
        City city = list.get(position);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_city_select,null);
            holder = new ViewHolder();
            holder.cityId = city.getId();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtCityName);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(city.getName());


        return convertView;
    }


    private static class ViewHolder{
        int cityId;
        TextView txtName;
    }
}
