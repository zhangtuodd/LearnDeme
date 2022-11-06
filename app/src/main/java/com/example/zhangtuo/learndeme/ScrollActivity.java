package com.example.zhangtuo.learndeme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.config.Router;
import com.example.base.util.LogUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import activity.FlowLayoutActivity;
import activity.ScaleRulerActivity;
import dagger.demo.Car;
import dagger.demo.DaggerMainComponent;
import dagger.demo.MainComponent;
import proxy.DynamicProxy;
import proxy.IStarBehavior;
import proxy.IStarBehaviorPlus;
import proxy.Star;
import takepic.recordvideo.save.db.CustomCameraActivity;
import ui.CircleView;
import ui.CommonDialog;
import ui.MasterBlockView;
import ui.OnTouchView;
import ui.popupwindow.DeletePupView;

//@Xml(layouts = "activity_main")
public class ScrollActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_layout);
    }

}
