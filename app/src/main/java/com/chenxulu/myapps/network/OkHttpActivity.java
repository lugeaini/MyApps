package com.chenxulu.myapps.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenxulu.myapps.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
    }

    private void request() {
        OkHttpClient httpClient = new OkHttpClient();

        FormBody formBody = new FormBody.Builder().addEncoded("", "").build();

        Request request = new Request.Builder().post(formBody).build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                }
            }
        });
    }


    private void requestMultiFile() {
        OkHttpClient httpClient = new OkHttpClient();

        FormBody formBody = new FormBody.Builder().addEncoded("", "").build();

        MultipartBody body = new MultipartBody.Builder().addFormDataPart("", "").build();

        Request request = new Request.Builder().post(formBody).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                }
            }
        });
    }
}
