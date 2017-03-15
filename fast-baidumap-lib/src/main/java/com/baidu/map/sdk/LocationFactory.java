/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.map.sdk;

/**
 * 位置信息单例类，保存有关位置信息数据
 * <p>
 * Created by lishicong on 2016/12/21.
 */

public class LocationFactory {

    private static LocationFactory instance;

    private LocationInfo locationInfo;

    private LocationFactory() {
        locationInfo = new LocationInfo();
    }

    public static LocationFactory getInstance() {
        if (instance == null) {
            synchronized (LocationFactory.class) {
                if (instance == null) {
                    instance = new LocationFactory();
                }
            }
        }
        return instance;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLatitude(double latitude) {
        locationInfo.latitude = latitude;
    }

    public double getLatitude() {
        return locationInfo.latitude;
    }

    public void setLongitude(double longitude) {
        locationInfo.longitude = longitude;
    }

    public double getLongitude() {
        return locationInfo.longitude;
    }

    public void setAddrStr(String addrStr) {
        locationInfo.addrStr = addrStr;
    }

    public String getAddrStr() {
        return locationInfo.addrStr;
    }
}
