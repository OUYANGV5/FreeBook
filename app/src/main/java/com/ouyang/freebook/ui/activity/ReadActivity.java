package com.ouyang.freebook.ui.activity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ActivityReadBinding;
import com.ouyang.freebook.modle.bean.BookChapter;
import com.ouyang.freebook.modle.bean.BookChapterData;
import com.ouyang.freebook.modle.bean.BookChapterList;
import com.ouyang.freebook.modle.bean.BookContent;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.pojo.StringIndex;
import com.ouyang.freebook.modle.request.BookRequest;
import com.ouyang.freebook.ui.adapter.BookContentAdapter;
import com.ouyang.freebook.ui.adapter.ChapterListAdapter;
import com.ouyang.freebook.util.ImmersionUtil;
import com.ouyang.freebook.util.RequestUtil;
import com.ouyang.freebook.util.TextViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReadActivity extends AppCompatActivity {
    ActivityReadBinding binding;
    BookRequest bookRequest;
    private BookDetails bookDetails;

    private BookContentAdapter bookContentAdapter;
    private List<BookContent> contentList;
    private BookContent currentContent;

    private List<BookChapter> chapterList;

    private Animation toolbarShowAnimation;
    private Animation toolbarHideAnimation;
    private Animation bottomShowAnimation;
    private Animation bottomHideAnimation;
    private boolean isMenuShow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);
        init();
        getAllChapter();
        initChapterContent();

    }

    private void getAllChapter() {
        bookRequest.getBookChapter(bookDetails.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookChapterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<BookChapterData> bookChapterDataResponseData) {
                        List<BookChapterList> list = bookChapterDataResponseData.getData().getList();
                        for (BookChapterList bookChapterList : list) {
                            chapterList.addAll(bookChapterList.getList());
                        }

                        binding.seekbar.setMax(chapterList.size() - 1);
                        binding.seekbar.setEnabled(true);
                        ChapterListAdapter chapterListAdapter=new ChapterListAdapter(chapterList);
                        chapterListAdapter.setOnItemClickListener(new ChapterListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                BookChapter bookChapter = chapterList.get(position);
                                boolean isNext = position > findCurrentContentIndex();
                                getData(bookDetails.getId(), bookChapter.getId(), isNext, true);
                            }
                        });
                        binding.chapterList.setAdapter(chapterListAdapter);
                        binding.num.setText("共"+chapterList.size()+"章");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initChapterContent() {
        contentList = new ArrayList<>(chapterList.size());
        bookContentAdapter = new BookContentAdapter(contentList);
        bookContentAdapter.setOnBindListener(new BookContentAdapter.OnBindListener() {
            @Override
            public void onBindViewHolder(int position) {
                BookContent bookContent = contentList.get(position);
                if (!bookContent.getCid().equals(bookDetails.getFirstChapterId())) {
                    getData(bookContent.getId(), bookContent.getPid(), false, false);
                }
                if (!bookContent.getCid().equals(bookDetails.getLastChapterId())) {
                    getData(bookContent.getId(), bookContent.getNid(), true, false);
                }
            }
        });
        bookContentAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuShow) {
                    hideMenu();
                } else {
                    showMenu();
                }
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setVerticalScrollBarEnabled(false);
        binding.recyclerView.setAdapter(bookContentAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("xxxz",recyclerView.getScrollY()+"");
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                BookContent bookContent = contentList.get(position);
                if (currentContent != bookContent) {
                    currentContent = bookContent;
                    binding.setChapter(currentContent.getCname());
                    binding.title.setText(currentContent.getCname());
                    for (int i = 0; i < chapterList.size(); i++) {
                        BookChapter bookChapter = chapterList.get(i);
                        if (currentContent.getCid().equals(bookChapter.getId())) {
                            binding.seekbar.setProgress(i);
                            binding.chapterList.scrollToPosition(i);
                            ChapterListAdapter chapterListAdapter= (ChapterListAdapter) binding.chapterList.getAdapter();
                            chapterListAdapter.setSelectItem(i);
                            break;
                        }
                    }
                }

            }
        });

        getCurrentChapter();
    }

    private void getCurrentChapter() {
        getData(bookDetails.getId(), bookDetails.getFirstChapterId(), true, false);
    }

    public void showMenu() {
        getSupportActionBar().show();
        binding.bottom.setVisibility(View.VISIBLE);
        binding.toolbar.startAnimation(toolbarShowAnimation);
        binding.bottom.startAnimation(bottomShowAnimation);
        isMenuShow=true;
    }

    public void hideMenu() {
        binding.toolbar.startAnimation(toolbarHideAnimation);
        binding.bottom.startAnimation(bottomHideAnimation);
        isMenuShow = false;
    }

    private void init() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        binding.bottom.setVisibility(View.GONE);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bookRequest = RequestUtil.get(BookRequest.class);
        bookDetails = getIntent().getParcelableExtra("book");
        chapterList = new ArrayList<>();
        toolbarShowAnimation = AnimationUtils.loadAnimation(this, R.anim.read_toolbar_show);
        toolbarShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                getSupportActionBar().show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        toolbarHideAnimation = AnimationUtils.loadAnimation(this, R.anim.read_toolbar_hide);
        toolbarHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getSupportActionBar().hide();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottomShowAnimation = AnimationUtils.loadAnimation(this, R.anim.read_bottom_menu_show);
        bottomShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.bottom.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottomHideAnimation = AnimationUtils.loadAnimation(this, R.anim.read_bottom_menu_hide);
        bottomHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.bottom.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.seekbar.setEnabled(false);
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.e("zxcv", "" + progress);
                BookChapter bookChapter = chapterList.get(progress);
                boolean isNext = progress > findCurrentContentIndex();
                getData(bookDetails.getId(), bookChapter.getId(), isNext, true);
            }
        });
        binding.name.setText(bookDetails.getName());
        binding.num.setText("共xxx章");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.chapterList.setLayoutManager(linearLayoutManager);
        binding.chapterList.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
        binding.buttonCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMenu();
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    public void getData(String bookId, final String chapterId, final boolean next, final boolean isScrollTo) {
        if (chapterId.equals("-1")) {
            return;
        }
        for (BookContent bookContent : contentList) {
            if (chapterId.equals(bookContent.getCid())) {
                return;
            }
        }
        Log.e("xxx", "获取" + chapterId);
        bookRequest.getBookContent(bookId, chapterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<BookContent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<BookContent> bookContentResponseData) {
                        List<String> stringList = TextViewUtil.makeStringToSection(bookContentResponseData.getData().getContent());
                        bookContentResponseData.getData().setContent(TextViewUtil.makeStringListToString(stringList, 0, stringList.size()));
                        int shouldPlacePosition = shouldPlacePosition(bookContentResponseData.getData(), next);
                        contentList.add(shouldPlacePosition, bookContentResponseData.getData());
                            /*if(next){
                                contentList.add(findContentIndexByChapterId(chapterId),bookContentResponseData.getData());
                            }else {
                                contentList.add(findContentIndexByChapterId(chapterId),bookContentResponseData.getData());
                            }*/
                        int current = findCurrentContentIndex();
                        bookContentAdapter.notifyDataSetChanged();
                        if (isScrollTo) {
                            binding.recyclerView.getLayoutManager().scrollToPosition(shouldPlacePosition);
                        }else if(!next) {
                            binding.recyclerView.getLayoutManager().scrollToPosition(current);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public int shouldPlacePosition(BookContent bookContent, boolean isNext) {
        String chapterId = bookContent.getCid();
        for (int i = 0; i < contentList.size(); i++) {
            BookContent b = contentList.get(i);
            if (isNext) {
                if (b.getNid().equals(chapterId)) {
                    return i + 1;
                }
            } else {
                if (b.getPid().equals(chapterId)) {
                    return i;
                }
            }
        }
        if (isNext) {
            return contentList.size();
        } else {
            return 0;
        }
    }

    public int findCurrentContentIndex() {
        for (int i = 0; i < contentList.size(); i++) {
            if (contentList.get(i) == currentContent) {
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImmersionUtil.makeImmersionMode(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("下载本书");
        return super.onCreateOptionsMenu(menu);
    }
}
