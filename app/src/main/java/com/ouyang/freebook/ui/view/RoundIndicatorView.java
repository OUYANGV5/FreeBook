package com.ouyang.freebook.ui.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ouyang.freebook.R;

import java.util.ArrayList;
import java.util.List;

public class RoundIndicatorView extends LinearLayout {
    private List<ImageView> indicatorViewList;
    private int indicatorWidth;
    private int indicatorHeight;
    private int indicatorPaddingHorizontal;
    private int indicatorPaddingVertical;
    public RoundIndicatorView(Context context) {
        super(context);
        init();
    }

    public RoundIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public int getIndicatorWidth() {
        return indicatorWidth;
    }

    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    public int getIndicatorPaddingHorizontal() {
        return indicatorPaddingHorizontal;
    }

    public void setIndicatorPaddingHorizontal(int indicatorPaddingHorizontal) {
        this.indicatorPaddingHorizontal = indicatorPaddingHorizontal;
    }

    public int getIndicatorPaddingVertical() {
        return indicatorPaddingVertical;
    }

    public void setIndicatorPaddingVertical(int indicatorPaddingVertical) {
        this.indicatorPaddingVertical = indicatorPaddingVertical;
    }

    private void init(){
        indicatorViewList=new ArrayList<>();
        indicatorWidth=10;
        indicatorHeight=10;
        indicatorPaddingHorizontal=5;
        indicatorPaddingVertical=5;
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.HORIZONTAL);

    }
    public void updateIndicatorViewLength(int length){
        indicatorViewList.clear();
        for(int i=0;i<length;i++){
            indicatorViewList.add(getNewIndicatorView());
        }
        updateIndicatorView();

    }
    public void updateIndicatorView(){
        removeAllViews();
        for(View v:indicatorViewList){
            addView(v);
        }
    }
    private ImageView getNewIndicatorView(){
        ImageView imageView=new ImageView(getContext());
        MarginLayoutParams layoutParams=new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(indicatorPaddingHorizontal,indicatorPaddingVertical,indicatorPaddingHorizontal,indicatorPaddingVertical);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.circle_indicator_drawable);
        return imageView;
    }
    public void setSelectedItem(int position){
        for(int i=0;i<getChildCount();i++){
            View v=getChildAt(i);
            if(i==position){
                v.setSelected(true);
            }else {
                v.setSelected(false);
            }
        }

    }
    public void setUpdateWidthViewPager(final ViewPager viewPager){
        if(viewPager!=null)
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setSelectedItem(position);
            }
        });
    }
}
