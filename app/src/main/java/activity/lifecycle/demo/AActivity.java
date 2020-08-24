package activity.lifecycle.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;

public class AActivity extends AppCompatActivity {

    private static final String TAG = "SActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        LogUtils.d(TAG,"AActivity.onCreate---------------");

        // 获取activity任务栈
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        LogUtils.d(TAG, "info---------------"+info);
    }

    

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG,"AActivity.onStart---------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG,"AActivity.onResume---------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG,"AActivity.onPause---------------");
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG,"AActivity.onStop---------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"AActivity.onDestroy---------------");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG,"AActivity.onRestart---------------");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(TAG,"AActivity.onConfigurationChanged---------------");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d(TAG,"AActivity.onNewIntent---------------");
    }




    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.d(TAG,"AActivity.onRestoreInstanceState---------------");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d(TAG,"AActivity.onSaveInstanceState---------------");
    }
}
