package takepic.recordvideo.save.db;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.base.util.LogUtils;
import com.example.base.util.SizeUtils;
import com.example.base.util.SystemUtil;
import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 官方抓取图片，录制视频demo
 * <p>
 * https://developer.android.google.cn/guide/topics/media/camera
 * <p>
 * 相关blog：https://juejin.im/post/5d6d1155e51d4561ea1a94a4#heading-76
 */
public class CustomCameraActivity extends BaseActivity {

    public static final String TAG = "CustomCameraActivity";
    private CameraApi cameraApi;
    private Camera mCamera;
    private SurfaceHolder holder;
    //    private CameraPreview mPreview;
    private SurfaceView surfaceView;
    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;//为0

    //缩放值
    private int mZoom;

    //两点的初始距离
    private float startDis;
    private boolean isMove = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);
        cameraApi = new CameraImpl();

        findViewById(R.id.button_record).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraApi.recordVideo();
                    }
                }
        );

        findViewById(R.id.button_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraApi.capturePic();
            }
        });

        findViewById(R.id.button_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfaceView.setOnTouchListener(null);
                holder.removeCallback(callback);
                cameraApi.switchCamera();
                surfaceView.setOnTouchListener(touchListener);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = cameraApi.createCamera(mCameraId);
        if (mCamera != null) {
//            mPreview = new CameraPreview(this, mCamera);

            surfaceView = findViewById(R.id.camera_preview);
            surfaceView.setOnTouchListener(touchListener);
            setSurfaceParams();
            cameraApi.setSurfaceView(surfaceView, this);

            holder = surfaceView.getHolder();
            //注册SurfaceHolder.Callback，以便在创建和销毁基础表面时收到通知。
            holder.addCallback(callback);
        }
    }

    SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            cameraApi.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            //如果预览可以更改或旋转，请在此处处理这些事件。确保在调整大小或重新设置格式之前停止预览
            if (holder.getSurface() == null) {
                return;
            }
            // 进行更改之前停止预览..
            //设置预览大小，并在此处进行任何调整大小，旋转或重新格式化更改，以新设置开始预览..
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //请注意在活动中释放“相机预览”。
        }
    };

    private void setSurfaceParams() {
        int width = SizeUtils.getScreenWidth(this);
        int height = SizeUtils.getScreenHeight(this);
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
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(realWidth, realHeight));
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraApi.releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        cameraApi.releaseCamera();              // release the camera immediately on pause event
    }


    //默认状态
    private static final int MODE_INIT = 0;
    //两个触摸点触摸屏幕状态
    private static final int MODE_ZOOM = 1;
    //标识模式
    private int mode = MODE_INIT;


    /**
     * 变焦
     *
     * @param zoom 缩放系数
     */
    public void setZoom(int zoom) {
        if (mCamera == null) {
            return;
        }
        //获取Paramters对象
        Camera.Parameters parameters;
        parameters = mCamera.getParameters();
        //如果不支持变焦
        if (!parameters.isZoomSupported()) {
            return;
        }
        //
        parameters.setZoom(zoom);
        //Camera对象重新设置Paramters对象参数
        mCamera.setParameters(parameters);
        mZoom = zoom;

    }

    /**
     * 返回缩放值
     *
     * @return 返回缩放值
     */
    public int getZoom() {
        return mZoom;
    }


    /**
     * 获取最大Zoom值
     *
     * @return zoom
     */
    public int getMaxZoom() {
        if (mCamera == null) {
            return -1;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        if (!parameters.isZoomSupported()) {
            return -1;
        }
        return parameters.getMaxZoom() > 50 ? 50 : parameters.getMaxZoom();
    }

    /**
     * 自动变焦
     */
    public void autoFoucus() {
        if (mCamera != null) {
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        Toast.makeText(CustomCameraActivity.this, "对焦成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * 触摸回调
     *
     * @param v     添加Touch事件具体的view
     * @param event 具体事件
     * @return
     */
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //无论多少跟手指加进来，都是MotionEvent.ACTION_DWON MotionEvent.ACTION_POINTER_DOWN
            //MotionEvent.ACTION_MOVE:
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                //手指按下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_INIT;
                    break;
                //当屏幕上已经有触摸点按下的状态的时候，再有新的触摸点被按下时会触发
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = MODE_ZOOM;
                    //计算两个手指的距离 两点的距离
                    startDis = SystemUtil.twoPointDistance(event);
                    break;
                //移动的时候回调
                case MotionEvent.ACTION_MOVE:
                    isMove = true;
                    //这里主要判断有两个触摸点的时候才触发
                    if (mode == MODE_ZOOM) {
                        //只有两个点同时触屏才执行
                        if (event.getPointerCount() < 2) {
                            return true;
                        }
                        //获取结束的距离
                        float endDis = SystemUtil.twoPointDistance(event);
                        //每变化10f zoom变1
                        int scale = (int) ((endDis - startDis) / 10f);
                        if (scale >= 1 || scale <= -1) {
                            int zoom = getZoom() + scale;
                            //判断zoom是否超出变焦距离
                            if (zoom > getMaxZoom()) {
                                zoom = getMaxZoom();
                            }
                            //如果系数小于0
                            if (zoom < 0) {
                                zoom = 0;
                            }
                            //设置焦距
                            setZoom(zoom);
                            //将最后一次的距离设为当前距离
                            startDis = endDis;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    //判断是否点击屏幕 如果是自动聚焦
                    if (isMove == false) {
                        //自动聚焦
                        autoFoucus();
                    }
                    isMove = false;
                    break;
            }
            return true;
        }
    };
}
