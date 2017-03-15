/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.data.service;

import com.fast.framework.network.NetworkModel;
import com.ff.example.data.bean.NewsBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lishicong on 2016/12/16.
 */

public interface NewsAPI {

    @POST("/order/driver/list")
    Observable<NetworkModel<NewsBean>> execute();
}
