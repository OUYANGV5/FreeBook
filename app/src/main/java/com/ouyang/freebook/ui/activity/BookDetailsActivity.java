package com.ouyang.freebook.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ouyang.freebook.R;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.request.BookRequest;
import com.ouyang.freebook.util.ImmersionUtil;
import com.ouyang.freebook.util.RequestUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class BookDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bookImg)
    ImageView bookImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.ratingBar)
    MaterialRatingBar ratingBar;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.top)
    LinearLayout top;
    @BindView(R.id.category)
    TextView category;
    private long id;

    private BookRequest bookRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        ImmersionUtil.setImmersion(this);
        top.setPadding(top.getPaddingLeft(), top.getPaddingTop() + ImmersionUtil.getStateBar(this), top.getPaddingRight(), top.getPaddingBottom());
        id = getIntent().getLongExtra("id", 0);
        init();
    }

    private void init() {
        bookRequest = RequestUtil.get(BookRequest.class);
        getData();
    }

    public void getData() {
        bookRequest.getBookDetails(String.valueOf(id))
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
                        category.setText("类别:\t"+bookDetails.getCName());
                        status.setText("状态:\t" + bookDetails.getBookStatus());
                        ratingBar.setRating((float) (bookDetails.getBookVote().getScore() / 2));
                        rating.setText(""+bookDetails.getBookVote().getScore());
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
