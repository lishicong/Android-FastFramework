/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.support.permissionslib;

/**
 * request permission callback
 * Created by caik on 2017/2/17.
 */

public interface OnRequestPermissionsCallBack{

    void onGrant();

    void onDenied(String permission);
}
