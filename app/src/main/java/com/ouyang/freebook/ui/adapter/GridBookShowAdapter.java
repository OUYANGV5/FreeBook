package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemBookshowGridBinding;
import com.ouyang.freebook.modle.bean.Book;

import java.util.List;

public class GridBookShowAdapter extends RecyclerView.Adapter<GridBookShowAdapter.GridBookViewHolder> {
    private List<Book> bookList;

    public GridBookShowAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public GridBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookshowGridBinding binding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                , R.layout.item_bookshow_grid,parent,false
        );
        return new GridBookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GridBookViewHolder holder, int position) {
        holder.binding.setBook(bookList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class GridBookViewHolder extends RecyclerView.ViewHolder{
        private ItemBookshowGridBinding binding;
        public GridBookViewHolder(ItemBookshowGridBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
