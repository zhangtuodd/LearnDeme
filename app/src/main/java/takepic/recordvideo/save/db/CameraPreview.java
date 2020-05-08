package takepic.recordvideo.save.db;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.base.util.CameraUtils;
import com.example.base.util.LogUtils;
import com.example.base.util.SizeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * A basic Camera preview class
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraPreview";
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Context context;
    private int orientation;
    private int screenHeight, screenWidth;


    public CameraPreview(final Context context, Camera camera) {
        super(context);
        this.context = context;
        setParams(context);
        mCamera = camera;
        mHolder = getHolder();
        //安装SurfaceHolder.Callback，以便在创建和销毁基础表面时收到通知。
        mHolder.addCallback(this);
    }

    private void setParams(Context context) {
        int width = SizeUtils.getScreenWidth(context);
        int height = SizeUtils.getScreenHeight(context);
        LogUtils.i(TAG, "screen size--" + width + "*" + height);
        int realWidth = 0;
        int realHeight = 0;
        if ((height / width) > (16f / 9)) {
            realHeight = height;
            realWidth = (int) (height / 16f * 9);
        } else {
            realWidth = width;
            realHeight = (int) (width * 16f / 9);
        }
        LogUtils.i(TAG, "real size--" + realWidth + "*" + realHeight);
        setLayoutParams(new FrameLayout.LayoutParams(realWidth, realHeight));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            //将 SurfaceView 连接到相机
            mCamera.setPreviewDisplay(holder);
            //开始预览
            mCamera.startPreview();

            //对焦监听需放到开始预览之后
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        Toast.makeText(context, "对焦成功", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
            });

        } catch (IOException e) {
            LogUtils.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //请注意在活动中释放“相机预览”。
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        //如果预览可以更改或旋转，请在此处处理这些事件。确保在调整大小或重新设置格式之前停止预览
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // 进行更改之前停止预览
        try {
//            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        //设置预览大小，并在此处进行任何调整大小，旋转或重新格式化更改，以新设置开始预览
        try {
//            mCamera.setPreviewDisplay(mHolder);
//            mCamera.startPreview();

        } catch (Exception e) {
            LogUtils.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }






}


