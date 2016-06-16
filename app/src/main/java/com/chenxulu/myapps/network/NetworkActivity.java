package com.chenxulu.myapps.network;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenxulu.myapps.R;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        allNetwork();
    }

    private void allNetwork() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networks = manager.getAllNetworkInfo();
        for (int i = 0; i < networks.length; i++) {
            NetworkInfo info = networks[i];
            System.out.println("name:" + info.getTypeName() + ",state:" + info.getState());
        }

        System.out.println("---");

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            System.out.println("name:" + networkInfo.getTypeName() + ",state:" + networkInfo.getState());
        }
    }
}
