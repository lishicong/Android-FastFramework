/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.fast.framework.network;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import com.fast.framework.support.L;
import com.fast.framework.util.MD5Util;

import android.net.Uri;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 自定义网络代理拦截
 * <p>
 * Created by lishicong on 2016/12/13.
 */

public final class UserAgentInterceptor implements Interceptor {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private static final boolean NETWORK_LOG = true;
    private final String userAgentHeaderValue;

    public UserAgentInterceptor(String userAgentHeaderValue) {
        this.userAgentHeaderValue = userAgentHeaderValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        Request request = addCommonParameter(original);

        Response response = chain.proceed(request);

        // L.i("http response data:" + response.body().string());
        return response;
    }

    /**
     * 重新创建Request，并添加公共参数
     *
     * @param original
     */
    private Request addCommonParameter(Request original) {

        String method = original.method();

        if (method.equalsIgnoreCase("POST")) {
            return addPostCommonParams(original);
        }
        return addGetCommonParams(original);
    }

    /**
     * GET
     *
     * @param original
     *
     * @return
     */
    private Request addGetCommonParams(Request original) {

        HttpUrl originalHttpUrl = original.url();

        // 添加公共参数
        HttpUrl.Builder httpUrlBuilder = originalHttpUrl.newBuilder();
        if (NetworkParams.params != null && NetworkParams.params.size() > 0) {
            for (String key : NetworkParams.params.keySet()) {
                httpUrlBuilder.addQueryParameter(key, NetworkParams.params.get(key));
            }
        }

        // 参数加密
        TreeMap<String, String> treeMap = new TreeMap<>();
        Uri uri = Uri.parse(httpUrlBuilder.build().url().toString());
        Set<String> params = uri.getQueryParameterNames();
        for (String param : params) {
            treeMap.put(param, uri.getQueryParameter(param));
        }

        // 添加签名
        httpUrlBuilder.addQueryParameter("sign", encrypt(treeMap));

        Request.Builder requestBuilder = original.newBuilder().url(httpUrlBuilder.build());

        Request request = requestBuilder.removeHeader(USER_AGENT_HEADER_NAME).addHeader(USER_AGENT_HEADER_NAME,
                                                                                        userAgentHeaderValue).build();

        if (NETWORK_LOG) {
            L.i("HTTP GET REQUEST URL:" + request.url());
        }

        return request;
    }

    /**
     * POST
     *
     * @param original
     *
     * @return
     */
    private Request addPostCommonParams(Request original) {

        Request.Builder requestBuilder = original.newBuilder();

        if (original.body() instanceof FormBody) {

            FormBody.Builder formBodyBuiler = new FormBody.Builder();

            FormBody originalFormBody = (FormBody) original.body();

            TreeMap<String, String> treeMap = new TreeMap<>();
            // 添加原有请求参数
            for (int i = 0; i < originalFormBody.size(); i++) {
                formBodyBuiler.addEncoded(originalFormBody.encodedName(i), originalFormBody.encodedValue(i));
                treeMap.put(originalFormBody.encodedName(i), originalFormBody.encodedValue(i));
            }

            // 添加公共请求参数
            if (NetworkParams.params != null && NetworkParams.params.size() > 0) {
                for (String key : NetworkParams.params.keySet()) {
                    formBodyBuiler.addEncoded(key, NetworkParams.params.get(key));
                    treeMap.put(key, NetworkParams.params.get(key));
                }
            }

            // 添加签名
            formBodyBuiler.add("sign", encrypt(treeMap));

            FormBody formBody = formBodyBuiler.build();

            // 输出带参数的log
            if (NETWORK_LOG) {
                String params = getPostParamString(formBody);
                L.i("HTTP POST REQUEST URL:" + original.url() + params);
            }

            requestBuilder.method(original.method(), formBody);
        }

        requestBuilder.removeHeader(USER_AGENT_HEADER_NAME).addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                .build();

        Request request = requestBuilder.build();

        return request;
    }

    private String getPostParamString(FormBody formBody) {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < formBody.size(); i++) {
            if (i == 0) {
                sbf.append("?");
            } else {
                sbf.append("&");
            }

            sbf.append(formBody.encodedName(i));
            sbf.append("=");
            sbf.append(formBody.encodedValue(i));
        }
        return sbf.toString();
    }

    /**
     * 对参数加密
     *
     * @param treeMap
     *
     * @return
     */
    private String encrypt(TreeMap<String, String> treeMap) {
        StringBuffer sbf = new StringBuffer();
        for (String key : treeMap.keySet()) {
            sbf.append(key).append("=").append(treeMap.get(key));
        }
        sbf.append("key-secrect");
        String encrypt = MD5Util.getMD5String(sbf.toString());
        return encrypt;
    }
}