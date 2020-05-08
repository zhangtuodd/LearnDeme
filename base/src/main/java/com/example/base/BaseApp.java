package com.example.base;

import android.app.Application;


public class BaseApp extends Application {
    public static BaseApp mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
