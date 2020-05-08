package camera_video_record;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import camera_video_record.utils.CameraUtils;

public class RecordActivity extends BaseActivity implements Camera.PreviewCallback, SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "MainActivity";

    GLSurfaceView mGlSurfaceView;
    Button mBtnStart;
    Button mBtnStop;
    Context mContext;
    private MainHandler mMainHandler;

    int currentCameraType = Camera.CameraInfo.CAMERA_FACING_FRONT;
    int cameraWidth = 1280;
    int cameraHeight = 720;
    Camera mCamera;
    boolean isNeedSwitchCameraSurfaceTexture = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);

        mGlSurfaceView = findViewById(R.id.gl_surface_view);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStop = findViewById(R.id.btn_stop);

        mContext = this;
        mMainHandler = new MainHandler(this);
        mGlSurfaceView.setEGLContextClientVersion(2);
        mGlSurfaceView.setRenderer(new GLRenderer());
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onResume() {
        super.onResume();
        openCamera(currentCameraType, cameraWidth, cameraHeight);
        mGlSurfaceView.onResume();
    }


    static class MainHandler extends Handler {

        static final int HANDLE_CAMERA_START_PREVIEW = 1;

        private WeakReference<RecordActivity> mActivityWeakReference;

        MainHandler(RecordActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecordActivity activity = mActivityWeakReference.get();
            switch (msg.what) {
                case HANDLE_CAMERA_START_PREVIEW:
                    activity.handleCameraStartPreview((SurfaceTexture) msg.obj);
                    break;
            }
        }
    }


    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.e(TAG, "onFrameAvailable: ");
    }

    private void handleCameraStartPreview(SurfaceTexture surfaceTexture) {
        mCamera.setPreviewCallbackWithBuffer(this);
        try {
            mCamera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceTexture.setOnFrameAvailableListener(this);
        mCamera.startPreview();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e(TAG, "onPointerCaptureChanged: ");
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.e(TAG, "onPreviewFrame: ");
        mCamera.addCallbackBuffer(data);
    }

    class GLRenderer implements GLSurfaceView.Renderer {
        SurfaceTexture mCameraSurfaceTexture;
        private int mCameraTextureId = 1;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            Log.e(TAG, "onSurfaceCreated: ");
            switchCameraSurfaceTexture();
        }

        public void switchCameraSurfaceTexture() {
            Log.e(TAG, "switchCameraSurfaceTexture");
            isNeedSwitchCameraSurfaceTexture = false;
            if (mCameraSurfaceTexture != null) {
                destroySurfaceTexture();
            }
            mCameraSurfaceTexture = new SurfaceTexture(mCameraTextureId);
            Log.e(TAG, "send start camera message");
            mMainHandler.sendMessage(mMainHandler.obtainMessage(
                    MainHandler.HANDLE_CAMERA_START_PREVIEW,
                    mCameraSurfaceTexture));
        }

        public void destroySurfaceTexture() {
            if (mCameraSurfaceTexture != null) {
                mCameraSurfaceTexture.release();
                mCameraSurfaceTexture = null;
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            Log.e(TAG, "onSurfaceChanged: ");
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            Log.e(TAG, "onDrawFrame: ");
        }
    }

    private void openCamera(int cameraType, int desiredWidth, int desiredHeight) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        int cameraId = 0;
        int numCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numCameras; i++) {
            Camera.getCameraInfo(i, info);
            if (info.facing == cameraType) {
                cameraId = i;
                mCamera = Camera.open(i);
                currentCameraType = cameraType;
                break;
            }
        }
        CameraUtils.setCameraDisplayOrientation(this, cameraId, mCamera);
        Camera.Parameters parameters = mCamera.getParameters();


        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO))
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);

        int[] closetFramerate = CameraUtils.closetFramerate(parameters, 30);
        parameters.setPreviewFpsRange(closetFramerate[0], closetFramerate[1]);

        CameraUtils.choosePreviewSize(parameters, desiredWidth, desiredHeight);
        mCamera.setParameters(parameters);
    }
}