package com.ouyang.freebook.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityMainBinding;
import com.ouyang.freebook.ui.fragment.BookshelfFragment;
import com.ouyang.freebook.ui.fragment.CategoryFragment;
import com.ouyang.freebook.ui.fragment.RankFragment;
import com.ouyang.freebook.ui.fragment.StoreFragment;
import com.ouyang.freebook.util.ImmersionUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Fragment> fragmentList;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionUtil.setImmersion(this);
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        init();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BookshelfFragment());
        fragmentList.add(new StoreFragment());
        fragmentList.add(new CategoryFragment());
        fragmentList.add(new RankFragment());
        activityMainBinding.bottomNav.enableShiftingMode(false);
        activityMainBinding.bottomNav.enableItemShiftingMode(false);
        activityMainBinding.bottomNav.enableAnimation(true);
        activityMainBinding.content.setOffscreenPageLimit(3);
        activityMainBinding.content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        activityMainBinding.bottomNav.setupWithViewPager(activityMainBinding.content);
    }


    public void setToolbar(Toolbar toolbar,boolean showNavIcon) {
        if (toolbar == null) {
            getSupportActionBar().hide();
            return;
        }
        setSupportActionBar(toolbar);
        if(!showNavIcon){
            return;
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMainBinding.rootView,
                toolbar,
                R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                activityMainBinding.contentRoot.layout(activityMainBinding.navigationView.getRight(),
                        0,
                        display.getWidth() + activityMainBinding.navigationView.getRight(),
                        display.getHeight());
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        activityMainBinding.rootView.addDrawerListener(toggle);
        toggle.syncState();
    }
}
