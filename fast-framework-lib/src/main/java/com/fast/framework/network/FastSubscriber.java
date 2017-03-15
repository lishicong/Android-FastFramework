/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import com.fast.framework.util.NetworkUtil;

import android.content.Context;
import rx.Subscriber;

/**
 * Created by lishicong on 2016/12/16.
 */

public abstract class FastSubscriber<T> extends Subscriber<T> {

    protected Context mContext;

    public FastSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetworkUtil.isConnected(mContext)) {
            _onError(NetworkException.NETWORK_CODE, "网络不可用");
        } else if (e instanceof NetworkException) {
            _onError(((NetworkException) e).getErrorCode(), e.getMessage());
        } else {
            _onError(NetworkException.NETWORK_CODE, e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(int errorCode, String msg);

}