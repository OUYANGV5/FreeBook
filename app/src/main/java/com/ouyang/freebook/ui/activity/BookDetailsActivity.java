package com.ouyang.freebook.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ouyang.freebook.modle.bean.Book;
import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityBookDetailsBinding;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.bean.comment.CommentResponseData;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewDataBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImmersionUtil.setImmersion(this);
        book = getIntent().getParcelableExtra("book");
        viewDataBinding.setBook(book);
        init();
    }


    private void init() {
        bookRequest = RequestUtil.get(BookRequest.class);
        viewDataBinding.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BookDetailsActivity.this,ReadActivity.class);
                intent.putExtra("book",viewDataBinding.getBookDetails());
                startActivity(intent);
            }
        });
        setHeaderAlpha(0);
        viewDataBinding.scrollRoot.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY<=viewDataBinding.top.getHeight()){
                    float alpha=scrollY*1.0f/viewDataBinding.top.getHeight();
                    setHeaderAlpha(alpha);
                }else {
                    setHeaderAlpha(1f);
                }
            }
        });
        getData();
    }
    public void setHeaderAlpha(float alpha){
        viewDataBinding.toolbar.getBackground().setAlpha((int) (255*alpha));
        viewDataBinding.title.setAlpha(alpha);
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
                        viewDataBinding.executePendingBindings();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        bookRequest.getComment(book.getId()+"","4","4")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentResponseData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentResponseData commentResponseData) {
                        viewDataBinding.setCommentData(commentResponseData);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public static void startBookDetailsActivity(Context context,Book book){
        Intent intent=new Intent(context,BookDetailsActivity.class);
        intent.putExtra("book",book);
        context.startActivity(intent);
    }

}
