package com.ouyang.freebook.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    private boolean scroll=false;
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scroll&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scroll&&super.onTouchEvent(ev);
    }

    public boolean isScroll() {
        return scroll;
    }

    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }
}
