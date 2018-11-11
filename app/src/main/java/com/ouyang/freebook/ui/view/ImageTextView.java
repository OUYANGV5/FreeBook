package com.ouyang.freebook.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;

import com.ouyang.freebook.R;

public class ImageTextView extends android.support.v7.widget.AppCompatTextView {
    private int imgWidth;
    private int imgHeight;
    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        setGravity(Gravity.CENTER);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView);
        imgWidth=typedArray.getDimensionPixelSize(R.styleable.ImageTextView_image_width,30);
        imgHeight=typedArray.getDimensionPixelSize(R.styleable.ImageTextView_image_height,30);
        Drawable[] drawable=getCompoundDrawables();
        setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
        typedArray.recycle();
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        setNewSizeDrawable(left);
        setNewSizeDrawable(top);
        setNewSizeDrawable(right);
        setNewSizeDrawable(bottom);
        super.setCompoundDrawables(left, top, right, bottom);
    }
    private Drawable setNewSizeDrawable(Drawable drawable){
        if(drawable!=null)
            drawable.setBounds(0,0,imgWidth,imgHeight);
        return drawable;
    }
}
