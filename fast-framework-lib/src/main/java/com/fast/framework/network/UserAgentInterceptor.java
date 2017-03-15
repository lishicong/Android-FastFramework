/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import java.io.IOException;

import com.fast.framework.support.L;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lishicong on 2016/12/13.
 */

public final class UserAgentInterceptor implements Interceptor {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private final String userAgentHeaderValue;

    public UserAgentInterceptor(String userAgentHeaderValue) {
        this.userAgentHeaderValue = userAgentHeaderValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder httpUrlBuilder = originalHttpUrl.newBuilder();
        if (NetworkParams.params != null && NetworkParams.params.size() > 0) {
            for (String key : NetworkParams.params.keySet()) {
                httpUrlBuilder.addQueryParameter(key, NetworkParams.params.get(key));
            }
        }

        HttpUrl url = httpUrlBuilder.build();
        L.i("http request url:" + url.toString());

        Request.Builder requestBuilder = original.newBuilder().url(url);

        Request request = requestBuilder.removeHeader(USER_AGENT_HEADER_NAME).addHeader(USER_AGENT_HEADER_NAME,
                                                                                        userAgentHeaderValue).build();

        return chain.proceed(request);
    }
}