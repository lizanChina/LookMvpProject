package com.zlc.lookmvp.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by zlc on 2017/2/9.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application;
    }

}
