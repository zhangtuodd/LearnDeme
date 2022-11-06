package com.example.zhangtuo.learndeme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/11
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, WebActivity.class));
//                finish();
//            }
//        }, 2000);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
