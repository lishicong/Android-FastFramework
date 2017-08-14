/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework;

import com.fast.framework.base.BaseActivity;
import com.fast.framework.base.EmptyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

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
        // this.setContentView(R.layout.activity_fast);
        View view = View.inflate(this, R.layout.activity_fast, mBaseLayout);
        mFragment = FastManager.dispatch(this, getIntent(), EmptyFragment.class);
    }

    public FastFragment getFastFragment() {
        return mFragment;
    }

    @Override
    public void onBackPressed() {
        if (mFragment != null) {
            mFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mFragment.onKeyDown(keyCode, event)) {
            // undo
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onNewIntent(Intent intent) {
        mFragment.onNewIntent(intent);
    }

}
