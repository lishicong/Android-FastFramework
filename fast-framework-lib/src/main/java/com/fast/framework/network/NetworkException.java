/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

/**
 * Created by lishicong on 2016/12/13.
 */

public class NetworkException extends Exception {

    private int errorCode;

    public NetworkException(String message) {
        this(message, ExceptionHandle.SERVER_ERROR_CODE);
    }

    public NetworkException(int errorCode) {
        this(ExceptionHandle.UNKOWN_ERROR, errorCode);
    }

    public NetworkException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
