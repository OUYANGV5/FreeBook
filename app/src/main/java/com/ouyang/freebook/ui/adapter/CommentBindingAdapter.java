package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ouyang.freebook.modle.bean.comment.Comments;
import com.ouyang.freebook.modle.bean.comment.Hots;

import java.util.List;

public class CommentBindingAdapter {
    @BindingAdapter("commentAdapter")
    public static void setCommentAdapter(RecyclerView recyclerView, List<Comments> hotsList){
        if(hotsList==null){
            return;
        }
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new BookCommentListAdapter(hotsList));
    }
}
