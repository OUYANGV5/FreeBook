package com.ouyang.freebook.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityWelcomeBinding;

import me.wangyuwei.particleview.ParticleView;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding activityWelcomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }else if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //setContentView(R.layout.activity_welcome);
        activityWelcomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_welcome);
        activityWelcomeBinding.particleview.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();

            }
        });

        activityWelcomeBinding.particleview.startAnim();
    }
}
