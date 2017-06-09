package com.chenxulu.library.utils.http;

import okhttp3.FormBody;

/**
 * Created by xulu on 16/7/26.
 */
public class RequestParams {
    private FormBody.Builder mBuilder;

    public RequestParams() {
        mBuilder = new FormBody.Builder();
    }

    /**
     * add request param
     *
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        mBuilder.addEncoded(key,value);
    }

    public FormBody build(){
        return mBuilder.build();
    }
}
