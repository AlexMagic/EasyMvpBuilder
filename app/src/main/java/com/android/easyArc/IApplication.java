package com.android.easyArc;

import android.app.Application;

import com.android.mvp.CommonContext;

/**
 * Created by Alex on 17/3/9.
 */

public class IApplication extends Application {

    private static CommonContext commonContext;

    @Override
    public void onCreate() {
        super.onCreate();
        commonContext = new CommonContext(this);
    }
}
