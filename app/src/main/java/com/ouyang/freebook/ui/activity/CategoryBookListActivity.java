package com.ouyang.freebook.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityCategoryBookListBinding;
import com.ouyang.freebook.databinding.ActivityRankBookListBinding;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Category;
import com.ouyang.freebook.ui.fragment.CategoryBookListFragment;
import com.ouyang.freebook.ui.fragment.RankBookListFragment;
import com.slideback.helper.SlideBackHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryBookListActivity extends AppCompatActivity {
    ActivityCategoryBookListBinding binding;

    private Category category;
    private String sort;
    private List<CategoryBookListFragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_category_book_list);
        SlideBackHelper.init(this);
        category=getIntent().getParcelableExtra("category");
        init();
    }
    private void init() {
        this.sort=getIntent().getStringExtra("sort");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.title.setText(category.getName());
        fragmentList=new ArrayList<>();
        fragmentList.add(CategoryBookListFragment.newInstance(RequestConfig.SORT_TYPE_HOT,category.getId(),"最热"));
        fragmentList.add(CategoryBookListFragment.newInstance(RequestConfig.SORT_TYPE_NEW,category.getId(),"最新"));
        fragmentList.add(CategoryBookListFragment.newInstance(RequestConfig.SORT_TYPE_VOTE,category.getId(),"评分"));
        fragmentList.add(CategoryBookListFragment.newInstance(RequestConfig.SORT_TYPE_OVER,category.getId(),"完结"));
        binding.viewPager.setOffscreenPageLimit(fragmentList.size()-2);
        binding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                Log.e("tttt",fragmentList.get(position).getTagName());
                return fragmentList.get(position).getTagName();
            }
        });
        binding.tab.setupWithViewPager(binding.viewPager);
    }

}
