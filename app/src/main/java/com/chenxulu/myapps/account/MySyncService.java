package com.chenxulu.myapps.account;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;

import com.chenxulu.library.service.BaseLibraryService;

/**
 * Define a Service that returns an IBinder for the
 * sync adapter class, allowing the sync adapter framework to call
 * onPerformSync().
 */
public class MySyncService extends BaseLibraryService {
    // Storage for an instance of the sync adapter
    private static MySyncAdapter mySyncAdapter = null;
    // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();

    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (mySyncAdapter == null) {
                mySyncAdapter = new MySyncAdapter(getApplicationContext(), true);
            }
        }
    }

    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     */
    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Get the object that allows external processes to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter constructors call super()
         */
        return mySyncAdapter.getSyncAdapterBinder();
    }

    /**
     * Handle the transfer of data between a server and an
     * app, using the Android sync adapter framework.
     */
    class MySyncAdapter extends AbstractThreadedSyncAdapter {
        // Global variables
        // Define a variable to contain a content resolver instance
        ContentResolver mContentResolver;

        /**
         * Set up the sync adapter
         */
        public MySyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
            /**
             * If your app uses a content resolver, get an instance of it
             * from the incoming Context
             */
            mContentResolver = context.getContentResolver();
        }

        /**
         * Set up the sync adapter. This form of the
         * constructor maintains compatibility with Android 3.0
         * and later platform versions
         */
        public MySyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
            super(context, autoInitialize, allowParallelSyncs);
            /**
             * If your app uses a content resolver, get an instance of it
             * from the incoming Context
             */
            mContentResolver = context.getContentResolver();
        }

        /**
         * Specify the code you want to run in the sync adapter. The entire
         * sync adapter runs in a background thread, so you don't have to set
         * up your own background processing.
         */
        @Override
        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
            //Put the data transfer code here.
            System.out.println("onPerformSync");
        }
    }
}
