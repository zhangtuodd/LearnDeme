package ui.audiochartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by zhangtuo on 2017/10/27.
 * <p>
 * 自定义音频条形图
 */

public class AudioChartView extends View {

    Paint paint = null;
    int width = 50;
    int space = 20;
    Random random = new Random();

    private int maxHeight;


    public AudioChartView(Context context) {
        this(context, null);
    }

    public AudioChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 10; i++) {
            int left = 200 + i * (width + space);
            int top = random.nextInt(980) + 20;
            int right = left + width;
            int bottom = 1000;
            canvas.drawRect(left, top, right, bottom, paint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxHeight = getHeight();
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                width,
                maxHeight,
                Color.GREEN,
                Color.BLACK,
                Shader.TileMode.CLAMP
        );
        paint.setShader(gradient);
    }
}
