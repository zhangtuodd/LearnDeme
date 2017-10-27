package com.example.zhangtuo.learndeme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import proxy.IStarBehavior;
import proxy.DynamicProxy;
import proxy.IStarBehaviorPlus;
import proxy.Star;
import ui.audiochartview.AudioChartActivity;
import ui.customview.CustomViewActivity;
import ui.immerse.ImmersedStatusActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //线程中断
//        InterruptThread.test();


        //重启应用
      /*  findViewById(R.id.restart).setOnClickListener(new View.CustomClickListener() {
            @Override
            public void onClick(View v) {
                RestartApp restartApp = new RestartApp();
                restartApp.restart(MainActivity.this);
            }
        });*/


//        agent();

        //沉浸式状态栏
        findViewById(R.id.immersed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImmersedStatusActivity.class));
            }
        });

        //自定义仪表盘
        findViewById(R.id.custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
            }
        });

        //自定义音频跳动条
        findViewById(R.id.audio_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AudioChartActivity.class));
            }
        });

    }


    /**
     * 主体只负责处理自己的事如演电影。代理负责处理一些前提条件如片酬，满足则通知主体，因此代理必须持有主体
     * <p>
     * 加深理解：被代理类 Star 只需要完成自己的功能，不用因为业务逻辑而频繁修改代码
     * ，取而代之的是用 Proxy 来做中间人，由它来代替 Star 完成一些业务操作。
     */
    private void agent() {
        //静态代理,
//        Star star = new Star("huangbo");
//        StaticProxy staticProxy = new StaticProxy(star);
//        staticProxy.movieShow(3000000);
//        staticProxy.tvShow(5);

        //动态代理
        Star star1 = new Star("huangbo");
        DynamicProxy movingProxy = new DynamicProxy(star1);
        IStarBehavior proxy = (IStarBehavior) movingProxy.getProxy();
        proxy.movieShow(3000000);
        proxy.tvShow(5);

        IStarBehaviorPlus proxy1 = (IStarBehaviorPlus) movingProxy.getProxy();
        proxy1.sing(10);


    }


}
