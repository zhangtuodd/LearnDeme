package ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/5/4
 */

public class ScaleRulerActivity extends Activity {
    private ScaleRulerView rulerView;
    private ImageView imageView;
    private FrameLayout layout;

    float screenWidth;
    float perWidth;
    float imageViewWidth;

    float startPosition;//开始位置
    float endPosition;//结束位置
    float perRemainWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scale_ruler_layout);
        initView();
    }

    private void initView() {

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenWidth = dm.widthPixels - (int) dp2px(30);
        Log.i("tag", "screenWidth ---- >>>> " + screenWidth);
        perWidth = screenWidth / 9;
        Log.i("tag", "perWidth ---- >>>> " + perWidth);
        startPosition = perWidth / 2 * 3;

        endPosition = screenWidth - startPosition;
        rulerView = (ScaleRulerView) findViewById(R.id.custom_view);
        imageView = (ImageView) findViewById(R.id.img);
        layout = (FrameLayout) findViewById(R.id.layout);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageViewWidth = imageView.getWidth();
                Log.i("tag", "imageViewWidth ---- >>>> " + imageViewWidth);
                imageView.setTranslationX(startPosition);
            }
        });
        float remainWidth;
        remainWidth = screenWidth - perWidth * 3;
        Log.i("tag", "remainWidth ---- >>>> " + remainWidth);

        perRemainWidth = remainWidth / 7;
        Log.i("tag", "perRemainWidth ---- >>>> " + perRemainWidth);
        layout.setOnTouchListener(new View.OnTouchListener() {
            float x = 0, y = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                if (x < startPosition) {
                    x = startPosition;
                }
                if (x > endPosition) {
                    x = endPosition;
                }
                //截头去尾，中间部分
                int a = (int) ((x - startPosition) / perRemainWidth);
                if (((x - startPosition) % perRemainWidth) > perRemainWidth / 2) {
                    a = a + 1;
                }
                Log.i("tag", "a ---- >>>> " + a);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimatorSet setDown = new AnimatorSet();
                        setDown.playTogether(
                                ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f),
                                ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f),
                                ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0.5f)
                        );
                        setDown.start();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //个人感觉跟手时，指尖在控件的中间比较好，所以减去宽高的一半
                        imageView.setX(a * perRemainWidth + startPosition);
                        rulerView.moveTo(a);
//                v.setY(y + v.getTop() + v.getTranslationY() - v.getHeight() / 2);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("tag", "x ---- >>>> " + x);
                        Log.i("tag", "imageView.getLeft() ---- >>>> " + imageView.getLeft());
                        Log.i("tag", "imageView.getTranslationX() ---- >>>> " + imageView.getTranslationX());
                        Log.i("tag", "imageView.getWidth() / 2 ---- >>>> " + imageView.getWidth() / 2);
//                        imageView.setX(x);
                        imageView.setX(a * perRemainWidth + startPosition);
                        rulerView.moveTo(a);
                        AnimatorSet setUp = new AnimatorSet();
                        setUp.playTogether(
                                ObjectAnimator.ofFloat(imageView, "scaleX", 1.5f, 1f),
                                ObjectAnimator.ofFloat(imageView, "scaleY", 1.5f, 1f),
                                ObjectAnimator.ofFloat(imageView, "alpha", 0.5f, 1f)
                        );
                        setUp.start();
                        break;
                }
                return true;
            }
        });

    }


    public float dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

}
