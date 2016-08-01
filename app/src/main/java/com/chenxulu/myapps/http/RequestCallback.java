package com.chenxulu.myapps.http;

import org.json.JSONObject;

/**
 * Created by xulu on 16/7/26.
 */
public interface RequestCallback {
    void onSuccess(JSONObject jsonObject);

    void onFailure(String error);
}
