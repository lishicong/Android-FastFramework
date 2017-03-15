/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

/**
 * Created by lishicong on 2016/12/13.
 */

public class NetworkException extends Exception {

    public static final int NETWORK_CODE = -1;

    private int errorCode = NETWORK_CODE;

    public NetworkException(String message) {
        this(message, NETWORK_CODE);
    }

    public NetworkException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
