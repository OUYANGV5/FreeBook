package com.ouyang.freebook.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookrackFragment extends BaseFragment {

    public BookrackFragment() {
        // Required empty public constructor
    }

    @Override
    public void init() {

    }

    @Override
    public void onVisibleAgain() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookrack, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
