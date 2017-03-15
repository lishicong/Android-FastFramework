/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.ff.example.data.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fast.framework.base.BaseBean;
import com.fast.framework.view.videolist.VideoListItem;

/**
 * Created by lishicong on 2016/12/16.
 */

public class NewsBean extends BaseBean {

    public int size;
    public List<Order> list = new ArrayList<>();

    public static class Order extends VideoListItem {
        @JSONField(name = "order_no")
        public String orderNo;
        @JSONField(name = "status_id")
        public int statusId;
        @JSONField(name = "status_desc")
        public String statusDesc;
        @JSONField(name = "start_place")
        public String startPlace;
        @JSONField(name = "end_place")
        public String endPlace;
        @JSONField(name = "create_time")
        public long createTime;

    }

}
