package com.example.zhangtuo.learndeme;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by zhangtuo on 2017/11/28.
 */

public class App extends Application {

    public static App mInstance;

    @Override
    public void onCreate() {
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
