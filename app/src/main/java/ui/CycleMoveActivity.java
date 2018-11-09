package ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

/**
 * 循环往复动画
 * https://www.cnblogs.com/seven1979/p/4386172.html
 *
 * @author zhangtuo
 * @date 2018/11/8
 */

public class CycleMoveActivity extends BaseActivity {
    ImageView im;
    private int ScreenWidth;
    private int ScreenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cycle_move_layout);
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
        im = (ImageView) findViewById(R.id.image);
        Animation();

    }


    private void Animation() {
        final TranslateAnimation translateAnimation = new TranslateAnimation(10,ScreenWidth,0,0);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(20);  //  设置动画重复次数
        translateAnimation.setRepeatMode(android.view.animation.Animation.REVERSE);
        //translateAnimation.setRepeatMode(Animation.RESTART);    //重新从头执行
        //translateAnimation.setRepeatMode(Animation.REVERSE);  //反方向执行
        im.setAnimation(translateAnimation);
        translateAnimation.startNow();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                translateAnimation.cancel();
            }
        },20000);
    }


}
