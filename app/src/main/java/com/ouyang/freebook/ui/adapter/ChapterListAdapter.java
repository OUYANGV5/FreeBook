package com.ouyang.freebook.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemChapterBinding;
import com.ouyang.freebook.modle.bean.BookChapter;

import java.util.List;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ChapterViewHolder> {
    private List<BookChapter> list;
    private OnItemClickListener onItemClickListener;
    private int selectItem;
    public ChapterListAdapter(List<BookChapter> list) {
        this.list = list;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<BookChapter> getList() {
        return list;
    }

    public void setList(List<BookChapter> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChapterBinding itemChapterBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_chapter,parent,false);
        ChapterViewHolder chapterViewHolder=new ChapterViewHolder(itemChapterBinding);
        return chapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, final int position) {
        holder.itemChapterBinding.setChapter(list.get(position));
        holder.itemChapterBinding.executePendingBindings();
        holder.itemView.setSelected(selectItem==position);
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                    selectItem=position;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder{
        ItemChapterBinding itemChapterBinding;
        public ChapterViewHolder(ItemChapterBinding itemChapterBinding) {
            super(itemChapterBinding.getRoot());
            this.itemChapterBinding=itemChapterBinding;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
