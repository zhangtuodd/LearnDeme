package ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * 首付比例尺 10 ～ 100
 *
 * @author zhangtuo
 * @date 2018/5/4
 * <p>
 * 底色为可预定色
 * 只画已预定时间和过期时间
 */


public class ScaleRulerView1 extends View {

    Paint canBookPaint;//可以预定
    Paint yetBookPaint;//已被预定
    Paint expiredPaint;//过期
    Paint textPaint;//时间字体

    int lineHeight = 0;//时间轴高度
    float perRuleWidth;//7-23 16个小时
    float screenWidth;

    float wordStartY;
    int ruleStartY;
    Context context;

    public ScaleRulerView1(Context context) {
        this(context, null);
    }

    public ScaleRulerView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleRulerView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth() - dp2px(context, 30);
        lineHeight = dp2px(context, 12);

        canBookPaint = new Paint();
        canBookPaint.setColor(Color.parseColor("#E9EAEB"));
        canBookPaint.setStrokeWidth(lineHeight);
        canBookPaint.setStyle(Paint.Style.STROKE);
        canBookPaint.setAntiAlias(true);

        yetBookPaint = new Paint();
        yetBookPaint.setColor(Color.parseColor("#4C7DE9"));
        yetBookPaint.setStrokeWidth(lineHeight);
        yetBookPaint.setStyle(Paint.Style.STROKE);
        yetBookPaint.setAntiAlias(true);


        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#66A8AAAD"));
        textPaint.setTextSize(dp2px(context, 11));

        expiredPaint = new Paint();
        expiredPaint.setColor(Color.parseColor("#D4D4D6"));
        expiredPaint.setStrokeWidth(lineHeight);
        expiredPaint.setStyle(Paint.Style.STROKE);
        expiredPaint.setAntiAlias(true);


        //绘制api会减半个高度，导致高度显示一半，这里加回来
        ruleStartY = dp2px(ScaleRulerView1.this.context, 6);
        Rect rect = new Rect();
        textPaint.getTextBounds("7", 0, 1, rect);
        float wordHeight = rect.height();
        wordStartY = dp2px(ScaleRulerView1.this.context, 20) + wordHeight;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//设置背景颜色
        float allWordWidth = 0;
        for (int i = 7; i < 24; i++) {
            float wordWidth = textPaint.measureText(String.valueOf(i));
            allWordWidth = allWordWidth + wordWidth;
        }

        perRuleWidth = (screenWidth - allWordWidth) / 16;
        float yetWordWidth = 0;
        for (int i = 7; i < 24; i++) {
            float wordWidth = textPaint.measureText(String.valueOf(i));
            canvas.drawText(String.valueOf(i), (i - 7) * perRuleWidth + yetWordWidth, wordStartY, textPaint);
            yetWordWidth = yetWordWidth + wordWidth;
        }

        calculateRulePoint(canvas, 7, 10, perRuleWidth, TimeType.expired);
        calculateRulePoint(canvas, 10, 18.5f, perRuleWidth, TimeType.yetBook);
        calculateRulePoint(canvas, 19f, 24, perRuleWidth, TimeType.canBook);
    }

    /**
     * @param startTime 12.5 - 12:30
     * @param endTime
     * @param type
     * @return
     */
    private void calculateRulePoint(Canvas canvas, float startTime, float endTime, float perRuleWidth, TimeType type) {
        float startX = 0;
        float endX = 0;
        float firstWordWidth = textPaint.measureText("7");
        if (startTime == 7) {
            startX = firstWordWidth / 4;
        } else {
            startX = calculate(startTime, perRuleWidth, startTime * 2 % 2 != 0);
        }
        endX = calculate(endTime, perRuleWidth, endTime * 2 % 2 != 0);

        if (type == TimeType.expired) {
            canvas.drawLine(startX, ruleStartY, endX, ruleStartY, expiredPaint);
        } else if (type == TimeType.yetBook) {
            canvas.drawLine(startX, ruleStartY, endX, ruleStartY, yetBookPaint);
        } else {
            canvas.drawLine(startX, ruleStartY, endX, ruleStartY, canBookPaint);
        }
    }

    private float calculate(float time, float perRuleWidth, boolean isHalfClock) {
        float gapWidth = 0;//刻度空隙宽度
        float wordWidth = 0;//字体宽度
        float pointX = 0;

        for (int i = 7; i <= time; i++) {
            float w = textPaint.measureText(String.valueOf(i));
            if (isHalfClock) {//是半点
                wordWidth = wordWidth + w;
            } else {
                if (i == time) {
                    wordWidth = wordWidth + w / 2;
                } else {
                    wordWidth = wordWidth + w;
                }
            }
        }
        gapWidth = (time - 7) * perRuleWidth;
        pointX = gapWidth + wordWidth;
        return pointX;
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    int[] expired;
    int[] yetBook;
    int[] canBook;

    public void setData(int[] expired, int[] yetBook, int[] canBook) {
        this.expired = expired;
        this.yetBook = yetBook;
        this.canBook = canBook;
        invalidate();
    }

    enum TimeType {
        expired,
        yetBook,
        canBook
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
