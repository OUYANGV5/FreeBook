package com.ouyang.freebook.ui.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.FragmentRankBinding;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.request.RankRequest;
import com.ouyang.freebook.ui.activity.BookDetailsActivity;
import com.ouyang.freebook.ui.activity.MainActivity;
import com.ouyang.freebook.ui.adapter.BookListAdapter;
import com.ouyang.freebook.ui.view.ProxyDrawable;
import com.ouyang.freebook.util.RequestUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends BaseFragment {
    FragmentRankBinding bind;

    private RankRequest rankRequest;
    private String sexType;
    private String sortType;
    private String timeType;
    private int index;

    BookListAdapter bookListAdapter;
    boolean hasNext;
    public RankFragment() {

        // Required empty public constructor
    }
    public void initTabLayout(TabLayout tabLayout){
        View v=tabLayout.getChildAt(0);
        v.setBackgroundDrawable(new ProxyDrawable(v,getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public void init() {
        hasNext=true;
        sexType = RequestConfig.SEX_TYPE_MAN;
        sortType = RequestConfig.SORT_TYPE_COMMEND;
        timeType = RequestConfig.TIME_TYPE_TOTAL;
        index = 1;
        rankRequest = RequestUtil.get(RankRequest.class);

        initTabLayout(bind.sex);
        initTabLayout(bind.sort);
        initTabLayout(bind.time);
        bind.sex.addTab(bind.sex.newTab().setText("男生"));
        bind.sex.addTab(bind.sex.newTab().setText("女生"));
        bind.sort.addTab(bind.sort.newTab().setText("最热"));
        bind.sort.addTab(bind.sort.newTab().setText("推荐"));
        bind.sort.addTab(bind.sort.newTab().setText("完结"));
        bind.sort.addTab(bind.sort.newTab().setText("收藏"));
        bind.sort.addTab(bind.sort.newTab().setText("新书"));
        bind.sort.addTab(bind.sort.newTab().setText("评分"));
        bind.time.addTab(bind.time.newTab().setText("周榜"));
        bind.time.addTab(bind.time.newTab().setText("月榜"));
        bind.time.addTab(bind.time.newTab().setText("总榜"));
        bind.sort.getTabAt(1).select();
        bind.time.getTabAt(2).select();
        bind.sex.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        sexType=RequestConfig.SEX_TYPE_MAN;
                        break;
                    case 1:
                        sexType=RequestConfig.SEX_TYPE_LADY;
                        break;
                }
                getData(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bind.sort.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        sortType=RequestConfig.SORT_TYPE_HOT;
                        break;
                    case 1:
                        sortType=RequestConfig.SORT_TYPE_COMMEND;
                        break;
                    case 2:
                        sortType=RequestConfig.SORT_TYPE_OVER;
                        break;
                    case 3:
                        sortType=RequestConfig.SORT_TYPE_COLLECT;
                        break;
                    case 4:
                        sortType=RequestConfig.SORT_TYPE_NEW;
                        break;
                    case 5:
                        sortType=RequestConfig.SORT_TYPE_VOTE;
                        break;
                }
                getData(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bind.time.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        timeType=RequestConfig.TIME_TYPE_WEEK;
                        break;
                    case 1:
                        timeType=RequestConfig.TIME_TYPE_MONTH;
                        break;
                    case 2:
                        timeType=RequestConfig.TIME_TYPE_TOTAL;
                        break;
                }
                getData(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bind.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        bookListAdapter = new BookListAdapter();
        bind.recyclerView.setAdapter(bookListAdapter);
        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Book book = bookListAdapter.getBookList().get(position);
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                //intent.putExtra("id", book.getId());
                intent.putExtra("book",book);
                startActivity(intent);
            }
        });
        bind.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                if(recyclerView.computeVerticalScrollExtent()+recyclerView.computeVerticalScrollOffset()
                        >=recyclerView.computeVerticalScrollRange()&&bookListAdapter.getBookList().size()>0){
                    bookListAdapter.setBottomStatus(1);
                    bookListAdapter.notifyDataSetChanged();
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData(false);
                        }
                    },200);

                }
            }
        });
        bind.refresh.setColorSchemeColors(Color.RED);
        bind.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(true);
            }
        });
        getData(true);
    }

    public void getData(final boolean isUpdate) {

        int i;
        if(isUpdate){
            bookListAdapter.setBottomStatus(BookListAdapter.STATUS_NOTHING);
            bookListAdapter.notifyDataSetChanged();
            i=1;
        }else {
            if(!hasNext){
                bookListAdapter.setBottomStatus(BookListAdapter.STATUS_NOTHING_LOAD);
                bookListAdapter.notifyDataSetChanged();
                return;
            }
            i=index++;
        }
        rankRequest.getRankList(sexType, sortType, timeType, i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if(isUpdate)
                            bind.refresh.setRefreshing(true);
                    }

                    @Override
                    public void onNext(ResponseData<BookList> bookListResponseData) {
                        hasNext=bookListResponseData.getData().isHasNext();
                        if(isUpdate){
                            bookListAdapter.getBookList().clear();
                            bookListAdapter.addBookList(bookListResponseData.getData().getBookList());
                            if(bind.recyclerView.getChildCount()>0)
                                bind.recyclerView.scrollToPosition(0);
                            index=1;
                        }else {
                            bookListAdapter.addBookList(bookListResponseData.getData().getBookList());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        bind.refresh.setRefreshing(false);
                        Log.e("请求网络失败", e.getMessage());
                        Snackbar.make(getView(), "请求失败了", 3000).setAction("重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData(isUpdate);
                            }
                        }).show();

                    }

                    @Override
                    public void onComplete() {
                        if(isUpdate)
                            bind.refresh.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onVisibleAgain() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setToolbar(bind.toolbar, true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = DataBindingUtil.bind(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
