package ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.icu.text.DecimalFormat;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

/**
 * Created by zhangtuo on 2017/10/17.
 * 感谢好文：
 * https://mp.weixin.qq.com/s?__biz=MzIxOTU1MDg5Ng==&mid=2247484222&idx=1&sn=95f3793ed034027363773acfbf15fd1e&chksm=97d8c6e1a0af4ff797babbe78ee859f897cf4fec688d28e97adfef9ebd74bcb14a19cd12252e#rd
 * <p>
 * 一步步完善：
 * 简单画个规定大小矩形，里面包括一个圆环
 * 给圆环添加一个动画
 * 给动画圆弧添加个背景圆环
 * 动态控制圆环的大小 重写onMeasure
 * 自定义一些属性，更方便的简化控件实用
 * <p>关于自定义属性的理解：主要是使用者在xml可以配置值，作者在代码中拿到相应的值，赋予相应的用途
 * 给控件添加一个数据
 */

public class CircleBarView extends View {
    private Paint rectPaint;//矩形外框
    private Paint circleBgPaint;
    private Paint circlePaint;//圆形

    private CircleBarAnim circleBarAnim;

    private float sweepAngle = 360;//圆弧经过的角度
    private float startAngle = 0;//开始绘制角度
    private float actualAngle;

    private RectF mRectF;//绘制圆弧的矩形区域

    private float barWidth;//圆弧进度条宽度
    private int defaultSize;//自定义View默认的宽高


    public CircleBarView(Context context) {
        this(context, null);

    }

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleBarView);
        int circleBgColor = typedArray.getColor(R.styleable.CircleBarView_circle_bg_color, Color.GRAY);
        int circleColor = typedArray.getColor(R.styleable.CircleBarView_circle_color, Color.GREEN);
        barWidth = typedArray.getDimension(R.styleable.CircleBarView_circle_stoken_width, px2dip(context, 10));
        startAngle = typedArray.getFloat(R.styleable.CircleBarView_start_angle, 0);
        sweepAngle = typedArray.getFloat(R.styleable.CircleBarView_sweep_angle, 360);

        typedArray.recycle();//记着回收


        defaultSize = dip2px(context, 100);
        mRectF = new RectF();


        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);//只描边
        rectPaint.setStrokeWidth(barWidth);
        rectPaint.setColor(Color.parseColor("#F2935B"));

        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setStrokeWidth(barWidth);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);

        circleBgPaint = new Paint();
        circleBgPaint.setColor(circleBgColor);
        circleBgPaint.setStrokeWidth(barWidth);
        circleBgPaint.setStyle(Paint.Style.STROKE);
        circleBgPaint.setAntiAlias(true);

        circleBarAnim = new CircleBarAnim();

    }

    TextView textView;

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(mRectF, rectPaint);

        canvas.drawArc(mRectF, 0, 360, false, circleBgPaint);

        canvas.drawArc(mRectF, startAngle, actualAngle, false, circlePaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultSize, heightMeasureSpec);
        int width = measureSize(defaultSize, widthMeasureSpec);
        int min = Math.min(width, height);// 获取View最短边的长度
        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形

        if (min >= barWidth * 2) {//这里简单限制了圆弧的最大宽度
            mRectF.set(barWidth / 2, barWidth / 2, min - barWidth / 2, min - barWidth / 2);
        }
    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    class CircleBarAnim extends Animation {

        public CircleBarAnim() {

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            actualAngle = interpolatedTime * sweepAngle * progressNum / maxNum;//这里计算进度条的比例
            Log.i("tag", "interpolatedTime--------------:" + interpolatedTime);
            if (textView != null) {
                textView.setText(decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%");
            }
            postInvalidate();
        }
    }

    private float progressNum;
    private float maxNum = 100;//100为满，随便设置

    //写个方法给外部调用，用来设置动画时间
    public void setProgressNum(float progressNum, int time) {
        this.progressNum = progressNum;
        circleBarAnim.setDuration(time);
        startAnimation(circleBarAnim);
    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
