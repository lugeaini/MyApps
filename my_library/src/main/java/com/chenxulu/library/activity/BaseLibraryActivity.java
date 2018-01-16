package com.chenxulu.library.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * log life cycle
 */
public class BaseLibraryActivity extends AppCompatActivity {
    private boolean lifecycle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(String.format("onCreate(savedInstanceState=%s)", savedInstanceState));
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        log("onAttachedToWindow()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log(String.format("onResume(NativeHeapAllocatedSize:%d, NativeHeapFreeSize:%d)", android.os.Debug.getNativeHeapAllocatedSize(), android.os.Debug.getNativeHeapFreeSize()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        log("onNewIntent()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        log(String.format("onConfigurationChanged(newConfig=%s)", newConfig));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        log(String.format("onActivityResult(requestCode=%d, resultCode=%d, data=%s)", requestCode, resultCode, data));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log("onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState()");
    }

    /**
     * 是否打印生命周期
     *
     * @param state
     */
    protected void logLifeCycle(boolean state) {
        this.lifecycle = state;
    }

    /**
     * 本类中log输出函数，tag为类名
     */
    protected void log(String message) {
        if (lifecycle)
            Log.d(getClass().getSimpleName(), message);
    }

}
