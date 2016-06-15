package com.wt.xwttravel.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.model.SuggestItem;
import com.wt.xwttravel.util.ImageUtil;

import java.util.List;

/**
 * Created by Genius on 2/2/16.
 */
public class DetailPagerAdapter extends PagerAdapter {
    private List<String> list;
    private LayoutInflater inflater;

    public DetailPagerAdapter(List<String> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0.equals(arg1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.pager_suggest,null);
        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.img);
//        ImageUtil.loadImage(imageView, Constants.DIVICE_HEIGHT_PX, list.get(position));
        imageView.setImageURI(Uri.parse(list.get(position)));
        container.addView(view);
        return view;
    }

}
