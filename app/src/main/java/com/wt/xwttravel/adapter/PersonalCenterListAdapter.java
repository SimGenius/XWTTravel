package com.wt.xwttravel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wt.xwttravel.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Genius on 3/23/16.
 */
public class PersonalCenterListAdapter extends MyBaseAdapter<Map<String,Object>>{

    public PersonalCenterListAdapter(Context context, List<Map<String, Object>> list) {
        super(context, list);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong((String)list.get(position).get("id"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Map<String,Object> item = list.get(position);

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_personal_center,null);
//            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.txt = (TextView) convertView.findViewById(R.id.txt);
            holder.txtRight = (TextView) convertView.findViewById(R.id.txtRight);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.img.setImageBitmap((Bitmap) item.get("img"));
        holder.txt.setText((String) item.get("txt"));
        holder.txtRight.setText((String) item.get("txtRight"));

        return convertView;
    }

    private static class ViewHolder{
//        ImageView img;
        TextView txt,txtRight;
    }
}