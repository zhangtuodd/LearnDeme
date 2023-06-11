package activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangtuo.learndeme.R;

public class OpenCloseViewActivity extends AppCompatActivity {

    private LinearLayout contentView;
    private Button clickView;
    private boolean isExpanded = false;
    private int initialContentHeight; //展开高度 即View的高度
    private int collapsedHeight;//折叠时需要的高度 0 or 其他..

    private boolean isDragging = false; // 标记是否正在拖动中
    private float initialY; // 记录初始Y坐标
    private int initialHeight; // 记录初始高度

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_close_layout);

        contentView = findViewById(R.id.contentView);
        clickView = findViewById(R.id.clickView);

        clickView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialY = event.getRawY();
                    initialHeight = contentView.getHeight();
                    isDragging = false; // 重置拖动标记
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float deltaY = event.getRawY() - initialY;
                    int newHeight = initialHeight + (int) deltaY;
                    newHeight = Math.max(collapsedHeight, newHeight);
                    newHeight = Math.min(initialContentHeight, newHeight);
                    ViewGroup.LayoutParams params = contentView.getLayoutParams();
                    params.height = newHeight;
                    contentView.setLayoutParams(params);
                    isDragging = true; // 设置拖动标记为true
                    return true;
                case MotionEvent.ACTION_UP:
                    if (isDragging) {
                        float deltaYRelease = event.getRawY() - initialY;
                        if (deltaYRelease < 0 && contentView.getHeight() < initialContentHeight) {
//                                animateCollapse();
                            toggleView();
                        } else if (deltaYRelease > 0 && contentView.getHeight() > collapsedHeight) {
//                                animateExpand();
                            toggleView();
                        }
                        isDragging = false; // 重置拖动标记为false
                        return true;
                    } else {
                        // 重置拖动标记为false
                        toggleView(); // 执行点击事件
                    }
                    break;
            }
            return false;
        });

        // 初始展开内容部分
        contentView.post(new Runnable() {
            @Override
            public void run() {
                initialContentHeight = contentView.getHeight();
                collapsedHeight = 0;

                ViewGroup.LayoutParams params = contentView.getLayoutParams();
                params.height = collapsedHeight;
                contentView.setLayoutParams(params);
            }
        });
    }

    private void toggleView() {
        if (isExpanded) {
            animateCollapse();
        } else {
            animateExpand();
        }
        isExpanded = !isExpanded;
    }

    private void animateCollapse() {
        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), collapsedHeight);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = contentView.getLayoutParams();
                params.height = height;
                contentView.setLayoutParams(params);
            }
        });
        animator.start();
    }

    private void animateExpand() {
        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), initialContentHeight);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = contentView.getLayoutParams();
                params.height = height;
                contentView.setLayoutParams(params);
            }
        });
        animator.start();
    }
}



