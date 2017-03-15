/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.base;

import com.fast.framework.R;
import com.fast.framework.style.SystemBarTintManager;
import com.fast.framework.style.skin.SkinnableActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lishicong on 2016/12/2.
 */

public abstract class BaseActivity extends SkinnableActivity {

    // 是否沉浸模式
    protected boolean isImmersive = true;
    // 默认状态栏颜色
    protected int statusbarColor = R.color.fast_framework_black_20000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (isImmersive) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(true);
            }

            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(statusbarColor);
            tintManager.setNavigationBarTintResource(R.color.fast_framework_transparent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
