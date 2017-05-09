/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import java.util.HashMap;
import java.util.Map;

import com.fast.framework.support.UUID;

/**
 * Created by lishicong on 2016/12/16.
 */

public class NetworkParams {

    public static Map<String, String> params = new HashMap<>();

    static {
        params.put("cuid", UUID.getInstance().getUUID());
        params.put("device_from", "1");
        params.put("sv", "1.0.0");
        params.put("channel", "radio");
        params.put("vehicle_channel", "radio");
    }

}
