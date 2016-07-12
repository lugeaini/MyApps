package com.chenxulu.myapps.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.chenxulu.library.service.BaseLibraryService;

public class MyAccountService extends BaseLibraryService {

    private MyAuthenticator myAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        myAuthenticator = new MyAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myAuthenticator.getIBinder();
    }

    class MyAuthenticator extends AbstractAccountAuthenticator {

        public MyAuthenticator(Context context) {
            super(context);
            System.out.println("MyAuthenticator()");
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            System.out.println("editProperties");
            return null;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
            System.out.println("addAccount");
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
            System.out.println("confirmCredentials");
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            System.out.println("getAuthToken");
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            System.out.println("getAuthTokenLabel");
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            System.out.println("updateCredentials");
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
            System.out.println("hasFeatures");
            return null;
        }
    }
}
