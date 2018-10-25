package com.ouyang.freebook.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ouyang.freebook.R;
import com.ouyang.freebook.modle.GlideImageLoader;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Banner;
import com.ouyang.freebook.modle.bean.ResponseDataList;
import com.ouyang.freebook.modle.request.MainRequest;
import com.ouyang.freebook.util.RequestUtil;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseFragment {


    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void init() {
        RequestUtil.get(MainRequest.class)
                .getBanner(RequestConfig.SEX_TYPE_MAN)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<ResponseDataList<Banner>, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(ResponseDataList<Banner> banner) throws Exception {
                        List<Banner> data = banner.getData();
                        List<String> imgUrlList=new ArrayList<>(data.size());
                        for(int i=0;i<data.size();i++){
                            imgUrlList.add(data.get(i).getImgurl());
                        }
                        return Observable.just(imgUrlList);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        //banner.update(strings);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("error",throwable.getMessage());
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
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        created();
        return view;
    }
    private void created(){
        /*banner.setImageLoader(new GlideImageLoader())
                .setDelayTime(2000).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        })
                .start();*/
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*banner.stopAutoPlay();
        unbinder.unbind();*/
    }
}
