package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;

import com.ouyang.freebook.util.ImmersionUtil;

public class PaddingBindingAdapter {

    @BindingAdapter("paddingTopToStateBar")
    public static void setPaddingTopWithStateBar(View view,boolean isPadding){
        view.setPadding(view.getPaddingLeft()
                ,view.getPaddingTop()+ImmersionUtil.getStateBar(view.getContext())
                ,view.getPaddingRight()
                ,view.getPaddingBottom());
    }
    @BindingAdapter(value = "heightWithStateBar",requireAll = false)
    public static void setHeightWithStateBar(View view,boolean is){
        view.getLayoutParams().height=ImmersionUtil.getStateBar(view.getContext());
        view.requestLayout();
    }
}
