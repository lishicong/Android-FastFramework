/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.util;

import com.fast.framework.support.L;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络状态相关工具类.
 * <p>
 * Created by lishicong on 2016/12/5.
 */

public class NetworkUtil {

    private NetworkUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     *
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        L.i("network is connected");
                        return true;
                    }
                }
            }
        }
        L.i("network is not connected");
        return false;
    }

    /**
     * 判断是否是wifi连接
     *
     * @param context
     *
     * @return
     */
    public static boolean isWifi(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
            return false;
        }
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 打开网络设置界面
     *
     * @param context
     */
    public static void openSetting(Context context) {

        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }
}
