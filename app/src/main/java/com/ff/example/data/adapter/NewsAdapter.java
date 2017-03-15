/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.data.adapter;

import java.util.HashSet;
import java.util.Set;

import com.fast.framework.data.RecyclerViewAdapter;
import com.fast.framework.style.skin.view.Skinnable;
import com.fast.framework.style.skin.view.SkinnableRelativeLayout;
import com.fast.framework.view.videolist.VideoViewHolder;
//import com.fast.framework.view.videolist.widget.PullToRefreshVideoView;
import com.ff.example.R;
import com.ff.example.data.bean.NewsBean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lishicong on 2016/12/16.
 */

public class NewsAdapter extends RecyclerViewAdapter<NewsBean.Order> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_list_item, parent, false),
                mSkinnableViewsCache);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        NewsHolder videoViewHolder = (NewsHolder) holder;
        videoViewHolder.bind(position, getObjects().get(position));
    }

    @Override
    public int getItemCount() {
        return getObjects().size();
    }

    static class NewsHolder extends VideoViewHolder {

        //public PullToRefreshVideoView videoView;

        public RelativeLayout mRelativeLayout;
        public ImageView imgIV;
        public TextView titleTV;
        public TextView hotTV;
        public TextView overdueTV;

        public NewsHolder(View view, Set<View> skinnableViewsCache) {

            super(view);
            super.mSkinnableViewsCache = skinnableViewsCache;
            //videoView = (PullToRefreshVideoView) view.findViewById(R.id.home_1_list_item_video_view);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.home_page_list_item_bg);
            imgIV = (ImageView) view.findViewById(R.id.home_page_list_item_image);
            titleTV = (TextView) view.findViewById(R.id.home_page_list_item_title);
            hotTV = (TextView) view.findViewById(R.id.home_page_list_item_hot);
            overdueTV = (TextView) view.findViewById(R.id.home_page_list_item_overdue);
            //initVideoView(videoView);
        }

        public void bind(int position, NewsBean.Order item) {
            super.bind(position, item);
            titleTV.setText(String.format("我是标题 %s", position));

            mSkinnableViewsCache.add(mRelativeLayout);
            for (View view : mSkinnableViewsCache) {
                if (view instanceof Skinnable) {
                    ((Skinnable) view).applyDayNight();
                }
            }
        }
    }

}
