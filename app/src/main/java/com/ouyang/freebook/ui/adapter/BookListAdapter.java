package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemBookBinding;
import com.ouyang.freebook.databinding.LayoutBottomAddmoreBinding;
import com.ouyang.freebook.modle.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ITEM = 0;//普通项
    public static final int TYPE_BOTTOM = 1;//底部控件

    public static final int STATUS_NOTHING=0;//无
    public static final int STATUS_LOADING=1;//加载中
    public static final int STATUS_NOTHING_LOAD=2;//加载完

    private List<Book> bookList ;
    private OnItemClickListener onItemClickListener;
    private int bottomStatus;// STATUS__*
    public BookListAdapter() {
        bookList = new ArrayList<>();
    }

    public BookListAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBookList(List<Book> bookList) {
        this.bookList.addAll(bookList);
        notifyDataSetChanged();
    }

    public int getBottomStatus() {
        return bottomStatus;
    }

    public void setBottomStatus(int bottomStatus) {
        this.bottomStatus = bottomStatus;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            ItemBookBinding itemBookBinding=DataBindingUtil.inflate(LayoutInflater
                    .from(parent.getContext()), R.layout.item_book, parent, false);
            return new BookViewHolder(itemBookBinding);
        }else {
            LayoutBottomAddmoreBinding layoutBottomAddmoreBinding=DataBindingUtil.inflate(LayoutInflater
                    .from(parent.getContext()),R.layout.layout_bottom_addmore,parent,false);
            return new BottomHolder(layoutBottomAddmoreBinding);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_BOTTOM;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < getItemCount() - 1) {
            final BookViewHolder bookViewHolder = (BookViewHolder) holder;
            final Book book = bookList.get(position);
            bookViewHolder.itemBookBinding.setBook(book);
            bookViewHolder.itemBookBinding.executePendingBindings();
        } else {
            BottomHolder bottomHolder= (BottomHolder) holder;
            LayoutBottomAddmoreBinding layoutBottomAddmoreBinding= bottomHolder.layoutBottomAddmoreBinding;
            if(bottomStatus==STATUS_NOTHING){
                layoutBottomAddmoreBinding.root.setVisibility(View.GONE);
            }else if(bottomStatus==STATUS_LOADING){
                layoutBottomAddmoreBinding.root.setVisibility(View.VISIBLE);
                layoutBottomAddmoreBinding.progressBar.setVisibility(View.VISIBLE);
                layoutBottomAddmoreBinding.text.setText("加载中..");
            }else if(bottomStatus==STATUS_NOTHING_LOAD){
                layoutBottomAddmoreBinding.root.setVisibility(View.VISIBLE);
                layoutBottomAddmoreBinding.progressBar.setVisibility(View.GONE);
                layoutBottomAddmoreBinding.text.setText("已经到底了..");
            }
            layoutBottomAddmoreBinding.executePendingBindings();
        }


    }


    @Override
    public int getItemCount() {
        return bookList.size() + 1;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class BottomHolder extends RecyclerView.ViewHolder {
        LayoutBottomAddmoreBinding layoutBottomAddmoreBinding;
        public BottomHolder(LayoutBottomAddmoreBinding layoutBottomAddmoreBinding) {
            super(layoutBottomAddmoreBinding.getRoot());
            this.layoutBottomAddmoreBinding=layoutBottomAddmoreBinding;
        }
    }

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemBookBinding itemBookBinding;

        BookViewHolder(ItemBookBinding itemBookBinding) {
            super(itemBookBinding.getRoot());
            this.itemBookBinding=itemBookBinding;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(View v, int Position);
    }

}
