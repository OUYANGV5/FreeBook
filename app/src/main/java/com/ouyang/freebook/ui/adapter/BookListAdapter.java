package com.ouyang.freebook.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ouyang.freebook.R;
import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.bean.Book;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class BookListAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_BOTTOM = 1;

    private List<Book> bookList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private int bottomStatus;//0,1,2  无,加载中,加载完
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
        if (viewType == TYPE_ITEM)
            return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
        else
            return new BottomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bottom_addmore, parent, false));
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
            BookViewHolder bookViewHolder = (BookViewHolder) holder;
            Book book = bookList.get(position);
            Glide.with(holder.itemView).load(RequestConfig.URL_IMG_BOOK_BASE + book.getImg()).into(bookViewHolder.bookImg);
            bookViewHolder.bookTitle.setText(book.getName());
            bookViewHolder.ratingBar.setRating((float) (book.getScore() / 2));
            bookViewHolder.ratingNum.setText(book.getScore() + "");
            bookViewHolder.bookInfo.setText(book.getCName() + "\t|\t" + book.getAuthor());
            bookViewHolder.bookDetails.setText(book.getDesc().replaceAll("\\s", ""));
        } else {
            BottomHolder bottomHolder= (BottomHolder) holder;
            if(bottomStatus==0){
                bottomHolder.linearLayout.setVisibility(View.GONE);
            }else if(bottomStatus==1){
                bottomHolder.linearLayout.setVisibility(View.VISIBLE);
                bottomHolder.progressBar.setVisibility(View.VISIBLE);
                bottomHolder.text.setText("加载中..");
            }else if(bottomStatus==2){
                bottomHolder.linearLayout.setVisibility(View.VISIBLE);
                bottomHolder.progressBar.setVisibility(View.GONE);
                bottomHolder.text.setText("已全部加载完");
            }
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
        @BindView((R.id.root))
        LinearLayout linearLayout;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.text)
        TextView text;
        public BottomHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.book_img)
        ImageView bookImg;
        @BindView(R.id.book_title)
        TextView bookTitle;
        @BindView(R.id.ratingBar)
        MaterialRatingBar ratingBar;
        @BindView(R.id.ratingNum)
        TextView ratingNum;
        @BindView(R.id.bookInfo)
        TextView bookInfo;
        @BindView(R.id.bookDetails)
        TextView bookDetails;

        BookViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
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
