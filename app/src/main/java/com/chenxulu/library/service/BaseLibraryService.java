package com.chenxulu.library.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class BaseLibraryService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(getClass().getSimpleName() + ":OnCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(getClass().getSimpleName() + ":onDestroy()");
    }
}
