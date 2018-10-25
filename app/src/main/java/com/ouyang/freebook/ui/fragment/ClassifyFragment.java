package com.ouyang.freebook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.BookChapter;
import com.ouyang.freebook.modle.bean.BookChapterData;
import com.ouyang.freebook.modle.bean.BookContent;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.CategoryBooks;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.bean.ResponseDataList;
import com.ouyang.freebook.modle.request.BookRequest;
import com.ouyang.freebook.modle.request.CategoryRequest;
import com.ouyang.freebook.modle.request.MainRequest;
import com.ouyang.freebook.ui.activity.MainActivity;
import com.ouyang.freebook.util.RequestUtil;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ClassifyFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        return view;
    }

    @Override
    public void init() {
        MainActivity mainActivity= (MainActivity) getActivity();
        Log.e("aaa","22222222222");
        RequestUtil.get(MainRequest.class).getBase(RequestConfig.SEX_TYPE_LADY)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<ResponseDataList<CategoryBooks>, ObservableSource<ResponseData<BookDetails>>>() {
                    @Override
                    public ObservableSource<ResponseData<BookDetails>> apply(ResponseDataList<CategoryBooks> categoryBooksResponseDataList) throws Exception {
                        Log.e("aaaa",""+categoryBooksResponseDataList.getData().get(0).getBooks().get(0).getId());
                        return RequestUtil.get(BookRequest.class).getBookDetails(categoryBooksResponseDataList.getData().get(0).getBooks().get(0).getId()+"");
                    }
                }).flatMap(new Function<ResponseData<BookDetails>, ObservableSource<ResponseData<BookChapterData>>>() {
            @Override
            public ObservableSource<ResponseData<BookChapterData>> apply(ResponseData<BookDetails> bookDetailsResponseData) throws Exception {
                Log.e("aaaa",""+bookDetailsResponseData.getData().getId());
                return RequestUtil.get(BookRequest.class).getBookChapter(bookDetailsResponseData.getData().getId()+"");
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookChapterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<BookChapterData> bookChapterDataResponseData) {
                        Log.e("aaa",bookChapterDataResponseData.getData().getList().get(0).getList().get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                            Log.e("aaa",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onVisibleAgain() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
