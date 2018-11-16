package com.ouyang.freebook.ui.adapter;

import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemBookshelfBookBinding;
import com.ouyang.freebook.modle.litepal.BookBean;

import java.util.List;

public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.BookViewHolder> {
    private List<BookBean> list;
    private OnItemClickListener onItemClickListener;
    public BookShelfAdapter(List<BookBean> list) {
        this.list = list;
    }
    public void updateList(List<BookBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<BookBean> getList() {
        return list;
    }

    public void setList(List<BookBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookshelfBookBinding itemBookShelfBookBinding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_bookshelf_book,parent,false);
        return new BookViewHolder(itemBookShelfBookBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, final int position) {
        BookBean bookBean = list.get(position);
        holder.itemBookshelfBookBinding.setBookBean(bookBean);
        holder.itemBookshelfBookBinding.executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null)
                    onItemClickListener.onItemClick(v,position,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class BookViewHolder extends RecyclerView.ViewHolder{
        ItemBookshelfBookBinding itemBookshelfBookBinding;

        public ItemBookshelfBookBinding getItemBookshelfBookBinding() {
            return itemBookshelfBookBinding;
        }

        private boolean isDrag;
        public BookViewHolder(ItemBookshelfBookBinding itemBookshelfBookBinding) {
            super(itemBookshelfBookBinding.getRoot());
            this.itemBookshelfBookBinding=itemBookshelfBookBinding;
        }
        public void onDrag(){
            if(isDrag){
                return;
            }
            isDrag=true;
            scaleAnimator(1.0f,1.1f);
        }
        public void onRelease(){
            if(!isDrag){
                return;
            }
            isDrag=false;
            scaleAnimator(itemView.getScaleX(),1.0f);
        }
        public void scaleAnimator(float fromValue,float toValue){
            ValueAnimator animator=ValueAnimator.ofFloat(fromValue,toValue);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float value= (Float) animation.getAnimatedValue();
                    itemView.setScaleX(value);
                    itemView.setScaleY(value);
                }
            });
            animator.start();
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v, int position, BookViewHolder holder);
    }
}
