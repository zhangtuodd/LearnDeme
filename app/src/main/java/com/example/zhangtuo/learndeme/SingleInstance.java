package com.example.zhangtuo.learndeme;

import android.util.Log;

/**
 * Created by zhangtuo on 2017/12/21.
 */

public class SingleInstance {
    private static final SingleInstance INSTANCE = new SingleInstance();

    //单利里面创建对象时的私有构造也是回执行的
    private SingleInstance() {
        Log.i("tag", "run struct---");
    }

    public static SingleInstance getInstance() {
        return INSTANCE;
    }
}
