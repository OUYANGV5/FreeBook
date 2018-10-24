package com.ouyang.freebook;

import android.app.Application;

import org.litepal.LitePal;

public class MyApplicaiotn extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

}
