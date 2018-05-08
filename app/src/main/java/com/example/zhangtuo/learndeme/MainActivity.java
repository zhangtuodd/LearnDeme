package com.example.zhangtuo.learndeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import proxy.IStarBehavior;
import proxy.DynamicProxy;
import proxy.IStarBehaviorPlus;
import proxy.Star;
import ui.PaintSurfaceView;
import ui.ScaleRulerActivity;
import ui.WritePadDialog;
import ui.audiochartview.AudioChartActivity;
import ui.customview.CustomViewActivity;
import ui.immerse.ImmersedStatusActivity;
import ui.popupwindow.DeletePupView;
import ui.rectangle.rectangleView;
import utils.SizeUtils;
import utils.ThreadUtils;

import static com.example.base.config.Router.BILIBILI_SPLASH;

public class MainActivity extends BaseActivity {

    DeletePupView pupView;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageView iv;


    private ImageView mIVSign;
    private TextView mTVSign;
    private Bitmap mSignBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, ScaleRulerActivity.class));
    }

//        mIVSign = (ImageView) findViewById(R.id.iv_sign);
//        mTVSign = (TextView) findViewById(R.id.tv_sign);
//
//        mTVSign.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                WritePadDialog mWritePadDialog = new WritePadDialog(
//                        MainActivity.this, new WritePadDialog.WriteDialogListener() {
//
//                    @Override
//                    public void onPaintDone(Object object) {
//                        mSignBitmap = (Bitmap) object;
////                        createSignFile();
//                        mIVSign.setImageBitmap(mSignBitmap);
//                        mTVSign.setVisibility(View.GONE);
//                    }
//                });
//
//                mWritePadDialog.show();
//            }
//        });
//    }
//
//    //创建签名文件
//    private void createSignFile() {
//        ByteArrayOutputStream baos = null;
//        FileOutputStream fos = null;
//        String path = null;
//        File file = null;
//        try {
//            path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
//            file = new File(path);
//            fos = new FileOutputStream(file);
//            baos = new ByteArrayOutputStream();
//            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
//            mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] b = baos.toByteArray();
//            if (b != null) {
//                fos.write(b);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//                if (baos != null) {
//                    baos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


//        final PaintSurfaceView view = (PaintSurfaceView) findViewById(R.id.view);
//
//
//        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                view.clear();
//            }
//        });
//
//        iv = (ImageView) findViewById(R.id.img);
//        iv.setVisibility(View.GONE);
//        view.setVisibility(View.VISIBLE);
//        findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bitmap bitmap = view.sure(view);
//                if (bitmap == null) {
//                    Toast.makeText(MainActivity.this, "bitmap为null", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                view.setVisibility(View.GONE);
//                iv.setVisibility(View.VISIBLE);
//
//                iv.setImageBitmap(bitmap);
//                try {
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                    byte[] bytes = baos.toByteArray();
//                    baos.close();
//                    String imgBase64 = Base64.encodeToString(bytes, Base64.DEFAULT);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


//        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
//            }
//        });
//        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build(BILIBILI_SPLASH).navigation();
////                finish();
////                maoPao();
//                xuanZe();
//            }
//        });
//
//        rectangleView view = (rectangleView) findViewById(R.id.rect);
//        view.setColor(getResources().getColor(R.color.red));
































  /*      ARouter.getInstance().inject(this);
        SingleInstance instance = SingleInstance.getInstance();
//        startActivity(new Intent(this, LifeCyclerActivity.class));

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(BILIBILI_SPLASH).navigation();
//                finish();
//                maoPao();
                xuanZe();
            }
        });

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(MainActivity.this,"return",Toast.LENGTH_SHORT).show();
            }
        };

        //关于回调
        showProgress();
        ThreadUtils threadUtils = new ThreadUtils();
        threadUtils.setThreadListener(new ThreadUtils.ThreadListener() {
            @Override
            public void response() {
                SystemClock.sleep(2000);
                hideProgress();
            }
        });
        threadUtils.build(ThreadUtils.Type.CACHED);


        pupView = new DeletePupView(MainActivity.this, new DeletePupView.ClickListener() {
            @Override
            public void deleteListener() {
                Log.i("tag", "delete----------------");
            }

            @Override
            public void changeListener() {
                Log.i("tag", "change----------------");
            }
        });
        //线程中断
//        InterruptThread.test();


        //重启应用
      *//*  findViewById(R.id.restart).setOnClickListener(new View.CustomClickListener() {
            @Override
            public void onClick(View v) {
                RestartApp restartApp = new RestartApp();
                restartApp.restart(MainActivity.this);
            }
        });*//*


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

        //公共popupwindow
        findViewById(R.id.pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pupView != null) {
                    pupView.showAsDropDown(v, SizeUtils.dip2px(MainActivity.this, -15), 0, Gravity.RIGHT);
                }
            }
        });*/


    private void xuanZe() {
        int[] a = {3, 2, 5, 12, 65, 34, 8, 10, 23};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            Log.i("tag", "a[" + i + "]------" + a[i]);
        }
    }

    /**
     * 冒泡排序
     */
    private void maoPao() {
        int[] a = {3, 2, 5, 12, 65, 34, 8, 10, 23};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            Log.i("tag", "a[" + i + "]------" + a[i]);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (pupView != null && pupView.isShowing()) {
            pupView.dismiss();
            return true;
        }
        return super.onTouchEvent(event);
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
