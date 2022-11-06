package camera_video_record.cameraDemo01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhangtuo.learndeme.R;

import java.io.File;
import java.io.IOException;


public class CameraTakePictureActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PictureCallback, View.OnClickListener {
    private static final String TAG = "CameraTakePictureActivi";

    SurfaceView mPictureSv;
    ImageView mImageView;
    Button mTakePicture;
    Button mBtnRecording;
    Button mBtnStartPreview;

    private Camera mCamera;
    private SurfaceHolder mHolder;
    private Bitmap mBitmap;
    private MediaRecorder mMediaRecorder;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_take_picture);

        mPictureSv = findViewById(R.id.picture_sv);
        mImageView = findViewById(R.id.image_view);
        mTakePicture = findViewById(R.id.take_picture);
        mTakePicture.setOnClickListener(this);
        mBtnRecording = findViewById(R.id.btn_recording);
        mBtnRecording.setOnClickListener(this);
        mBtnStartPreview = findViewById(R.id.btn_start_preview);
        mBtnStartPreview.setOnClickListener(this);
        mHolder = mPictureSv.getHolder();
//        mHolder.setFormat(PixelFormat.TRANSPARENT);
//        mHolder.addCallback(this);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void openCamera() {
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        Camera.Parameters parameters = mCamera.getParameters();
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        if (data != null) {
            Log.e(TAG, "onPictureTaken: " + data.toString());
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e(TAG, "onPointerCaptureChanged: " + hasCapture);
    }


    private void startRecording() {
        try {
            mCamera.unlock();
            mMediaRecorder = new MediaRecorder();
           /* String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/ych/123.mp4";
            new File(filePath);*/
            File file = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/1234.mp4");
            mMediaRecorder.reset();
            mMediaRecorder.setCamera(mCamera);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
//            mMediaRecorder.setVideoSize(320, 240);//每个手机的屏幕视频都不一样，需要调整
//            mMediaRecorder.setVideoFrameRate(4);
            mMediaRecorder.setVideoEncoder(MediaRecorder
                    .VideoEncoder.H264);
            mMediaRecorder.setVideoSize(1280, 720);
            // 每秒 4帧
            mMediaRecorder.setOrientationHint(90);
            mMediaRecorder.setVideoFrameRate(20);
            mMediaRecorder.setPreviewDisplay(mHolder.getSurface());  // ①
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            isRecording = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
    }

    private void stopRecording() {
        if (mMediaRecorder != null) {
            isRecording = false;
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }

    private void showImageView(byte[] data) {
        mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        mImageView.setImageBitmap(mBitmap);
        mImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.take_picture://拍照
                mCamera.takePicture(new Camera.ShutterCallback() {
                    @Override
                    public void onShutter() {
                        Log.e(TAG, "onShutter: " + "按下快门");
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        if (data != null) {
                            showImageView(data);
                            Log.e(TAG, "onPictureTaken: " + data.toString());
                        }
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        if (data != null) {
                            showImageView(data);
                            Log.e(TAG, "onPictureTaken2: " + data.toString());
                        }
                    }
                });
                break;


            case R.id.btn_recording:
                if (!isRecording) {
                    mBtnRecording.setText("停止录制");
                    startRecording();
                } else {
                    mBtnRecording.setText("开始录制");
                    stopRecording();
                }
                break;

            case R.id.btn_start_preview://预览
                openCamera();
                mImageView.setVisibility(View.GONE);
                break;


        }
    }
}
