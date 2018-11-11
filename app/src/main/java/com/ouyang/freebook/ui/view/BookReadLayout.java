package com.ouyang.freebook.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@Deprecated
public class BookReadLayout extends FrameLayout {
    public static final int TOUCH_STATUS_PRESS_PRE=1;
    public static final int TOUCH_STATUS_PRESS_CENTER=2;
    public static final int TOUCH_STATUS_PRESS_NEXT=3;
    public static final int TOUCH_STATUS_SWIPE_PRE=4;
    public static final int TOUCH_STATUS_SWIPE_NEXT=5;
    private FrameLayout pre;
    private FrameLayout current;
    private FrameLayout next;
    private boolean isInit;
    private OnTouchListener onTouchListener;

    private float downX;//按下后记录x值
    private float swipeMaxLength;//水平滑动多长距离才算判定成功
    private View currentSwipeView;
    public BookReadLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BookReadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookReadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    public void init(){
        pre=new FrameLayout(getContext());
        pre.setBackgroundColor(Color.BLUE);
        current=new FrameLayout(getContext());
        current.setBackgroundColor(Color.YELLOW);
        next=new FrameLayout(getContext());
        next.setBackgroundColor(Color.RED);
        addView(next);
        addView(current);
        addView(pre);
    }
    public void addAll(View v1,View v2,View v3){
        pre.addView(v1);
        current.addView(v2);
        next.addView(v3);
        isInit=true;
    }

    public View getPre() {
        return pre;
    }

    public void setPre(FrameLayout pre) {
        this.pre.removeAllViews();
        this.pre.addView(current);
    }

    public FrameLayout getCurrent() {
        return current;
    }

    public void setCurrent(View current) {
        this.current.removeAllViews();
        this.current.addView(current);
    }

    public FrameLayout getNext() {
        return next;
    }

    public void setNext(FrameLayout next) {
        this.next.removeAllViews();
        this.next.addView(current);
    }

    public OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if(currentSwipeView==null){
                    if(event.getX()>downX){
                        currentSwipeView=pre;
                    }else{
                        currentSwipeView=current;
                    }
                }
                currentSwipeView.setTranslationX(event.getX()-downX);
                break;
            case MotionEvent.ACTION_UP:
                currentSwipeView=null;
                float upX=event.getX();
                float offset=upX-downX;
                int status=0;
                if(offset>0.1f*getWidth()){//将前一个滑出来
                    status=TOUCH_STATUS_SWIPE_PRE;
                }else if(offset<-0.1*getWidth()){//将后一个滑出来
                    status=TOUCH_STATUS_SWIPE_NEXT;
                }else if(offset==0) {//当只是按下时
                    if(upX<getWidth()*1.0f/3){//屏幕左边按下
                        status=TOUCH_STATUS_PRESS_PRE;
                    }else if(upX>getWidth()*2.0f/3){//屏幕右边按下
                        status=TOUCH_STATUS_PRESS_NEXT;
                    }else {//屏幕中间按下
                        status=TOUCH_STATUS_PRESS_CENTER;
                    }
                }
                if(onTouchListener!=null) {
                    switch (status) {
                        case TOUCH_STATUS_PRESS_PRE:
                        case TOUCH_STATUS_SWIPE_PRE:
                            onTouchListener.onTouchPre(this);
                            break;
                        case TOUCH_STATUS_PRESS_CENTER:
                            onTouchListener.onTouchCenter(this);
                            break;
                        case TOUCH_STATUS_PRESS_NEXT:
                        case TOUCH_STATUS_SWIPE_NEXT:
                            onTouchListener.onTouchNext(this);
                            break;
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed, left, top, right, bottom);
        next.layout(left,top,right,bottom);
        current.layout(left,top,right,bottom);
        pre.layout(-right,top,left,bottom);
    }

    public interface OnTouchListener{
        void onTouchPre(BookReadLayout layout);
        void onTouchNext(BookReadLayout layout);
        void onTouchCenter(BookReadLayout layout);
    }
}
