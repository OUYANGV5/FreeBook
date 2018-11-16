package com.ouyang.freebook.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.FragmentRankBookListBinding;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.request.RankRequest;
import com.ouyang.freebook.ui.activity.BookDetailsActivity;
import com.ouyang.freebook.ui.activity.RankBookListActivity;
import com.ouyang.freebook.ui.adapter.BookListAdapter;
import com.ouyang.freebook.util.ImmersionUtil;
import com.ouyang.freebook.util.RequestUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RankBookListFragment extends BaseFragment {
    FragmentRankBookListBinding binding;
    private BookListAdapter bookListAdapter;
    private RankRequest rankRequest;

    private String tag;

    private String typeSort;
    private String typeTime;
    private int index;
    private boolean hasNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_rank_book_list,container,false);
        return binding.getRoot();
    }

    @Override
    public void onVisibleAgain() {

    }
    public static RankBookListFragment newInstance(String sort,String time,String tag){
        RankBookListFragment rankBookListFragment = new RankBookListFragment();
        rankBookListFragment.typeSort=sort;
        rankBookListFragment.typeTime=time;
        rankBookListFragment.tag=tag;
        return rankBookListFragment;
    }
    private void getData(final boolean isUpdate) {
        int i;
        /*if(isUpdate){
            index=1;
            i=index;
        }else {
            i=++index;
        }*/
        if(isUpdate){
            bookListAdapter.setBottomStatus(BookListAdapter.STATUS_NOTHING);
            bookListAdapter.notifyDataSetChanged();
            index=1;
            i=index;
        }else {
            if(!hasNext){
                bookListAdapter.setBottomStatus(BookListAdapter.STATUS_NOTHING_LOAD);
                bookListAdapter.notifyDataSetChanged();
                return;
            }
            i=index++;
        }
        rankRequest.getRankList(RequestConfig.SEX_TYPE_MAN,typeSort,typeTime,i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if(isUpdate)
                            binding.refresh.setRefreshing(true);
                    }

                    @Override
                    public void onNext(ResponseData<BookList> bookListResponseData) {
                        hasNext=bookListResponseData.getData().isHasNext();
                        List<Book> bookList = bookListResponseData.getData().getBookList();
                        if(isUpdate){
                            bookListAdapter.getBookList().clear();
                            bookListAdapter.notifyDataSetChanged();
                        }
                        bookListAdapter.addBookList(bookList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.refresh.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        binding.refresh.setRefreshing(false);
                    }
                });
    }

    @Override
    public void init() {
        rankRequest=RequestUtil.get(RankRequest.class);

        binding.refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(true);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bookListAdapter=new BookListAdapter();
        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Book book = bookListAdapter.getBookList().get(position);
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("book",book);
                startActivity(intent);
            }
        });
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(bookListAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
        getData(true);
    }

    public String getTagName() {
        return tag;
    }

    public void setTagName(String tag) {
        this.tag = tag;
    }
}
