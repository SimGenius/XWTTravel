package com.wt.xwttravel.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wt.xwttravel.R;
import com.wt.xwttravel.util.MessageFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortbySelectFragment extends MessageFragment {

    View view;

    Button btnSuggest,btnNear,btnPriceLow,btnPriceHigh,btnCheap;
    AbsoluteLayout bottomSpace;


    boolean animating = false;

    public SortbySelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sortby_select, null);
        bottomSpace = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        btnSuggest = (Button) view.findViewById(R.id.btnSuggest);
        btnNear = (Button) view.findViewById(R.id.btnNear);
        btnPriceLow = (Button) view.findViewById(R.id.btnPriceLow);
        btnPriceHigh = (Button) view.findViewById(R.id.btnPriceHigh);
        btnCheap = (Button) view.findViewById(R.id.btnCheap);
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
    }


    void openAnim(){
        final AbsoluteLayout background = (AbsoluteLayout) view.findViewById(R.id.bottomSpace);
        final LinearLayout selectList = (LinearLayout) view.findViewById(R.id.listLayout);

        final Animation calendarAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.in_from_top);
        Animation backgroundAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
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


    @Override
    public void onMessageArrived(Intent message) {

    }
}
