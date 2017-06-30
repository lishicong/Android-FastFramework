/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.FastFragment;
import com.fast.framework.support.FT;
import com.fast.framework.support.L;
import com.fast.framework.support.UUID;
import com.fast.framework.util.DensityUtil;
import com.fast.framework.widget.FastViewPager;
import com.fast.framework.widget.TabPageIndicator;
import com.ff.example.R;
import com.ff.example.SettingConfig;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomeFragment1 extends FastFragment {

    /**
     * Tab标题
     */
    private static final String[] TITLE =
            new String[] {"头条", "房产", "另一面", "女人", "财经", "数码", "情感", "科技", "今天", "又收", "回复"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        L.i(UUID.getInstance().getUUID());
        SettingConfig.updateNetworkCommonParams(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home_1, container, false);

        //实例化ViewPager， 然后给ViewPager设置Adapter
        FastViewPager pager = (FastViewPager) view.findViewById(R.id.f_home_pager);
        pager.setCanScroll(false);
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager());
        pager.setAdapter(adapter);

        //实例化TabPageIndicator，然后与ViewPager绑在一起（核心步骤）
        TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.f_home_indicator);
        indicator.setViewPager(pager);

        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_NOSAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#00000000"));// 设置分割线的颜色
        indicator.setDividerPadding((int) DensityUtil.dp2px(mActivity, 48));
        indicator.setIndicatorColorResource(R.color.fast_framework_global);// 设置底部导航线的颜色
        indicator.setTextColorSelected(mActivity.getResources().getColor(R.color.fast_framework_global));// 设置tab标题选中的颜色
        indicator.setTextColor(mActivity.getResources().getColor(R.color.fast_framework_f_minor));// 设置tab标题未被选中的颜色
        indicator.setTextSize((int) DensityUtil.sp2px(mActivity, 13));// 设置字体大小
        //如果要设置监听ViewPager中包含的Fragment的改变(滑动切换页面)
        // ，使用OnPageChangeListener为它指定一个监听器，那么不能像之前那样直接设置在ViewPager上了，而要设置在Indicator上，
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                FT.showToast(mActivity, TITLE[arg0]);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        return view;
    }

    /**
     * 定义ViewPager的适配器
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new HomePageFragment();
            Bundle args = new Bundle();
            args.putString("arg", TITLE[position]);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }

}
