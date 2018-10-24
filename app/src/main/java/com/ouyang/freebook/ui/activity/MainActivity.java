package com.ouyang.freebook.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ouyang.freebook.R;
import com.ouyang.freebook.ui.fragment.BookrackFragment;
import com.ouyang.freebook.ui.fragment.ClassifyFragment;
import com.ouyang.freebook.ui.fragment.RankFragment;
import com.ouyang.freebook.ui.fragment.StoreFragment;
import com.ouyang.freebook.util.ImmersionUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.content)
    ViewPager content;

    List<Fragment> fragmentList;

    @BindView(R.id.bottomNav)
    BottomNavigationViewEx bottomNav;

    @BindView(R.id.contentRoot)
    LinearLayout contentRoot;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.rootView)
    DrawerLayout rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //ImmersionUtil.setImmersion(this);
        init();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BookrackFragment());
        fragmentList.add(new StoreFragment());
        fragmentList.add(new ClassifyFragment());
        fragmentList.add(new RankFragment());
        bottomNav.enableShiftingMode(false);
        bottomNav.enableItemShiftingMode(false);
        content.setOffscreenPageLimit(3);
        content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        bottomNav.setupWithViewPager(content);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, rootView,
                toolbar,
                R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                contentRoot.layout(navigationView.getRight(),
                        0,
                        display.getWidth() + navigationView.getRight(),
                        display.getHeight());
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        rootView.addDrawerListener(toggle);
        toggle.syncState();
    }
}
