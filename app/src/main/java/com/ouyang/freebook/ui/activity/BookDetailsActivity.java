package com.ouyang.freebook.ui.activity;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
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
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;

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
        /*top.setPadding(top.getPaddingLeft(), top.getPaddingTop() + ImmersionUtil.getStateBar(this), top.getPaddingRight(), top.getPaddingBottom());
        //id = getIntent().getLongExtra("id", 0);*/
        book = getIntent().getParcelableExtra("book");
        viewDataBinding.setBook(book);
        bookRequest=RequestUtil.get(BookRequest.class);
        bookRequest.getBookDetails(book.getId()+"").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<BookDetails> bookDetailsResponseData) {
                        viewDataBinding.setBookDetails(bookDetailsResponseData.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //init();
    }
/*

    private void init() {
        bookRequest = RequestUtil.get(BookRequest.class);
        Glide.with(top).load(RequestConfig.URL_IMG_BOOK_BASE + book.getImg())
                .apply(new RequestOptions().optionalTransform(new BlurTransformation(360)))
                .into(new CustomViewTarget<LinearLayout, Drawable>(top) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackgroundDrawable(resource);
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
        Glide.with(bookImg).load(RequestConfig.URL_IMG_BOOK_BASE + book.getImg()).into(bookImg);
        name.setText(book.getName());
        author.setText("作者:\t" + book.getAuthor());
        category.setText("类别:\t" + book.getCName());
        ratingBar.setRating((float) (book.getScore() / 2));
        rating.setText("" + book.getScore());
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
                        Glide.with(top).load(RequestConfig.URL_IMG_BOOK_BASE + bookDetails.getImg())
                                .apply(new RequestOptions().optionalTransform(new BlurTransformation(360)))
                                .into(new CustomViewTarget<LinearLayout, Drawable>(top) {
                                    @Override
                                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                        this.view.setBackgroundDrawable(resource);
                                    }

                                    @Override
                                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                                    }
                                });
                        Glide.with(bookImg).load(RequestConfig.URL_IMG_BOOK_BASE + bookDetails.getImg()).into(bookImg);
                        name.setText(bookDetails.getName());
                        author.setText("作者:\t" + bookDetails.getAuthor());
                        category.setText("类别:\t" + bookDetails.getCName());
                        status.setText("状态:\t" + bookDetails.getBookStatus());
                        ratingBar.setRating((float) (bookDetails.getBookVote().getScore() / 2));
                        rating.setText("" + bookDetails.getBookVote().getScore());
                        text.setText(bookDetails.getDesc());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
*/

}
