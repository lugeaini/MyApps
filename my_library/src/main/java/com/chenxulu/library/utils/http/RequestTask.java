package com.chenxulu.library.utils.http;

import android.app.Activity;
import android.app.Dialog;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xulu on 16/7/26.
 */
public abstract class RequestTask {
    private static OkHttpClient okHttpClient;

    private String url;
    private RequestParams requestParams;
    private Activity activity;
    private Dialog dialog;

    public RequestTask(Activity activity, String url, RequestParams requestParams) {
        this.activity = activity;
        this.url = url;
        this.requestParams = requestParams;
    }

    public void execute() {
        dialog.show();
        if (okHttpClient == null) {
            synchronized (RequestTask.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }

        Request request = new Request.Builder().post(requestParams.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    protected abstract void onSuccess(JSONObject jsonObject);

    protected void onFailure(String error) {

    }
}
