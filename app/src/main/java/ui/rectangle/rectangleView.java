package ui.rectangle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.base.util.SizeUtils;
import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/21
 */

public class rectangleView extends View {

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix

    public rectangleView(Context context) {
        this(context, null);
    }

    public rectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public rectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix(context);
    }

    private void initBitmapAndMatrix(Context context) {
//        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.bg_img_red);
        mBitmap = Bitmap.createBitmap(SizeUtils.dip2px(context, 46), SizeUtils.dip2px(context, 30), Bitmap.Config.RGB_565);
//        mBitmap.eraseColor(Color.parseColor("#ffffffff"));//填充颜色
        mPolyMatrix = new Matrix();

        float[] src = {0, 0,                                    // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下

        float[] dst = {0, mBitmap.getHeight() / 3 * 2,          // 左上
                mBitmap.getWidth(), mBitmap.getHeight() / 3 * 1,// 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下

        // 核心要点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1); // src.length >> 1 为位移运算 相当于处以2

        // 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
//        mPolyMatrix.postScale(0.26f, 0.26f);
//        mPolyMatrix.postTranslate(0, 200);
    }

    public void setColor(int color) {
        mBitmap.eraseColor(color);//填充颜色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, paint);
    }
}
