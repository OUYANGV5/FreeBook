package com.ouyang.freebook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class BaseFragment extends Fragment {
    private boolean isSetUserVisible;//判断是否调用过setUserVisibleHint方法
    private boolean isInit;
    private boolean isFirst;

    public BaseFragment() {
        isSetUserVisible=false;
        isInit=false;
        isFirst=false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!isInit){
            if(isFirst){
                init();
                isInit=true;
            }else if(!isSetUserVisible){
                init();
                isInit=true;
            }
        }

    }
    public abstract void init();

    public abstract void onVisibleAgain();
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isSetUserVisible){
            isSetUserVisible=true;
        }
        if(!isInit&&isVisibleToUser){
            if(getContext()!=null){
                init();
                isInit=true;
            }else {
                isFirst=true;
            }
        }
        if(isVisibleToUser&&getContext()!=null){
            onVisibleAgain();
        }
    }
}
