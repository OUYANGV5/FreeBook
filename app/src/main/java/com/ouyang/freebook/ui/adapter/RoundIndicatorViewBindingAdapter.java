package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;

import com.ouyang.freebook.ui.view.RoundIndicatorView;

public class RoundIndicatorViewBindingAdapter {
    @BindingAdapter("indicatorLength")
    public static void setAdapter(RoundIndicatorView roundIndicatorView,int size){
        roundIndicatorView.updateIndicatorViewLength((int) Math.ceil(size/3.0f));
        roundIndicatorView.setSelectedItem(0);
    }
}
