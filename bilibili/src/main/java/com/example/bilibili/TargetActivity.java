package com.example.bilibili;

import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.config.Router;

import client.cloudy.com.annotation.BindPath;
import z_router.MyRouter;

@Route(path = Router.BILIBILI_TARGET)
@BindPath(path = Router.BILIBILI_TARGET)
public class TargetActivity extends AppCompatActivity {


    @Autowired
    int age;

    @Autowired
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        ARouter.getInstance().inject(this);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(Router.ACFUN_ACT)
                        .navigation();
//                MyRouter.getInstance().jumpActivity(Router.ACFUN_ACT, null);
            }
        });


        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {


            }
        });
    }
}
