package com.ouyang.freebook.ui.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityBookDetailsBinding;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.request.BookRequest;
import com.ouyang.freebook.util.ImmersionUtil;
import com.ouyang.freebook.util.RequestUtil;
import com.slideback.helper.SlideBackHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookDetailsActivity extends AppCompatActivity {
    ActivityBookDetailsBinding viewDataBinding;
    private Book book;
    private BookRequest bookRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding= DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        //setContentView(R.layout.activity_book_details);
        SlideBackHelper.init(this);
        setSupportActionBar(viewDataBinding.toolbar);
        ImmersionUtil.setImmersion(this);
        book = getIntent().getParcelableExtra("book");
        viewDataBinding.setBook(book);
        init();
    }


    private void init() {
        bookRequest = RequestUtil.get(BookRequest.class);
        getData();
    }

    public void getData() {
        bookRequest.getBookDetails(String.valueOf(book.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<BookDetails> bookDetailsResponseData) {
                        BookDetails bookDetails = bookDetailsResponseData.getData();
                        viewDataBinding.setBookDetails(bookDetails);
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
