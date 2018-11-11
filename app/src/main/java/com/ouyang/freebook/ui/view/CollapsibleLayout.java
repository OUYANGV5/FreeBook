package com.ouyang.freebook.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
@Deprecated
public class CollapsibleLayout extends ViewGroup {
    private View bottomView;

    public CollapsibleLayout(Context context) {
        super(context);
    }

    public CollapsibleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsibleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        View childen=null;
        if(getChildCount()>0){
            childen=getChildAt(0);
            childen.measure(widthMeasureSpec,heightMeasureSpec);
        }else {
            setMeasuredDimension(width,height);
            return;
        }
        if(heightMode!=MeasureSpec.EXACTLY){
            height=childen.getMeasuredHeight();
        }
        if(widthMode!=MeasureSpec.EXACTLY){
            width=childen.getMeasuredWidth();
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
