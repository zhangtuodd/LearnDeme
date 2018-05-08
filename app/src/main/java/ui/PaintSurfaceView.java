package ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/28
 */

public class PaintSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = PaintSurfaceView.class.getSimpleName();

    private SurfaceHolder mHolder;
    private Paint mPaint;
    // 是否绘制
    private boolean isDrawing;
    private Canvas mCanvas;
    private Path mPath;
    private Context context;
    private Bitmap mBitmap;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();


    public PaintSurfaceView(Context context) {
        this(context, null);
    }

    public PaintSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mHolder = getHolder();
        mHolder.addCallback(this);

        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置画笔的风格
        mPaint.setStyle(Paint.Style.STROKE);
        //设置边线的宽度
        mPaint.setStrokeWidth(10);
        //设置画笔的颜色
        mPaint.setColor(Color.BLACK);

        mPath = new Path();
        mBitmap = Bitmap.createBitmap(300,400, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        isDrawing = true;
        new Thread(this).start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
        Log.d(TAG, "surfaceDestroyed: ");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                //记录所有划过的点
                mPath.lineTo(x, y);
                return true;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        while (isDrawing) {
            try {
                // 锁定画布
                mCanvas = mHolder.lockCanvas();
                myDraw();
            } catch (Exception e) {
                Log.d(TAG, "run: " + e.getMessage());
            } finally {
                if (mCanvas != null) {
                    // 释放画布
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }


    public void myDraw() {
        mCanvas.drawColor(Color.WHITE);
        mCanvas.drawBitmap(mBitmap, 0, 0, null);
        mCanvas.drawPath(mPath, mPaint);
    }

    public void clear() {
        mPath.reset();
    }

    public Bitmap sure(View view) {
        if (mPath.isEmpty()) {
            Toast.makeText(context, "请签名", Toast.LENGTH_SHORT).show();
            return null;
        } else {
//            Bitmap bitmap = getViewBitmap(view);
            return mBitmap;
//            try {
//                Bitmap bitmap = getViewBitmap(view);
//
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] bytes = baos.toByteArray();
//                baos.close();
//                return Base64.encodeToString(bytes, Base64.DEFAULT);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "";
//            }
        }
    }

    public Bitmap getViewBitmap(View v) {
        return resizeImage(mBitmap, 320, 480);
    }

    // 缩放
    public static Bitmap resizeImage(Bitmap bitmap, int width, int height) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        float scaleWidth = ((float) width) / originWidth;
        float scaleHeight = ((float) height) / originHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, originWidth,
                originHeight, matrix, true);
        return resizedBitmap;
    }

}