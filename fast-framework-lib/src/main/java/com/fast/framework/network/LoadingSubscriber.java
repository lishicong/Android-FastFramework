/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import android.content.Context;

/**
 * Created by lishicong on 2016/12/16.
 */

public abstract class LoadingSubscriber<T> extends FastSubscriber<T> {

    public LoadingSubscriber(Context mContext) {
        super(mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoadingView();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        hideLoadingView();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        hideLoadingView();
    }

    protected abstract void showLoadingView();

    protected abstract void hideLoadingView();

}

