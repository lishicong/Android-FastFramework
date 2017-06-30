/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.base.BaseFragment;
import com.fast.framework.support.L;
import com.fast.framework.widget.notifi.NotifiProgress;
import com.fast.framework.widget.notifi.NotifiWidget;
import com.ff.example.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomeFragment2 extends BaseFragment implements View.OnClickListener {

    private NotifiProgress nofiti;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    private NotifiWidget widget;

    private WidgetBroadcastReceiver mWidgetBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWidgetBroadcastReceiver = new WidgetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotifiWidget.ACTION_BUTTON);
        mActivity.registerReceiver(mWidgetBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(mWidgetBroadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_home_2, container, false);

        mButton1 = (Button) view.findViewById(R.id.notifi_download_start);
        mButton2 = (Button) view.findViewById(R.id.notifi_download_pause);
        mButton3 = (Button) view.findViewById(R.id.notifi_download_cancel);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        widget = new NotifiWidget(mActivity, "正在播放");
        widget.setSong("爱你一万年");
        widget.setSinger("刘德华");
        widget.show();

        //        NotifiProgress.Builder builder = new NotifiProgress.Builder();
        //        builder.setTitle("新版本升级包v1.1");
        //        builder.setDefautlStatus("准备下载");
        //        builder.setStartStatus("下载中");
        //        builder.setPauseStatus("已暂停");
        //        builder.setStopStatus("已停止");
        //        builder.setErrorStatus("下载错误");
        //        nofiti = new NotifiProgress(mActivity, "文件下载");
        //        nofiti.setBuilder(builder);
        //        nofiti.show();
    }

    @Override
    public void onClick(View view) {
        long id = view.getId();
        if (id == R.id.notifi_download_start) {
            nofiti.start();
            mHandler.sendEmptyMessage(NOTIFI_PROGRESS_REFRESH);
        } else if (id == R.id.notifi_download_pause) {
            nofiti.pause();
            mHandler.removeCallbacksAndMessages(null);
        } else if (id == R.id.notifi_download_cancel) {
            nofiti.stop();
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    int i = 0;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NOTIFI_PROGRESS_REFRESH:
                    i++;
                    nofiti.refresh(i);
                    mHandler.sendEmptyMessageDelayed(NOTIFI_PROGRESS_REFRESH, 500);
                    break;
                default:
                    break;
            }
        }
    };

    static final int NOTIFI_PROGRESS_REFRESH = 1;

    /////////
    private class WidgetBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(NotifiWidget.ACTION_BUTTON)) {
                int buttonId = intent.getIntExtra(NotifiWidget.INTENT_BUTTONID_TAG, 0);
                switch (buttonId) {
                    case NotifiWidget.BUTTON_LAYOUT_ID:
                        L.i("BUTTON_LAYOUT_ID");
                        widget.clear();
                        break;
                    case NotifiWidget.BUTTON_PREV_ID:
                        L.i("BUTTON_PREV_ID");
                        break;
                    case NotifiWidget.BUTTON_PALY_ID:
                        widget.palyOrPause();
                        L.i("BUTTON_PALY_ID");
                        break;
                    case NotifiWidget.BUTTON_NEXT_ID:
                        L.i("BUTTON_NEXT_ID");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
