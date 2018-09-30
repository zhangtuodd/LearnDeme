package com.example.zhangtuo.learndeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

//import mvvm.OneActivity;
import mvvm.BIndActivity;
import mvvm.BIndActivity2;
import mvvm.BIndActivity3;
import mvvm.BIndActivity4;
import mvvm.BIndActivity5;
import proxy.IStarBehavior;
import proxy.DynamicProxy;
import proxy.IStarBehaviorPlus;
import proxy.Star;
import ui.CommonDialog;
import ui.popupwindow.DeletePupView;

public class MainActivity extends BaseActivity {

    DeletePupView pupView;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageView iv;


    private ImageView mIVSign;
    private TextView mTVSign;
    private Bitmap mSignBitmap;
    int screenWidth;
    int perHeight;//每个块的高度
    int perWidth;
    CommonDialog dialog;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        startActivity(new Intent(this, BIndActivity5.class));
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new CommonDialog(MainActivity.this, "确认删除吗？", "确认", "取消", new CommonDialog.ActionListener() {
                    @Override
                    public void clickLeft() {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic, options);
                        Log.d("logWrapperImageView", "width=" + bitmap.getWidth() + ",height=" + bitmap.getHeight() + ",size=" + bitmap.getByteCount());
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRight() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
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
