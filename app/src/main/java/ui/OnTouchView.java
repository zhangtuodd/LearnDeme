package ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.EventUtils;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-26
 */
public class OnTouchView extends View {
    public OnTouchView(Context context) {
        super(context);
    }

    public OnTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OnTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        LogUtils.e("zhang", "onTouchEvent------action:::" + EventUtils.getEvent(event.getAction()));
//
////        switch (action) {
//////            case (MotionEvent.ACTION_DOWN):
//////                return true;
//////            case MotionEvent.ACTION_MOVE:
//////                return true;
////            case MotionEvent.ACTION_UP:
////                return false;
////
////
////        }
//
//        return super.onTouchEvent(event);
//
//    }

}