//public class OpenCloseViewActivity extends AppCompatActivity {
//
//    private LinearLayout contentView;
//    private Button clickView;
//    private boolean isExpanded = true;
//    private int initialContentHeight;
//    private int collapsedHeight;
//    private GestureDetector gestureDetector;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.open_close_layout);
//
//        contentView = findViewById(R.id.contentView);
//        clickView = findViewById(R.id.clickView);
//
//        clickView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggleView();
//            }
//        });
//
//        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
//            private float initialY;
//            private int initialHeight;
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                if (e2.getAction() == MotionEvent.ACTION_MOVE) {
//                    float deltaY = e2.getRawY() - initialY;
//                    int newHeight = initialHeight + (int) deltaY;
//                    newHeight = Math.max(collapsedHeight, newHeight);
//                    newHeight = Math.min(initialContentHeight, newHeight);
//                    ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                    params.height = newHeight;
//                    contentView.setLayoutParams(params);
//                }
//                return true;
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                toggleView();
//                return true;
//            }
//
//            @Override
//            public boolean onDown(MotionEvent e) {
//                initialY = e.getRawY();
//                initialHeight = contentView.getHeight();
//                return true;
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                if (e2.getAction() == MotionEvent.ACTION_UP) {
//                    float deltaYRelease = e2.getRawY() - initialY;
//                    if (deltaYRelease < 0 && contentView.getHeight() < initialContentHeight) {
//                        animateCollapse();
//                    } else if (deltaYRelease > 0 && contentView.getHeight() > collapsedHeight) {
//                        animateExpand();
//                    }
//                }
//                return true;
//            }
//        });
//
//        clickView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });
//
//        // 初始展开内容部分
//        contentView.post(new Runnable() {
//            @Override
//            public void run() {
//                initialContentHeight = contentView.getHeight();
//                collapsedHeight = 0;
//                collapseView(contentView);
//            }
//        });
//    }
//
//    private void toggleView() {
//        TransitionManager.beginDelayedTransition((ViewGroup) contentView.getParent());
//
//        if (isExpanded) {
//            animateCollapse();
//        } else {
//            animateExpand();
//        }
//
//        isExpanded = !isExpanded;
//    }
//
//    private void expandView(View view) {
//        view.getLayoutParams().height = initialContentHeight;
//    }
//
//    private void collapseView(View view) {
//        view.getLayoutParams().height = collapsedHeight;
//    }
//
//    private void animateCollapse() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), collapsedHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//
//    private void animateExpand() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), initialContentHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//}

//public class OpenCloseViewActivity extends AppCompatActivity {
//
//    private LinearLayout contentView;
//    private Button clickView;
//    private boolean isExpanded = true;
//    private int initialContentHeight;
//    private int collapsedHeight;
//    private GestureDetector gestureDetector;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.open_close_layout);
//
//        contentView = findViewById(R.id.contentView);
//        clickView = findViewById(R.id.clickView);
//
//        clickView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggleView();
//            }
//        });
//
//        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
//            private float initialY;
//            private int initialHeight;
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                int newHeight = 0;
//                if (e2.getAction() == MotionEvent.ACTION_MOVE) {
//                    float deltaY = e2.getRawY() - initialY;
//                    newHeight = initialHeight + (int) deltaY;
//                    newHeight = Math.max(collapsedHeight, newHeight);
//                    newHeight = Math.min(initialContentHeight, newHeight);
//                    ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                    params.height = newHeight;
//                    contentView.setLayoutParams(params);
//                }
//                if (e2.getAction() == MotionEvent.ACTION_UP) {
//
//                    if(newHeight != initialContentHeight){
//                        toggleView();
//                    }
//                    return true;
//                }
//               return false;
//            }
//
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                toggleView();
//                return true;
//            }
//
//            @Override
//            public boolean onDown(MotionEvent e) {
//                initialY = e.getRawY();
//                initialHeight = contentView.getHeight();
//                return true;
//            }
//
////            @Override
////            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
////                if (e2.getAction() == MotionEvent.ACTION_UP) {
////                    float deltaYRelease = e2.getRawY() - initialY;
////                    if (deltaYRelease < 0 && contentView.getHeight() < initialContentHeight) {
////                        animateCollapse();
////                    } else if (deltaYRelease > 0 && contentView.getHeight() > collapsedHeight) {
////                        animateExpand();
////                    }
////                }
////                return true;
////            }
//        });
//
//        clickView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });
//
//        // 初始展开内容部分
//        contentView.post(new Runnable() {
//            @Override
//            public void run() {
//                initialContentHeight = contentView.getHeight();
//                collapsedHeight = 0;
//                collapseView(contentView);
//            }
//        });
//    }
//
//    private void toggleView() {
//        TransitionManager.beginDelayedTransition((ViewGroup) contentView.getParent());
//
//        if (isExpanded) {
//            animateCollapse();
//        } else {
//            animateExpand();
//        }
//
//        isExpanded = !isExpanded;
//    }
//
//    private void expandView(View view) {
//        view.getLayoutParams().height = initialContentHeight;
//    }
//
//    private void collapseView(View view) {
//        view.getLayoutParams().height = collapsedHeight;
//    }
//
//    private void animateCollapse() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), collapsedHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//
//    private void animateExpand() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), initialContentHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//}




//public class OpenCloseViewActivity extends AppCompatActivity {
//
//    private LinearLayout contentView;
//    private Button clickView;
//    private boolean isExpanded = true;
//    private int initialContentHeight;
//    private int collapsedHeight;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.open_close_layout);
//
//        contentView = findViewById(R.id.contentView);
//        clickView = findViewById(R.id.clickView);
//
//        clickView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggleView();
//            }
//        });
//
//        clickView.setOnTouchListener(new View.OnTouchListener() {
//            private float initialY;
//            private int initialHeight;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        initialY = event.getRawY();
//                        initialHeight = contentView.getHeight();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        float deltaY = event.getRawY() - initialY;
//                        int newHeight = initialHeight + (int) deltaY;
//                        newHeight = Math.max(collapsedHeight, newHeight);
//                        newHeight = Math.min(initialContentHeight, newHeight);
//                        ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                        params.height = newHeight;
//                        contentView.setLayoutParams(params);
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        float deltaYRelease = event.getRawY() - initialY;
//                        if (deltaYRelease < 0 && contentView.getHeight() < initialContentHeight) {
//                            animateCollapse();
//                        } else if (deltaYRelease > 0 && contentView.getHeight() > collapsedHeight) {
//                            animateExpand();
//                        }
//                        return true;
//                }
//                return false;
//            }
//        });
//
//        // 初始展开内容部分
//        contentView.post(new Runnable() {
//            @Override
//            public void run() {
//                initialContentHeight = contentView.getHeight();
//                collapsedHeight = 0;
//                collapseView(contentView);
//            }
//        });
//    }
//
//    private void toggleView() {
//        TransitionManager.beginDelayedTransition((ViewGroup) contentView.getParent());
//
//        if (isExpanded) {
//            expandView(contentView);
//        } else {
//            collapseView(contentView);
//        }
//
//        isExpanded = !isExpanded;
//    }
//
//    private void expandView(View view) {
//        view.getLayoutParams().height = initialContentHeight;
//    }
//
//    private void collapseView(View view) {
//        view.getLayoutParams().height = collapsedHeight;
//    }
//
//    private void animateCollapse() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), collapsedHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//
//    private void animateExpand() {
//        ValueAnimator animator = ValueAnimator.ofInt(contentView.getHeight(), initialContentHeight);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int height = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams params = contentView.getLayoutParams();
//                params.height = height;
//                contentView.setLayoutParams(params);
//            }
//        });
//        animator.start();
//    }
//}




///**
// * 以下是点击的展开折叠效果，没有按压拖拽的效果
// */
//public class OpenCloseViewActivity extends AppCompatActivity {
//
//    private LinearLayout contentView;
//    private Button clickView;
//    private boolean isExpanded = true;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.open_close_layout);
//
//        contentView = findViewById(R.id.contentView);
//        clickView = findViewById(R.id.clickView);
//
//        clickView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggleView();
//            }
//        });
//
//        // 初始展开内容部分
//        contentView.setVisibility(View.VISIBLE);
//    }
//
//    private void toggleView() {
//        TransitionManager.beginDelayedTransition((ViewGroup) contentView.getParent());
//
//        if (isExpanded) {
//            collapseView(contentView);
//        } else {
//            expandView(contentView);
//        }
//
//        isExpanded = !isExpanded;
//    }
//
//    private void expandView(View view) {
//        view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        view.requestLayout();
//    }
//
//    private void collapseView(View view) {
//        view.getLayoutParams().height = 0;
//        view.requestLayout();
//    }
//}
