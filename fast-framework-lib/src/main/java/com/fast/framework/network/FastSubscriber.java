/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import com.fast.framework.support.L;
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
            onFailure(NetworkException.NETWORK_CODE, "请联接网络");
        } else if (e instanceof NetworkException) {
            onFailure(((NetworkException) e).getErrorCode(), e.getMessage());
        } else {
            onFailure(NetworkException.NETWORK_CODE, e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(int errorCode, String msg);

}