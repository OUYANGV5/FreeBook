package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemBookCommentBinding;
import com.ouyang.freebook.modle.bean.comment.Comments;
import com.ouyang.freebook.modle.bean.comment.Hots;

import java.util.List;

public class BookCommentListAdapter extends RecyclerView.Adapter<BookCommentListAdapter.CommentViewHoder> {
    private List<Comments> list;

    public BookCommentListAdapter(List<Comments> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CommentViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookCommentBinding itemBookCommentBinding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                , R.layout.item_book_comment,parent,false
        );
        return new CommentViewHoder(itemBookCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHoder holder, int position) {
        holder.itemBookCommentBinding.setComment(list.get(position));
        holder.itemBookCommentBinding.executePendingBindings();
        if(position==list.size()-1){
            holder.itemBookCommentBinding.moreComment.setVisibility(View.VISIBLE);
            holder.itemBookCommentBinding.moreComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else {
            holder.itemBookCommentBinding.moreComment.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CommentViewHoder extends RecyclerView.ViewHolder{
        private ItemBookCommentBinding itemBookCommentBinding;
        public CommentViewHoder(ItemBookCommentBinding itemBookCommentBinding) {
            super(itemBookCommentBinding.getRoot());
            this.itemBookCommentBinding=itemBookCommentBinding;
        }
    }
}
