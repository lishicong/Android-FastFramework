/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.FastFragment;
import com.fast.framework.base.BaseFragment;
import com.fast.framework.support.FTE;
import com.fast.framework.view.FastTitlebarView;
import com.fast.framework.widget.FastRadioGroup;
import com.ff.example.R;
import com.ff.example.ScanActivity;
import com.ff.example.widget.HomeBottomDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/7.
 */

public class HomeFragment extends FastFragment implements View.OnClickListener {

    private FastTitlebarView mTitleBarView;

    private FTE mFTE;

    private BaseFragment mFragment1 = new HomeFragment1();
    private BaseFragment mFragment2 = new HomeFragment2();
    private BaseFragment mFragment3 = new HomeFragment3();
    private BaseFragment mFragment4 = new HomeFragment4();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home, container, false);

        mTitleBarView = (FastTitlebarView) view.findViewById(R.id.fast_home_titlebar_view);

        mFTE = new FTE(mActivity, R.id.main_pager);

        FastRadioGroup mRadioGroup = (FastRadioGroup) view.findViewById(R.id.f_home_bottom_radio_group);
        mRadioGroup.setOnCheckedChangeListener(new FastRadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(FastRadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.f_home_bottom_radio_1:
                        mFTE.switchContent(mFragment1);
                        break;
                    case R.id.f_home_bottom_radio_2:
                        mFTE.switchContent(mFragment2);
                        break;
                    case R.id.f_home_bottom_radio_3:
                        mFTE.switchContent(mFragment3);
                        break;
                    case R.id.f_home_bottom_radio_4:
//                        mFTE.switchContent(mFragment4);
                        HomeBottomDialog dialog = new HomeBottomDialog();
                        dialog.show(getChildFragmentManager(), HomeBottomDialog.class.getSimpleName());
                        break;
                    default:
                        break;
                }
            }
        });

        mFTE.switchContent(mFragment1);

        mTitleBarView.mImageViewBack.setImageResource(R.mipmap.zxing_icon_scan_white);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTitleBarView.mImageViewBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ScanActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
