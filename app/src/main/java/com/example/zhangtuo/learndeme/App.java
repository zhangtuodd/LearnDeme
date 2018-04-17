package com.example.zhangtuo.learndeme;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;

import widget.ProgressDialog;

/**
 * Created by zhangtuo on 2017/11/28.
 */

public class App extends Application {

    public static App mInstance;
    public static Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        SDKInitializer.initialize(this);
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        ARouter.openLog();
        ARouter.openDebug();// 开启日志
        ARouter.init(mInstance); // 尽可能早，推荐在Application中初始化

    }

    public static App getInstance() {
        return mInstance;
    }


}
