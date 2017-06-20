/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.fragment;

import com.fast.framework.FastFragment;
import com.fast.framework.data.RecyclerViewAdapter;
import com.fast.framework.network.DialogSubscriber;
import com.fast.framework.network.NetworkException;
import com.fast.framework.network.RetrofitFactory;
import com.fast.framework.support.FT;
import com.fast.framework.support.L;
import com.fast.framework.view.videolist.widget.PullToRefreshRecyclerView;
import com.ff.example.R;
import com.ff.example.data.adapter.NewsAdapter;
import com.ff.example.data.bean.NewsBean;
import com.ff.example.data.service.NewsAPI;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishicong on 2016/12/8.
 */

public class HomePageFragment extends FastFragment implements PullToRefreshRecyclerView.RecyclerViewListener {

    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerViewAdapter = new NewsAdapter(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f_homepage, container, false);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.recycler_view);
        mPullToRefreshRecyclerView.setRecyclerViewAdapter(mRecyclerViewAdapter);
        mPullToRefreshRecyclerView.setRecyclerViewListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPullToRefreshRecyclerView.onActivityCreated();
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
            }

            @Override
            public void onItemLongClick(View v, int position) {
            }
        });
    }

    @Override
    public void recyclerRefreshData() {
        requestData();
    }

    @Override
    public void recyclerMoreData() {
    }

    @Override
    public void onRecyclerScrolled(RecyclerView recyclerView, int dx, int dy) {
    }

    @Override
    public void onScrollBottom() {

    }

    private void requestData() {
        RetrofitFactory.createService(NewsAPI.class).execute().compose(RetrofitFactory.<NewsBean>handleResult())
                .subscribe(new DialogSubscriber<NewsBean>(mActivity) {

                    @Override
                    protected void onSuccess(NewsBean bean) {

                        for (NewsBean.Order order : bean.list) {
                            order.setVideoUrl(url);
                            order.setCoverUrl(purl1);
                        }

                        mRecyclerViewAdapter.refreshObjects(bean.list);
                        mPullToRefreshRecyclerView.getPtrFrame().refreshComplete();
                    }

                    @Override
                    protected void onFailure(NetworkException ne) {
                        FT.showToast(mActivity, ne.getMessage());
                        mPullToRefreshRecyclerView.getPtrFrame().refreshComplete();
                    }
                });
    }

    private static final String url = "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";
    private static final String purl1 = "http://img10.3lian.com/sc6/show02/67/27/03.jpg";

}
