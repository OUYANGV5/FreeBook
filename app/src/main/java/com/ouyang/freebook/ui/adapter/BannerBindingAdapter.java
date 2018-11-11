package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.view.Gravity;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class BannerBindingAdapter {
    @BindingAdapter("bannerList")
    public static void setBannerDataList(final Banner banner, List<com.ouyang.freebook.modle.bean.Banner> data){
        if(data==null){
            return;
        }
        Observable.just(data)
                .map(new Function<List<com.ouyang.freebook.modle.bean.Banner>, List<String>>() {
                    @Override
                    public List<String> apply(List<com.ouyang.freebook.modle.bean.Banner> banners) throws Exception {
                        List<String> imgUrlList=new ArrayList<>();
                        for(com.ouyang.freebook.modle.bean.Banner b:banners){
                            imgUrlList.add(b.getImgurl());
                        }
                        return imgUrlList;
                    }
                }).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> stringList) {
                banner.update(stringList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
