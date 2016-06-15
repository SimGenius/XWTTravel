package com.wt.xwttravel.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wt.xwttravel.Constants;
import com.wt.xwttravel.R;
import com.wt.xwttravel.adapter.CategorySelectListAdapter;
import com.wt.xwttravel.model.CategorySelectItem;
import com.wt.xwttravel.util.MessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategorySelectFragment extends MessageFragment {

    boolean animating = false;
    AbsoluteLayout bottomSpace;
    View view;
    ListView listView;
    List<CategorySelectItem> categorySelectItemList;

    public CategorySelectFragment() {
        // Required empty public constructor

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_select, container, false);

        bottomSpace = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        listView = (ListView) view.findViewById(R.id.listView);
        setDefaultData();

        LinearLayout padding = new LinearLayout(getActivity());
        padding.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) (10 * Constants.DIVICE_DENSITY)));
        listView.addHeaderView(padding);
        listView.setAdapter(new CategorySelectListAdapter(getActivity(), categorySelectItemList));
        setListeners();
        openAnim();

        return view;
    }




    void setListeners(){
        bottomSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnim();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("categoryId",(int)id);
                intent.putExtra("position", position);

                sendbackMessage(intent);
                endAnim();
            }
        });
    }


    void openAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        final LinearLayout selectList = (LinearLayout) view.findViewById(R.id.listLayout);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.in_from_top);
        Animation backgroundAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.findViewById(R.id.baseLayout).setVisibility(View.VISIBLE);
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectList.setVisibility(View.VISIBLE);
                selectList.startAnimation(calendarAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        calendarAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        background.startAnimation(backgroundAnimation);

    }

    void endAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        final LinearLayout selectList = (LinearLayout) view.findViewById(R.id.listLayout);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.out_from_top);
        final Animation backgroundAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

        calendarAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectList.setVisibility(View.INVISIBLE);
                background.startAnimation(backgroundAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });



        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animating = true;


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.findViewById(R.id.baseLayout).setVisibility(View.INVISIBLE);
                animating = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        selectList.startAnimation(calendarAnimation);

    }

    void setDefaultData(){
        categorySelectItemList = new ArrayList<CategorySelectItem>();
        categorySelectItemList.add(new CategorySelectItem(0,"name0",10));
        categorySelectItemList.add(new CategorySelectItem(1,"name1",10));
        categorySelectItemList.add(new CategorySelectItem(2,"name2",20));
        categorySelectItemList.add(new CategorySelectItem(3,"name3",30));
        categorySelectItemList.add(new CategorySelectItem(4,"name4",4));
        categorySelectItemList.add(new CategorySelectItem(5,"name5",6));
        categorySelectItemList.add(new CategorySelectItem(6,"name6",0));
        categorySelectItemList.add(new CategorySelectItem(7,"name1",10));
        categorySelectItemList.add(new CategorySelectItem(8,"name2",20));
        categorySelectItemList.add(new CategorySelectItem(9,"name3",30));
        categorySelectItemList.add(new CategorySelectItem(10,"name4",4));
        categorySelectItemList.add(new CategorySelectItem(11,"name5",6));
        categorySelectItemList.add(new CategorySelectItem(12,"name6",0));
    }


    @Override
    public void onMessageArrived(Intent message) {
    }


}
