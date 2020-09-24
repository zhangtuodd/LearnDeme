package com.example.zhangtuo.learndeme;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zhangtuo on 2018/3/1.
 */

public class LifeCyclerActivity extends Activity {
    private static final String TAG = "ActivityTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "---onCreate()---");
//        int a = 10/0;
//        final Dialog cameraDialog = new Dialog(this);//不导入V4
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
//        cameraDialog.setContentView(view);
//        cameraDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "---onStart()---");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "---onRestart()---");
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i = 2 / 0;
        Log.d(TAG, "---onResume()---");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "---onPause()---");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "---onStop()---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "---onDestroy()---");
    }

    //当指定了android:configChanges="orientation"后,方向改变时onConfigurationChanged被调用,并且activity不再销毁重建
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT://竖屏
                Log.i(TAG,"竖屏");
                break;
            case Configuration.ORIENTATION_LANDSCAPE://横屏
                Log.i(TAG,"横屏");
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "---onSaveInstanceState()---");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "---onRestoreInstanceState()---");
    }
}
