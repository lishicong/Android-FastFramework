/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.base.BaseFragment;
import com.ff.example.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomeFragment2 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home_2, container, false);

        return view;
    }
}
