package com.example.bilibili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.config.Router;

import client.cloudy.com.annotation.BindPath;
import z_router.MyRouter;

@Route(path = Router.BILIBILI_TARGET)
@BindPath(path = Router.BILIBILI_TARGET)
public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance()
//                        .build(Router.ACFUN_ACT)
//                        .navigation();
                MyRouter.getInstance().jumpActivity(Router.ACFUN_ACT, null);
            }
        });


        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {


            }
        });
    }
}
