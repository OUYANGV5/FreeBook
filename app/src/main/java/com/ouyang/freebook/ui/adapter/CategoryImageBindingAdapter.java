package com.ouyang.freebook.ui.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ouyang.freebook.R;

public class CategoryImageBindingAdapter {
    @BindingAdapter("categoryImage")
    public static void setImage(ImageView image,String id){
        int imgId= R.drawable.empty_book_img;
        switch (id){
            case "1":
                imgId=R.drawable.classify1;
                break;
            case "2":
                imgId=R.drawable.classify2;
                break;
            case "3":
                imgId=R.drawable.classify3;
                break;
            case "4":
                imgId=R.drawable.classify4;
                break;
            case "5":
                imgId=R.drawable.classify5;
                break;
            case "6":
                imgId=R.drawable.classify6;
                break;
            case "7":
                imgId=R.drawable.classify7;
                break;
            case "66":
                imgId=R.drawable.classify66;
                break;
        }
        image.setImageResource(imgId);
    }
}
