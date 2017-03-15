/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.FastFragment;
import com.fast.framework.style.skin.SkinnableActivity;
import com.fast.framework.support.L;
import com.ff.example.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomeFragment3 extends FastFragment implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        L.i(" ================= onCreate3 savedInstanceState:" + savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home_3, container, false);

        view.findViewById(R.id.btn_change).setOnClickListener(this);
        view.findViewById(R.id.btn_change2).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_change:
//                mActivity.setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//            case R.id.btn_change2:
//                mActivity.setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//        }

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO: {
                ((SkinnableActivity) mActivity).setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                // Night mode is not active, we're in day time
                break;
            }
            case Configuration.UI_MODE_NIGHT_YES: {
                ((SkinnableActivity) mActivity).setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                // Night mode is active, we're at night!
                break;
            }
            case Configuration.UI_MODE_NIGHT_UNDEFINED: {
                // We don't know what mode we're in, assume notnight
            }
        }
    }
}
