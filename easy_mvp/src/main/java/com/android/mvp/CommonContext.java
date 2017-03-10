package com.android.mvp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.android.mvp.utils.Log;

/**
 * Created by alex on 16-9-27.
 */

public class CommonContext {

    private static CommonContext sInstance;

    public static CommonContext getsInstance() {
        return sInstance;
    }

    private Context context;

    public CommonContext(Context context){
        sInstance = this;
        this.context = context;

    }

    public Context getContext() {
        return context;
    }


}
