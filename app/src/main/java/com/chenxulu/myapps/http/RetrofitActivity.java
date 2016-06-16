package com.chenxulu.myapps.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenxulu.myapps.R;

import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }


    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();
    }

    public interface TestApi {

    }
}
