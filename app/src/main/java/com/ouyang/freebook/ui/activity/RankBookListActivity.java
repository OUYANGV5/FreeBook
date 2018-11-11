package com.ouyang.freebook.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityRankBookListBinding;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.request.RankRequest;
import com.ouyang.freebook.ui.adapter.BookListAdapter;
import com.ouyang.freebook.ui.fragment.RankBookListFragment;
import com.ouyang.freebook.util.ImmersionUtil;
import com.ouyang.freebook.util.RequestUtil;
import com.slideback.helper.SlideBackHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RankBookListActivity extends AppCompatActivity {
    ActivityRankBookListBinding binding;
    private String sort;
    private List<RankBookListFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_rank_book_list);
        SlideBackHelper.init(this);
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
        String title=null;
        switch (sort){
            case RequestConfig.SORT_TYPE_COMMEND:
                title="推荐榜";
                break;
            case RequestConfig.SORT_TYPE_HOT:
                title="最热榜";
                break;
            case RequestConfig.SORT_TYPE_COLLECT:
                title="收藏榜";
                break;
            case RequestConfig.SORT_TYPE_VOTE:
                title="评分榜";
                break;
            case RequestConfig.SORT_TYPE_OVER:
                title="完结榜";
                break;
            case RequestConfig.SORT_TYPE_NEW:
                title="新书榜";
                break;
        }
        binding.title.setText(title);
        fragmentList=new ArrayList<>();
        fragmentList.add(RankBookListFragment.newInstance(sort,RequestConfig.TIME_TYPE_WEEK,"周榜"));
        fragmentList.add(RankBookListFragment.newInstance(sort,RequestConfig.TIME_TYPE_MONTH,"月榜"));
        fragmentList.add(RankBookListFragment.newInstance(sort,RequestConfig.TIME_TYPE_TOTAL,"总榜"));
        binding.viewPager.setOffscreenPageLimit(2);
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
