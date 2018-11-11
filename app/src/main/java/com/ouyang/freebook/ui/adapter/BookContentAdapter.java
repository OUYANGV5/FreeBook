package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.LayoutBookPageBinding;
import com.ouyang.freebook.modle.bean.BookContent;

import java.util.List;

public class BookContentAdapter extends RecyclerView.Adapter<BookContentAdapter.ContentViewHolder> {
    private List<BookContent> list;
    private OnBindListener onBindListener;
    private View.OnClickListener onClickListener;
    public BookContentAdapter(List<BookContent> list) {
        this.list = list;
    }

    public OnBindListener getOnBindListener() {
        return onBindListener;
    }

    public void setOnBindListener(OnBindListener onBindListener) {
        this.onBindListener = onBindListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutBookPageBinding layoutBookPageBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.layout_book_page,parent,false);
        return new ContentViewHolder(layoutBookPageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        holder.layoutBookPageBindingl.setContent(list.get(position));
        if(onBindListener!=null)
            onBindListener.onBindViewHolder(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ContentViewHolder extends RecyclerView.ViewHolder{
        private LayoutBookPageBinding layoutBookPageBindingl;
        public ContentViewHolder(LayoutBookPageBinding layoutBookPageBinding) {
            super(layoutBookPageBinding.getRoot());
            this.layoutBookPageBindingl=layoutBookPageBinding;
            itemView.setOnClickListener(onClickListener);
        }
    }
    public interface OnBindListener{
        void onBindViewHolder(int position);
    }

}
