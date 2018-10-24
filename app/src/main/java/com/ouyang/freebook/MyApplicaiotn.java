package com.ouyang.freebook;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.slideback.helper.manager.ActivityLifeManager;

import org.litepal.LitePal;

public class MyApplicaiotn extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        ActivityLifeManager.registerListener(this);
    }

}
