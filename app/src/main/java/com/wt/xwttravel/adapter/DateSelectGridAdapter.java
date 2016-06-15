package com.wt.xwttravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.model.DateCube;

import java.util.List;

/**
 * Created by Genius on 2/15/16.
 */
public class DateSelectGridAdapter extends BaseAdapter {

    Context context;
    List<DateCube> list;
    LayoutInflater inflater;

    public DateSelectGridAdapter(Context context, List<DateCube> list) {
        this.context = context;
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
        return list.get(position).getDate().getTime();
    }

    @Override       //整个屏幕直接显示所有，用不到holder
    public View getView(int position, View convertView, ViewGroup parent) {

        if (list.get(position).getText().equals("")){
            return new View(context);
        }
        DateCube dateCube = list.get(position);
        convertView = createButton(dateCube);


        return convertView;
    }




    private Button createButton(DateCube dateCube){
        Button button = new Button(context);

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(Constants.DIVICE_WIDTH_PX/7,Constants.DIVICE_WIDTH_PX/7);
        button.setLayoutParams(params);

        button.setBackgroundResource(R.drawable.btn_white);
        button.setText(dateCube.getText());
        button.setTextSize(14);        //字体大小
        button.setTextColor(dateCube.getColor());


        return button;
    }
}
