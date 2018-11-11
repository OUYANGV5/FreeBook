package com.ouyang.freebook.ui.view;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ouyang.freebook.modle.bean.BookContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookReadAdapter extends PagerAdapter {
    private List<BookContent> contentList;
    private ViewPager viewPager;
    private Map<BookContent,List<String>> rowDataMap;//每一章的每一行内容
    public BookReadAdapter(List<BookContent> contentList, ViewPager viewPager) {
        this.contentList = new ArrayList<>();
        addBookContent(contentList);
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    public List<BookContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<BookContent> contentList) {
        this.contentList = contentList;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }
    public void addBookContent(BookContent bookContent){
        if(bookContent!=null){
            this.contentList.add(bookContent);

            onUpdateData(bookContent);
        }

    }
    public void addBookContent(List<BookContent> bookContent){
        if(bookContent!=null){
            for(BookContent b:bookContent){
                addBookContent(b);
            }
        }
    }
    public void onUpdateData(BookContent bookContent){

    }
}
