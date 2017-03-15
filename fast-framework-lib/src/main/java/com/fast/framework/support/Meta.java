/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.support;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by lishicong on 2017/3/2.
 */

public class Meta {

    private Meta() {
    }

    /**
     * 获取meta字段
     *
     * @param con
     * @param name
     *
     * @return
     */
    public static String getMetaString(Context con, String name) {

        ApplicationInfo ai = null;

        try {
            ai = con.getPackageManager().getApplicationInfo(con.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Bundle bundle = ai.metaData;

        return bundle.getString(name);
    }

}
