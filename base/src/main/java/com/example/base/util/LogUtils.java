package com.example.base.util;

/**
 * logUtils
 *
 * @author zhangtuo
 * @date 2018/3/22
 */

import android.text.TextUtils;
import android.util.Log;


public class LogUtils {

    public static final String TAG = "logUtils";

    public static void d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.i(TAG, msg);
        }
    }
}