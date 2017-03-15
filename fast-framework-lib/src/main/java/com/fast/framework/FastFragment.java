/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework;

import com.fast.framework.base.BaseFragment;

/**
 * 界面管理框架的fragment基类，所有使用动态界面管理的fragment都需要继承此类。
 * <p>
 * 注意：界面管理框架fragment下面的二级fragment不继承此类
 * <p>
 * Created by lishicong on 2016/12/2.
 */

public abstract class FastFragment extends BaseFragment {

    public void onBackPressed() {
        mActivity.finish();
    }

}
