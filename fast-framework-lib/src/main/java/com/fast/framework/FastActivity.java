/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework;

import com.fast.framework.base.BaseActivity;
import com.fast.framework.base.EmptyFragment;

import android.os.Bundle;

/**
 * 界面管理框架的activity基类，所有使用动态界面管理的activity都需要继承此类。
 * <p>
 * Created by lishicong on 2016/12/2.
 */

public class FastActivity extends BaseActivity {

    private FastFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);

        mFragment = FastManager.dispatch(this, getIntent(), EmptyFragment.class);
    }

    @Override
    public void onBackPressed() {
        if (mFragment != null) {
            mFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
