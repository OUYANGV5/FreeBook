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
import com.ouyang.freebook.util.RequestUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

    @Override
    public void init() {
        hasNext=true;
        sexType = RequestConfig.SEX_TYPE_MAN;
        sortType = RequestConfig.SORT_TYPE_HOT;
        timeType = RequestConfig.TIME_TYPE_TOTAL;
        index = 1;
        rankRequest = RequestUtil.get(RankRequest.class);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setToolbar(bind.toolbar, true);
        bind.sex.addTab(bind.sex.newTab().setText("男生"));
        bind.sex.addTab(bind.sex.newTab().setText("女生"));
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
                        >=recyclerView.computeVerticalScrollRange()){
                    bookListAdapter.setBottomStatus(1);
                    bookListAdapter.notifyDataSetChanged();
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData(false);
                        }
                    },500);

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
            bookListAdapter.setBottomStatus(0);
            bookListAdapter.notifyDataSetChanged();
            i=1;
        }else {
            if(!hasNext){
                bookListAdapter.setBottomStatus(2);
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
                            bookListAdapter.getBookList().addAll(bookListResponseData.getData().getBookList());
                            index=1;
                        }else {
                            bookListAdapter.addBookList(bookListResponseData.getData().getBookList());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
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
