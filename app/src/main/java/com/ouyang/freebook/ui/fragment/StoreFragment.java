package com.ouyang.freebook.ui.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.FragmentStoreBinding;
import com.ouyang.freebook.modle.GlideImageLoader;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Banner;
import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.modle.bean.CategoryBooks;
import com.ouyang.freebook.modle.bean.ResponseDataList;
import com.ouyang.freebook.modle.request.MainRequest;
import com.ouyang.freebook.ui.activity.BookDetailsActivity;
import com.ouyang.freebook.ui.activity.RankBookListActivity;
import com.ouyang.freebook.util.RequestUtil;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseFragment implements View.OnClickListener {
    FragmentStoreBinding binding;
    MainRequest mainRequest;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void init() {
        mainRequest=RequestUtil.get(MainRequest.class);
        mainRequest.getBanner(RequestConfig.SEX_TYPE_MAN)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseDataList<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseDataList<Banner> bannerResponseDataList) {
                        binding.setBannerList(bannerResponseDataList.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mainRequest.getBase(RequestConfig.SEX_TYPE_MAN)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseDataList<CategoryBooks>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseDataList<CategoryBooks> categoryBooksResponseDataList) {
                        binding.gridNewView.setCategoryBooks(categoryBooksResponseDataList.getData().get(0));
                        binding.gridHotView.setCategoryBooks(categoryBooksResponseDataList.getData().get(1));
                        binding.scrollView.setCategoryBooks(categoryBooksResponseDataList.getData().get(2));
                        binding.gridOverView.setCategoryBooks(categoryBooksResponseDataList.getData().get(3));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("123456",""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("123456","on");
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
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_store, container, false);
        created();
        initListener();
        return binding.getRoot();
    }

    private void initListener() {
        binding.commend.setOnClickListener(this);
        binding.vote.setOnClickListener(this);
        binding.hot.setOnClickListener(this);
        binding.over.setOnClickListener(this);
        binding.collect.setOnClickListener(this);
        binding.scrollView.more.setOnClickListener(this);
        binding.gridNewView.more.setOnClickListener(this);
        binding.gridHotView.more.setOnClickListener(this);
        binding.gridOverView.more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sort=null;
        switch (v.getId()){
            case R.id.commend:
                sort=RequestConfig.SORT_TYPE_COMMEND;
                break;
            case R.id.vote:
                sort=RequestConfig.SORT_TYPE_VOTE;
                break;
            case R.id.hot:
                sort=RequestConfig.SORT_TYPE_HOT;
                break;
            case R.id.over:
                sort=RequestConfig.SORT_TYPE_OVER;
                break;
            case R.id.collect:
                sort=RequestConfig.SORT_TYPE_COLLECT;
                break;
        }
        if(binding.scrollView.more==v){
            sort=RequestConfig.SORT_TYPE_COMMEND;
        }else if(binding.gridNewView.more==v){
            sort=RequestConfig.SORT_TYPE_NEW;
        }else if(binding.gridHotView.more==v){
            sort=RequestConfig.SORT_TYPE_HOT;
        }else if(binding.gridOverView.more==v){
            sort=RequestConfig.SORT_TYPE_OVER;
        }
        Intent intent=new Intent(getActivity(),RankBookListActivity.class);
        intent.putExtra("sort",sort);
        startActivity(intent);
    }
    private void created(){
        binding.banner.setImageLoader(new GlideImageLoader())
                .setDelayTime(2000).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Book book=new Book();
                String bookId=binding.getBannerList().get(position).getParam();
                book.setId(Integer.parseInt(bookId));
                BookDetailsActivity.startBookDetailsActivity(getContext(),book);
            }
        }) .start();
        binding.scrollView.roundIndicatorFirst.setUpdateWidthViewPager(binding.scrollView.scrollBooks);
        binding.scrollViewRoot.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int visible;
                if(scrollY>binding.header.getHeight()){
                    visible=View.VISIBLE;

                }else {
                    visible=View.GONE;
                }
                if(binding.visibleHeader.getVisibility()!=visible){
                    binding.visibleHeader.setVisibility(visible);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*banner.stopAutoPlay();
        unbinder.unbind();*/
    }

}
