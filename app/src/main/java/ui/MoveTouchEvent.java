package ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/5/7
 */
public class MoveTouchEvent implements View.OnTouchListener {
    int x = 0, y = 0;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                AnimatorSet setDown = new AnimatorSet();
                setDown.playTogether(
                        ObjectAnimator.ofFloat(v, "scaleX", 1f, 1.5f),
                        ObjectAnimator.ofFloat(v, "scaleY", 1f, 1.5f),
                        ObjectAnimator.ofFloat(v, "alpha", 1f, 0.5f)
                );
                setDown.start();
                break;
            case MotionEvent.ACTION_MOVE:
                //个人感觉跟手时，指尖在控件的中间比较好，所以减去宽高的一半
                v.setX(x + v.getLeft() + v.getTranslationX() - v.getWidth() / 2);
//                v.setY(y + v.getTop() + v.getTranslationY() - v.getHeight() / 2);
                break;
            case MotionEvent.ACTION_UP:
                AnimatorSet setUp = new AnimatorSet();
                setUp.playTogether(
                        ObjectAnimator.ofFloat(v, "scaleX", 1.5f, 1f),
                        ObjectAnimator.ofFloat(v, "scaleY", 1.5f, 1f),
                        ObjectAnimator.ofFloat(v, "alpha", 0.5f, 1f)
                );
                setUp.start();
                break;
        }
        return true;
    }


}
