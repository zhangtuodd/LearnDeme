package ui.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhangtuo 2021/11/8
 */
public class ScrollLayout extends ViewGroup {
    private Scroller mScroller;
    //当前设备滑动的最小距离
    private int mTouchSlop;
    private int leftBorder;//布局内容的左边界
    private int rightBorder;//布局内容的右边界
    private float mRawXDown;
    private float mRawXMove;
    private float mRawXLastMove;

    public ScrollLayout(Context context) {
        super(context);
        initView(context);
    }

    public ScrollLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //为ScrollLayout中的某一个子View给出一个建议的测量大小和测量模式
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.layout(i * view.getMeasuredWidth(), 0, (i + 1) * view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
        Log.i("TAG-onLayout", "leftBorder-:" + leftBorder + " | rightBorder-:" + rightBorder);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRawXDown = ev.getRawX();
                mRawXLastMove = mRawXDown;
                Log.i("TAG-onIntercept-DOWN", "mRawXDown-:" + mRawXDown + " | mRawXLastMove-:" + mRawXLastMove);
                break;
            case MotionEvent.ACTION_MOVE:
                mRawXMove = ev.getRawX();
                mRawXLastMove = mRawXMove;
                float distance = Math.abs(mRawXMove - mRawXDown);
                Log.i("TAG-onIntercept-MOVE", "mRawXDown-:" + mRawXDown + " | mRawXLastMove-:" + mRawXLastMove
                        + " | distance-:"+distance + " | mTouchSlop-:"+mTouchSlop);
                //左右滑动时，拦截子view的触摸事件
                if (distance > mTouchSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG-onIntercept-UP", "ACTION_UP---------");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mRawXMove = event.getRawX();
                int distanceX = (int) (mRawXLastMove - mRawXMove);
                Log.i("TAG-onTouchEvent-MOVE", "mRawXLastMove-:" + mRawXLastMove + " | mRawXMove-:" + mRawXMove
                 +" | distanceX-:"+distanceX + " | getScrollX()-:"+getScrollX());

                //对边界异常情况的处理
                if (getScrollX() + distanceX < leftBorder) {
                    scrollBy(leftBorder, 0);
                }
                if (getScrollX() + getWidth() + distanceX > rightBorder) {
                    scrollBy(rightBorder - getWidth(), 0);
                }
                scrollBy(distanceX, 0);
                mRawXLastMove = mRawXMove;
                break;
            case MotionEvent.ACTION_UP:
                //当前所在的page页面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                Log.i("TAG--onTouchEvent-UP", "dx: " + dx);
                Log.i("TAG--onTouchEvent-UP", "getScrollX: " + getScrollX());
                Log.i("TAG--onTouchEvent-UP", "getWidth: " + getWidth());
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
