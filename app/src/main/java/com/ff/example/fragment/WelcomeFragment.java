/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.base.GuideFragment;
import com.fast.framework.style.guide.BasicPage;
import com.fast.framework.style.guide.GuideConfiguration;
import com.ff.example.R;

import android.os.Bundle;

/**
 * Created by lishicong on 2016/12/5.
 */

public class WelcomeFragment extends GuideFragment {

    public WelcomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected GuideConfiguration configuration() {
        return new GuideConfiguration.Builder(mActivity).defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultDescriptionTypefacePath("Montserrat-Bold.ttf")

                .page(new BasicPage(R.mipmap.guide_pic1, "Welcome", "欢迎使用Fast-Framework框架")
                              .background(R.color.fast_framework_white))

                .page(new BasicPage(R.mipmap.guide_pic2, "简介", "此框架提供了App的基础功能")
                              .background(R.color.fast_framework_white))

                .page(new BasicPage(R.mipmap.guide_pic3, "配置", "使用Android studio直接引入即可")
                              .background(R.color.fast_framework_white))

                .page(new BasicPage(R.mipmap.guide_pic4, "支持", "不断的升级改进，提供完好的技术支持")
                              .background(R.color.fast_framework_white))

                .swipeToDismiss(true).canSkip(false).exitAnimation(android.R.anim.fade_out).build();
    }

}
