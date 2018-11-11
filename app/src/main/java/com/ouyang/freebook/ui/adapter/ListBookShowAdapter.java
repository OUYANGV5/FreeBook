package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemBookshowListBinding;
import com.ouyang.freebook.modle.bean.Book;

import java.util.List;

public class ListBookShowAdapter extends RecyclerView.Adapter<ListBookShowAdapter.ListBookViewHolder> {
    private List<Book> bookList;

    public ListBookShowAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ListBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookshowListBinding binding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                , R.layout.item_bookshow_list,parent,false
        );
        return new ListBookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookViewHolder holder, int position) {
        holder.binding.setBook(bookList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ListBookViewHolder extends RecyclerView.ViewHolder{
        private ItemBookshowListBinding binding;
        public ListBookViewHolder(ItemBookshowListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
