package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ouyang.freebook.modle.RequestConfig;

import jp.wasabeef.glide.transformations.BlurTransformation;
/*
    根据图片名字获取图片和高斯模糊背景
 */
public class ImageBindingAdapter {
    @BindingAdapter(value = "imgName")
    public static void bindImageUrl(ImageView imageView,String imgName){
        Glide.with(imageView).load(RequestConfig.URL_IMG_BOOK_BASE+imgName).into(imageView);
    }
    @BindingAdapter("blurBackground")
    public static void bindImageToBackground(View v,String imgName){
        Glide.with(v)
                .load(RequestConfig.URL_IMG_BOOK_BASE+imgName)
                .apply(new RequestOptions().optionalTransform(new BlurTransformation(360)))
                .into(new CustomViewTarget<View ,Drawable>(v) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            this.view.setBackgroundDrawable(resource);
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
