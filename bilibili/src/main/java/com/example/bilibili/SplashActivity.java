package com.example.bilibili;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.base.config.Router.BILIBILI_SPLASH;

/**
 * Created by zhangtuo on 2017/11/28.
 */
@Route(path = BILIBILI_SPLASH)
public class SplashActivity extends Activity {

    @BindView(R2.id.splash_default_iv)
    ImageView imageView;
    @OnClick(R2.id.splash_default_iv)
    void click(){
        Toast.makeText(this,"butterknife生效",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

    }
}
