package com.wt.xwttravel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Genius on 3/28/16.
 */
public class ResizableRelativeLayout extends RelativeLayout {


    private OnSizeChangeListener onSizeChangeListener;

    public interface OnSizeChangeListener{
        void onSizeChange(int width, int height, int oldWidth, int oldHeight);
    }

    public ResizableRelativeLayout(Context context) {
        super(context);
    }

    public ResizableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        onSizeChangeListener.onSizeChange(w,h,oldw,oldh);
    }

    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener){
        this.onSizeChangeListener = onSizeChangeListener;
    }


}
