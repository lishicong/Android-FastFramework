/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;

/**
 * java 反射工具类
 * <p>
 * Created by lishicong on 2017/3/13.
 */

public class Reflect {

    private Reflect() {
    }

    public static Class getClassByStr(String str) {
        Class clazz = null;
        try {
            clazz = Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Object getObjectByStr(Context context, String str) {
        Class clazz = getClassByStr(str);
        Object obj = null;
        if (clazz != null) {
            Constructor<?> cons[] = clazz.getConstructors();
            for (Constructor con : cons) {
                Class<?> clazzs[] = con.getParameterTypes();
                if (clazzs.length == 1) {
                    try {
                        obj = con.newInstance(context);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

}
