/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example;

import com.fast.framework.network.NetworkParams;

import android.content.Context;

/**
 * Created by lishicong on 2016/12/16.
 */

public class SettingConfig {

    /**
     * 获取网络请求的公共参数
     *
     * @return
     */
    public static void updateNetworkCommonParams(Context context) {
        NetworkParams.params.put("device_from", "1");
        NetworkParams.params.put("sv", "1.0.0");
        NetworkParams.params.put("bduss", "RxcE1nNzRzZklMMjVkfmZXNk5xTUp2Q0FzanBBbH4yWkhZZU9TVmJPSER"
                +
                "-bHhZSVFBQUFBJCQAAAAAAAAAAAEAAACQcygsuPG48c7XODg4ODkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMNxNVjDcTVYfl");
        NetworkParams.params.put("cuid", "1870909A73BDAA1C6C48910336FC2742|316639720151368");
        NetworkParams.params.put("cur_lng", "116.278228");
        NetworkParams.params.put("cur_lat", "40.049648");
    }
}
