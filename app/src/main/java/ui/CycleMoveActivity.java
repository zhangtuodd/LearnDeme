package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
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
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                // X轴的开始位置
                android.view.animation.Animation.RELATIVE_TO_SELF, -1f,
                // X轴的结束位置
                android.view.animation.Animation.RELATIVE_TO_SELF, ScreenWidth / 80,
                // Y轴的开始位置
                android.view.animation.Animation.RELATIVE_TO_SELF, 0f,
                // Y轴的结束位置
                android.view.animation.Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(20);  //  设置动画重复次数

        translateAnimation.setRepeatMode(android.view.animation.Animation.REVERSE);
        //translateAnimation.setRepeatMode(Animation.RESTART);    //重新从头执行
        //translateAnimation.setRepeatMode(Animation.REVERSE);  //反方向执行

        animationSet.addAnimation(translateAnimation);
        im.setAnimation(animationSet);

    }
}
