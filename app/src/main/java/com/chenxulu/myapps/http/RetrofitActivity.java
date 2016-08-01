package com.chenxulu.myapps.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenxulu.myapps.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }


    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .build();

        TestApi api = retrofit.create(TestApi.class);

        Call<String> call = api.list("xulu");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public interface TestApi {
        @GET("users/{user}/repos")
        Call<String> list(@Path("user") String user);


    }

}
