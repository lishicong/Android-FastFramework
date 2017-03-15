/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example;

import com.baidu.map.sdk.LocationService;
import com.baidu.mapapi.SDKInitializer;
import com.fast.framework.FastApplication;
import com.fast.framework.network.NetworkConfig;
import com.fast.framework.network.RetrofitFactory;

/**
 * Created by lishicong on 2016/12/5.
 */

public class ExApplication extends FastApplication {

    /**
     * 百度地图定位服务
     */
    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initNetwork();
        this.initBaidumap();
    }

    /**
     * 初使化网络服务器配置
     */
    private void initNetwork() {
        final NetworkConfig.Builder config = new NetworkConfig.Builder();
        config.setHttpServerBaseUrl("http://36.110.198.56");
        NetworkConfig.getInstance().init(config);
        RetrofitFactory.init(this, "agent");
    }

    /**
     * 初使化baidu map sdk.
     */
    private void initBaidumap() {
        // 初使化定位服务
        locationService = new LocationService(this.getApplicationContext());
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this.getApplicationContext());
    }
}
