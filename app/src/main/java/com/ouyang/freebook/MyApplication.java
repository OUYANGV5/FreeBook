package com.ouyang.freebook;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.slideback.helper.manager.ActivityLifeManager;

import org.litepal.LitePal;

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        ActivityLifeManager.registerListener(this);
        context=this;
    }

    public static Context getContext() {
        return context;
    }
}
