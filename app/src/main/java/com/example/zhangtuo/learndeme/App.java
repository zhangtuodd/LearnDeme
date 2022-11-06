package com.example.zhangtuo.learndeme;

import android.app.Application;
import android.content.Context;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;
import androidx.multidex.MultiDex;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;
import com.example.base.BaseApp;
//import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import mvvm.BindAdapters;
import mvvm.ViewModelBinder;
import widget.ProgressDialog;
import z_router.MyRouter;

/**
 * Created by zhangtuo on 2017/11/28.
 */

public class App extends BaseApp {

    public static App mInstance;
    public static Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    public Context getApplicationContext() {
        return this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
//        SDKInitializer.initialize(this);
        super.onCreate();
        mInstance = this;
        init();
        initX5();
        UMConfigure.setLogEnabled(true);
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(false);
        UMConfigure.init(
                this,
                "5b7a5a1cb27b0a666a00036a",
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);


    }

    /*
注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口
（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
*/


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


        // 判断当前进程是否为LeakCanary进程，该进程运行一个HeapAnalyzerService服务
        // 如果不是，则初始化LeakCanary进程
//        if (! LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this);
//        }
    }

    private void init() {
        ARouter.openLog();
        ARouter.openDebug();// 开启日志
        ARouter.init(mInstance); // 尽可能早，推荐在Application中初始化

        //mvvm初始化binder
        BindAdapters.setBinder(BindAdapters.viewModelBinder);


        //自定义路由框架
        MyRouter.getInstance().init(this);
    }

    public static App getInstance() {
        return mInstance;
    }


}
