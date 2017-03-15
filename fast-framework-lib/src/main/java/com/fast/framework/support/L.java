/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.support;

import java.util.Locale;

import android.util.Log;

/**
 * 日志输出类
 * <p>
 * Created by lishicong on 2016/12/5.
 */

public class L {

    private static final String TAG = "FastFramework";
    private static final String TAG_CONTENT_PRINT = "%s:%s.%s:%d";
    private static final String TAG_LOG = " FFLOG |||(~_~).zZ ------>>> ";
    // log trace 开关
    private static final boolean LOG_TRACE_ENABLED = true;

    private static final int VERBOSE = 5;
    private static final int DEBUG = 4;
    private static final int INFO = 3;
    private static final int WARN = 2;
    private static final int ERROR = 1;

    // 输出的log级别
    private static final int LEVEL = VERBOSE;

    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void v(String msg) {

        if (LEVEL >= VERBOSE) {
            Log.v(TAG, getContent(getCurrentStackTraceElement()) + TAG_LOG + msg);
        }
    }

    public static void d(String msg) {

        if (LEVEL >= DEBUG) {
            Log.d(TAG, getContent(getCurrentStackTraceElement()) + TAG_LOG + msg);
        }
    }

    public static void i(String msg) {

        if (LEVEL >= INFO) {
            Log.i(TAG, getContent(getCurrentStackTraceElement()) + TAG_LOG + msg);
        }
    }

    public static void w(String msg) {

        if (LEVEL >= WARN) {
            Log.w(TAG, getContent(getCurrentStackTraceElement()) + TAG_LOG + msg);
        }
    }

    public static void e(String msg) {

        if (LEVEL >= ERROR) {
            Log.e(TAG, getContent(getCurrentStackTraceElement()) + TAG_LOG + msg);
        }
    }

    /**
     * 获取方法的调用栈信息
     *
     * @return
     */
    private static StackTraceElement getCurrentStackTraceElement() {

        return Thread.currentThread().getStackTrace()[4];

    }

    /**
     * 格式化调用栈信息
     *
     * @param trace
     *
     * @return
     */
    private static String getContent(StackTraceElement trace) {

        //        return String.format(Locale.ENGLISH, TAG_CONTENT_PRINT, TAG, trace.getClassName(), trace
        // .getMethodName(),
        //                             trace.getLineNumber());
        return String.format(Locale.ENGLISH, "%s:%d", trace.getClassName(), trace.getLineNumber());
    }

    /**
     * 输出一个debug级别的log
     */
    public static void trace() {

        if (LOG_TRACE_ENABLED) {
            Log.d(TAG, getContent(getCurrentStackTraceElement()));
        }
    }

    /**
     * 打印当前调用栈信息
     */
    public static void traceStack() {

        String tag = TAG;
        int priority = Log.ERROR;

        if (LOG_TRACE_ENABLED) {

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            Log.println(priority, tag, stackTrace[4].toString());
            StringBuilder str = new StringBuilder();
            String prevClass = null;

            for (int i = 5; i < stackTrace.length; i++) {
                String className = stackTrace[i].getFileName();
                int idx = className.indexOf(".java");
                if (idx >= 0) {
                    className = className.substring(0, idx);
                }
                if (prevClass == null || !prevClass.equals(className)) {

                    str.append(className.substring(0, idx));

                }
                prevClass = className;
                str.append(".").append(stackTrace[i].getMethodName()).append(":").append(stackTrace[i].getLineNumber())
                        .append("->");
            }

            Log.println(priority, tag, str.toString());
        }
    }

}
