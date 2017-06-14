package com.chenxulu.myapps.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chenxulu.myapps.R;

public class MyAccountActivity extends AppCompatActivity {

    
    // Constants
    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.chenxulu.myapps.account";

    // Sync interval constants
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
    public static final long SYNC_INTERVAL = SYNC_INTERVAL_IN_MINUTES * SECONDS_PER_MINUTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSyncAccount(getBaseContext());
            }
        });

        findViewById(R.id.fab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountList();
            }
        });

        findViewById(R.id.fab3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSync();
            }
        });
    }

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account createSyncAccount(Context context) {
        // Create the account type and default account
        String name = context.getString(R.string.app_name);
        String type = context.getPackageName();
        Account newAccount = new Account(name, type);
        // Get an instance of the Android account manager
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
            //开启同步, 并设置同步周期
            ContentResolver.setIsSyncable(newAccount, AUTHORITY, 1);
            ContentResolver.setSyncAutomatically(newAccount, AUTHORITY, true);
            ContentResolver.addPeriodicSync(newAccount, AUTHORITY, Bundle.EMPTY, SYNC_INTERVAL);
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }

    /**
     * show account list
     */
    private void accountList() {
        try {
            AccountManager manager = AccountManager.get(this);
            Account[] accounts = manager.getAccounts();

            for (int i = 0; i < accounts.length; i++) {
                System.out.println("name:" + accounts[i].name + ",type:" + accounts[i].type);
            }
        } catch (Exception e) {

        }

        Toast.makeText(this, "get account list", Toast.LENGTH_LONG).show();
    }

    /**
     * request sync
     */
    private void requestSync() {
        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        String name = getString(R.string.app_name);
        String type = getPackageName();
        Account account = new Account(name, type);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        ContentResolver.requestSync(account, AUTHORITY, settingsBundle);

        Toast.makeText(this, "request sync", Toast.LENGTH_LONG).show();
    }

}
