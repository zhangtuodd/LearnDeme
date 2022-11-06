package ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;

import java.nio.file.Path;

/**
 * @author： zhangtuo
 * @date： 2019-12-26
 * @description： 把长度分成60段，每录制1s，更新长度
 * 两种方案：
 * 用canvas绘制
 * 用横向progress  (放弃，同一个progress颜色无法调整)
 */
public class DouyinProgressBar extends View {
    private Paint bgPaint;
    private Paint recordPaint;
    private Paint pausePaint;
    private float barWidth;
    private Context mContext;
    private float dp16;


    public DouyinProgressBar(Context context) {
        this(context, null);
    }

    public DouyinProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DouyinProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initTypedArray(context, attrs);
    }

    private void initTypedArray(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DouyinProgressBar);
        int bgColor = typedArray.getColor(R.styleable.DouyinProgressBar_bg_color, Color.GRAY);
        int recordColor = typedArray.getColor(R.styleable.DouyinProgressBar_record_color, Color.YELLOW);
        int pauseColor = typedArray.getColor(R.styleable.DouyinProgressBar_pause_color, Color.WHITE);
//        barWidth = typedArray.getFloat(R.styleable.DouyinProgressBar_stroke_width, 4);
        barWidth = dip2px(context,20);
        typedArray.recycle();


        dp16 = dip2px(mContext, 16);
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
//        bgPaint.setStrokeWidth(barWidth);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setAntiAlias(true);

        recordPaint = new Paint();
        recordPaint.setColor(recordColor);
//        recordPaint.setStrokeWidth(barWidth);
        recordPaint.setStyle(Paint.Style.FILL);
        recordPaint.setAntiAlias(true);

        pausePaint = new Paint();
        pausePaint.setColor(pauseColor);
//        pausePaint.setStrokeWidth(barWidth);
        pausePaint.setStyle(Paint.Style.FILL);
        pausePaint.setAntiAlias(true);

        record();
    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 0, recordPix, 0, recordPaint);
        canvas.drawRect(0, 0, recordPix, barWidth, recordPaint);
//        canvas.drawLine(0, 0, getScreenWidth(mContext) / 2, 0, bgPaint);
    }


    public float getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (dm == null) ? 0 : (dm.widthPixels);
    }


    float recordPix = dp16;
    float segments = 0;

    public void record() {
        handler.post(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            segments = segments + 1;
            if (segments > 60) {
                LogUtils.i("DouyinProgressBar", "getScreenWidth------------" + getScreenWidth(mContext));
                LogUtils.i("DouyinProgressBar", "dp16------------" + dp16);
                LogUtils.i("DouyinProgressBar", "everySegment------------" + ((getScreenWidth(mContext) - dp16 * 2) / 60));

                pause();
                return;
            }
//            recordPix = (getScreenWidth(mContext) - dp16 * 2) / 60 * segments + dp16;
            recordPix = (getScreenWidth(mContext) - dp16 * 2) / 60 * segments;
            invalidate();
            LogUtils.i("DouyinProgressBar", "segments------------" + segments);
            LogUtils.i("DouyinProgressBar", "recordPix------------" + recordPix);
            handler.postDelayed(runnable, 100);
        }
    };

    public void pause() {
        handler.removeCallbacks(runnable);
        handler.removeCallbacksAndMessages(null);
    }

    private static Handler handler = new Handler(Looper.getMainLooper());


}
