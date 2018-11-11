package com.ouyang.freebook.ui.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.ItemCategoryCardBinding;
import com.ouyang.freebook.modle.bean.Category;
import com.ouyang.freebook.ui.activity.CategoryBookListActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> list;

    public CategoryAdapter(List<Category> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryCardBinding itemCategoryCardBinding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                , R.layout.item_category_card,parent,false
        );
        return new CategoryViewHolder(itemCategoryCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        holder.itemCategoryCardBinding.setCategory(list.get(position));
        holder.itemCategoryCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),CategoryBookListActivity.class);
                intent.putExtra("category",holder.itemCategoryCardBinding.getCategory());
                v.getContext().startActivity(intent);
            }
        });
        holder.itemCategoryCardBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        ItemCategoryCardBinding itemCategoryCardBinding;
        public CategoryViewHolder(ItemCategoryCardBinding itemCategoryCardBinding) {
            super(itemCategoryCardBinding.getRoot());
            this.itemCategoryCardBinding=itemCategoryCardBinding;
        }
    }
}
