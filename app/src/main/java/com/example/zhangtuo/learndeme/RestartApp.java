package com.example.zhangtuo.learndeme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhangtuo on 2017/10/11.
 *
 * 重启应用
 */

public class RestartApp {
    public  void restart(MainActivity context){
        Context applicationContext = context.getApplicationContext();
        context.finish();
        Intent intent = new Intent(applicationContext, MainActivity.class);
        PendingIntent restartIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 清理数据，重启程序
        AlarmManager mgr = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, restartIntent); // 1秒钟后重启应用
    }
}
