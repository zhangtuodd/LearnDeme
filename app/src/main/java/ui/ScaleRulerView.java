package ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.zhangtuo.learndeme.R;

/**
 * 首付比例尺 10 ～ 100
 *
 * @author zhangtuo
 * @date 2018/5/4
 */

public class ScaleRulerView extends View {

    int defaultSize = 0;
    Paint bgPaint;
    Paint slidePaint;
    Paint textPaint;
    int baseLineY;
    float screenWidth;
    float perWidth;//分成十份，每份宽度
    int startHeight;
    int size = 0;
    Bitmap bitmap;
    float slideWidth;
    float perRemainWidth;
    float startPosition;//开始位置
    float remainWidth;
    Context context;

    public ScaleRulerView(Context context) {
        this(context, null);
    }

    public ScaleRulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.slide);
        this.post(new Runnable() {
            @Override
            public void run() {
                startHeight = getHeight() / 2;
                baseLineY = getHeight();
                slideWidth = bitmap.getHeight();
                Log.i("tag", "slideWidth ---- >>> " + slideWidth);
            }
        });


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth() - (int) dp2px(context, 30);
        perWidth = screenWidth / 9;
        Log.i("tag", "ScaleRulerView--screenWidth ---- >>> " + screenWidth);
        Log.i("tag", "ScaleRulerView---perWidth ---- >>> " + perWidth);

        startPosition = perWidth / 2 * 3;

        remainWidth = screenWidth - perWidth * 3;
        Log.i("tag", "ScaleRulerView---remainWidth ---- >>>> " + remainWidth);

        perRemainWidth = remainWidth / 7;
        Log.i("tag", "ScaleRulerView---perRemainWidth ---- >>>> " + perRemainWidth);

        defaultSize = dp2px(context, 5);
        bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor("#eaeaea"));                    //设置画笔颜色
        bgPaint.setStrokeWidth(defaultSize);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setAntiAlias(true);

        slidePaint = new Paint();
        slidePaint.setColor(Color.parseColor("#527EFE"));                    //设置画笔颜色
        slidePaint.setStrokeWidth(defaultSize);
        slidePaint.setStyle(Paint.Style.STROKE);
        slidePaint.setAntiAlias(true);


        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#9b9ea9"));
        textPaint.setTextSize(dp2px(context, 12)); //以px为单位
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);                  //设置背景颜色
        canvas.drawLine(0, startHeight, screenWidth, startHeight, bgPaint);
        canvas.drawLine(0, startHeight, startPosition + perRemainWidth * size, startHeight, slidePaint);
        Log.i("tag", "baseLineY----  >>>> " + baseLineY);

        for (int i = 0; i < 8; i++) {
            canvas.drawText(String.valueOf((i + 1) * 10), startPosition + perRemainWidth * i - slideWidth / 2, baseLineY, textPaint);
        }


    }

    public void moveTo(int size) {
        this.size = size;
        invalidate();
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }
}
