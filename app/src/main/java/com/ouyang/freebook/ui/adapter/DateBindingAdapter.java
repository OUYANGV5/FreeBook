package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBindingAdapter {
    public static SimpleDateFormat simpleDateFormat;
    static {
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    }
    @BindingAdapter("timeText")
    public static void setTime(TextView textView,long time){
        textView.setText(simpleDateFormat.format(new Date(time)));
    }
}
