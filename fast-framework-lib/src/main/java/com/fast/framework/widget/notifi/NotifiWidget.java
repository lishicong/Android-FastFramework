package com.fast.framework.widget.notifi;

import com.fast.framework.R;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by lishicong on 2017/6/20.
 */

public class NotifiWidget extends BaseNotifi {

    public final static String ACTION_BUTTON = "com.fast.notifi.widget.ButtonClick";

    public final static String INTENT_BUTTONID_TAG = "FastNotifiIntentButtonId";
    /**
     * 上一首ID
     */
    public final static int BUTTON_PREV_ID = 1;
    /**
     * 播放ID
     */
    public final static int BUTTON_PALY_ID = 2;
    /**
     * 下一首ID
     */
    public final static int BUTTON_NEXT_ID = 3;

    private boolean isPlay = false;

    private String song;
    private String singer;

    public NotifiWidget(Context context, String ticker) {
        super(context, ticker);
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void show() {
        RemoteViews remoteViews = getRemoteView();
        mNotifiBuilder.setContent(remoteViews);
        showNotify(NOTIFI_ID_WIDGET);
    }

    public void palyOrPause() {
        isPlay = !isPlay;
        show();
    }

    private RemoteViews getRemoteView() {
        RemoteViews mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.fast_notifi_widget);
        mRemoteViews.setImageViewResource(R.id.fast_notifi_widget_icon, R.mipmap.ic_share_friends);
        //API3.0
        mRemoteViews.setTextViewText(R.id.fast_notifi_widget_song, song);
        mRemoteViews.setTextViewText(R.id.fast_notifi_widget_singer, singer);

        //
        if (isPlay) {
            mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.fast_notifi_pause);
        } else {
            mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.fast_notifi_play);
        }

        Intent buttonIntent = new Intent(ACTION_BUTTON);

        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PREV_ID);

        PendingIntent intent_prev = PendingIntent.getBroadcast(mContext, BUTTON_PREV_ID, buttonIntent,
                                                               PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_prev, intent_prev);

        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_paly = PendingIntent.getBroadcast(mContext, BUTTON_PALY_ID, buttonIntent,
                                                               PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_play, intent_paly);

        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(mContext, BUTTON_NEXT_ID, buttonIntent,
                                                               PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_next, intent_next);

        return mRemoteViews;
    }

}
