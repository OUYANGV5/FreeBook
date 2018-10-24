package com.ouyang.freebook.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private List<Book> bookList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

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

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        Glide.with(holder.itemView).load(RequestConfig.URL_IMG_BOOK_BASE + book.getImg()).into(holder.bookImg);
        holder.bookTitle.setText(book.getName());
        holder.ratingBar.setRating((float) (book.getScore()/2));
        holder.ratingNum.setText(book.getScore()+"");
        holder.bookInfo.setText(book.getCName()+"\t|\t"+book.getAuthor());
        holder.bookDetails.setText(book.getDesc().replaceAll("\\s",""));
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
