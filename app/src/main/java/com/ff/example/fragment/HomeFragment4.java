/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.baidu.map.sdk.LocationFactory;
import com.baidu.map.sdk.LocationListener;
import com.baidu.map.sdk.LocationManager;
import com.fast.framework.base.BaseFragment;
import com.fast.framework.support.L;
import com.ff.example.ExApplication;
import com.ff.example.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomeFragment4 extends BaseFragment {

    private LocationManager mLocationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home_4, container, false);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = new LocationManager(((ExApplication) mActivity.getApplication()).locationService);
        mLocationManager.setLocationListener(new LocationListener() {

            @Override
            public void onReceiveLocation() {
                L.i("receive location latitude:" + LocationFactory.getInstance().getLatitude());
                L.i("receive location longitude:" + LocationFactory.getInstance().getLongitude());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mLocationManager.onStartLocation();
    }

    @Override
    public void onStop() {
        mLocationManager.onStopLocation();
        super.onStop();
    }
}
