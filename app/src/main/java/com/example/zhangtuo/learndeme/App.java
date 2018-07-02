package com.example.zhangtuo.learndeme;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;
import com.tencent.smtt.sdk.QbSdk;

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
        initX5();
    }

    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean isX5Core) {
                //true表示x5内核加载成功
                Log.d("app", " onViewInitFinished is " + isX5Core);
            }

            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }
        };
        //x5初始化（sdk内部考虑了冷启动耗时，有异步优化）
        QbSdk.initX5Environment(getApplicationContext(), cb);
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
