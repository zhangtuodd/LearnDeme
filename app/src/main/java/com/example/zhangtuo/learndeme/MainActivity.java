package com.example.zhangtuo.learndeme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

//import mvvm.OneActivity;
import activity.TestCacheActivity;
import camera_video_record.RecordActivity;
import camera_video_record.cameraDemo01.CameraTakePictureActivity;
import camera_video_record.cameraDemo01.Record2Activity;
import camera_video_record.cameraDemo01.RecordingActivity;
import mvvm.activity.MainMActivity;
import proxy.IStarBehavior;
import proxy.DynamicProxy;
import proxy.IStarBehaviorPlus;
import proxy.Star;
import takepic.recordvideo.save.db.CustomCameraActivity;
import takepic.recordvideo.save.db.TakePicRecordActivity;
import ui.CommonDialog;
import ui.CycleMoveActivity;
import ui.HostInfo;
import ui.MasterBlockView;
import ui.customview.CustomViewActivity;
import ui.popupwindow.DeletePupView;

public class MainActivity extends BaseActivity {

    DeletePupView pupView;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageView iv;

    private Button recordVideo;
    private ImageView mIVSign;
    private TextView mTVSign;
    private Bitmap mSignBitmap;
    int screenWidth;
    int perHeight;//每个块的高度
    int perWidth;
    CommonDialog dialog;
    ImageView imageView;
    RelativeLayout relativeLayout;


    private void checkPerm() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            coLaunch();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO}, 101);
        }
    }

    private void coLaunch() {
//        startActivity(new Intent(this, TakePicRecordActivity.class));
        startActivity(new Intent(this, CustomCameraActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    coLaunch();

                } else {
                    Toast.makeText(this, "Permissions Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private MasterBlockView masterBlockView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        recordVideo = findViewById(R.id.recordVideo);
        masterBlockView = findViewById(R.id.master_view);
        HostInfo hostInfo = new HostInfo();
        //赶紧切换一下吧现在央视频APP里登录的，不是你刚刚的%s，赶紧切换一下吧现在央视频APP里登录的，不是你刚刚的%s，赶紧切换一下吧
        hostInfo.content = "公告内容不一样时才更新跑马灯公告内容公告1";//不一"+ new Random().nextInt(100)
        hostInfo.avatarImg = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
        masterBlockView.setData(hostInfo, new MasterBlockView.VisibleListener() {
            @Override
            public void visibleType(boolean b) {
                if(b){
                    masterBlockView.setVisibility(View.VISIBLE);
                }
            }
        });
        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPerm();
            }
        });

//        relativeLayout = findViewById(R.id.rl);
//
//        TextView tv1 = new TextView(this);
//        tv1.setText("111");
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
//        params.addRule(RelativeLayout.ALIGN_PARENT_END,RelativeLayout.TRUE);
//        params.setMargins(20,20,10,10);
//        tv1.setLayoutParams(params);
//        relativeLayout.addView(tv1);
//
//
//        TextView tv2 = new TextView(this);
//        tv2.setText("000");
//        relativeLayout.addView(tv2);
//        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params2.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
//        params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
//        params2.setMargins(50,50,0,0);
//        tv2.setLayoutParams(params2);


//        startActivity(new Intent(this, CustomViewActivity.class));
//        startActivity(new Intent(this, CycleMoveActivity.class));
//        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new CommonDialog(MainActivity.this, "确认删除吗？", "确认", "取消", new CommonDialog.ActionListener() {
//                    @Override
//                    public void clickLeft() {
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inJustDecodeBounds = false;
//                        options.inPreferredConfig = Bitmap.Config.RGB_565;
//                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic, options);
//                        Log.d("logWrapperImageView", "width=" + bitmap.getWidth() + ",height=" + bitmap.getHeight() + ",size=" + bitmap.getByteCount());
//                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void clickRight() {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });
//        findViewById(R.id.webView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MobclickAgent.onEvent(MainActivity.this,"home");
//                Intent intent = new Intent(MainActivity.this, FlowLayoutActivity.class);
//                startActivity(intent);
//            }
//        });
//        LinearLayout layout = (LinearLayout) findViewById(R.id.ceshi);
//
//        Log.i("chushu", " --- >>> " + 5 / 7);
//        Log.i("chushu", " --- >>> " + 15 / 7);
//        Log.i("chushu", " --- >>> " + 5 % 7);
//        Log.i("chushu", " --- >>> " + 15 % 7);
//        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        screenWidth = wm.getDefaultDisplay().getWidth();
//        perWidth = screenWidth / 4;
//        perHeight = dp2px(this, 30);
//        TextView textView = new TextView(this);
//        textView.setText("50");
//        textView.setBackgroundResource(R.color.bangumi_index_yellow_bg);
//        textView.setGravity(Gravity.CENTER);
//        textView.setLayoutParams(new ViewGroup.LayoutParams(perWidth, perHeight));
//        layout.addView(textView);
//        startActivity(new Intent(this, FlowLayoutActivity.class));


    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


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
