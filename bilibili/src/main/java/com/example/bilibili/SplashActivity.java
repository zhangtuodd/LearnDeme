package com.example.bilibili;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;


/**
 * Created by zhangtuo on 2017/11/28.
 */

public class SplashActivity extends Activity {


//    Unbinder unbinder;
//    @BindView(R2.id.splash_default_iv)
    ImageView imageView;
//    @OnClick(R2.id.splash_default_iv)
    void click(View view){
        Toast.makeText(this,"butterknife生效",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.splash_default_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,BaiduMapActivity.class));
            }
        });
//        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}
