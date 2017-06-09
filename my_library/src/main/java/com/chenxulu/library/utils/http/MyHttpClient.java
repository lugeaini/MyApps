package com.chenxulu.library.utils.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xulu on 16/7/26.
 */
public class MyHttpClient {
    private OkHttpClient okHttpClient;

    private static MyHttpClient myHttpClient;

    private MyHttpClient() {
        okHttpClient = new OkHttpClient();
    }

    public static MyHttpClient getInstance() {
        if (myHttpClient == null) {
            synchronized (MyHttpClient.class) {
                if (myHttpClient == null) {
                    myHttpClient = new MyHttpClient();
                }
            }
        }
        return myHttpClient;
    }

    public void request(String url, RequestParams params, final RequestCallback callback) {
        Request request = new Request.Builder().post(params.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }


}
