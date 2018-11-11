package com.ouyang.freebook.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.FragmentCategoryBinding;
import com.ouyang.freebook.modle.bean.Category;
import com.ouyang.freebook.modle.bean.ResponseDataList;
import com.ouyang.freebook.modle.request.CategoryRequest;
import com.ouyang.freebook.ui.activity.MainActivity;
import com.ouyang.freebook.ui.adapter.CategoryAdapter;
import com.ouyang.freebook.util.RequestUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {
    FragmentCategoryBinding binding;
    private List<Category> categoryList;
    CategoryAdapter adapter;
    CategoryRequest categoryRequest;
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void init() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        categoryList=new ArrayList<>();
        adapter=new CategoryAdapter(categoryList);
        binding.recyclerView.setAdapter(adapter);
        categoryRequest=RequestUtil.get(CategoryRequest.class);
        getData();
    }

    @Override
    public void onVisibleAgain() {
        MainActivity mainActivity= (MainActivity) getActivity();
        mainActivity.setToolbar(binding.toolbar,true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_category,container,false);
        return binding.getRoot();
    }
    public void getData(){
        categoryRequest.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseDataList<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseDataList<Category> categoryResponseDataList) {
                        categoryList.clear();
                        categoryList.addAll(categoryResponseDataList.getData());
                        adapter.notifyDataSetChanged();
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
