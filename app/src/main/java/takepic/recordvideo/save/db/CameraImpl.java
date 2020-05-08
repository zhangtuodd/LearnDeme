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
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.base.util.CameraUtils;
import com.example.base.util.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static takepic.recordvideo.save.db.CustomCameraActivity.TAG;

public class CameraImpl implements CameraApi {

    private int screenHeight, screenWidth;
    private Camera mCamera;
    private SurfaceView surfaceView;
    private Context context;
    private int orientation;

    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;//为0

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    @Override
    public Camera createCamera(int cameraId) {
        if (CameraUtils.checkCameraHardware()) {
            mCamera = CameraUtils.getCameraInstance(cameraId);
            int numberOfCameras = CameraUtils.getNumberOfCameras();
            LogUtils.i(TAG, "numberOfCameras------------" + numberOfCameras);

            Camera.Parameters parameters = mCamera.getParameters();

//            List<Camera.Size> supportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
//            for (int i = 0; i < supportedPreviewSizes.size(); i++) {
//                Camera.Size size = supportedPreviewSizes.get(i);
//                LogUtils.i(TAG, "PreviewSizes----" + size.width + "*" + size.height);
//            }

            //设置预览格式
            parameters.setPreviewFormat(ImageFormat.NV21);

//            mCamera.cancelAutoFocus();
            //判断是否支持连续自动对焦图像
            if (isSupportFocus(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE, parameters)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                //判断是否支持单次自动对焦
            } else if (isSupportFocus(Camera.Parameters.FOCUS_MODE_AUTO, parameters)) {
                //自动对焦(单次)
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }

            CameraUtils.setPictureSize(parameters);
            CameraUtils.setPreviewSize(parameters);
            mCamera.setParameters(parameters);
            //android3.0之前版本需要
            //mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            return mCamera;

        } else {
            return null;
        }

    }

    /**
     * 判断是否支持对焦模式
     *
     * @return
     */
    private boolean isSupportFocus(String focusMode, Camera.Parameters parameters) {
        boolean isSupport = false;
        //获取所支持对焦模式
        List<String> listFocus = parameters.getSupportedFocusModes();
        for (String s : listFocus) {
            //如果存在 返回true
            if (s.equals(focusMode)) {
                isSupport = true;
            }

        }
        return isSupport;
    }


    @Override
    public void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    @Override
    public void startPreview() {
        try {
            //根据所传入的SurfaceHolder对象来设置实时预览
            mCamera.setPreviewDisplay(surfaceView.getHolder());

            //调整预览角度
            setCameraDisplayOrientation(mCameraId, mCamera);

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
            //开启人脸检测
//            startFaceDetect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void capturePic() {
        mCamera.takePicture(null, null, mPicture);
    }

    @Override
    public void savePicToFile() {

    }

    /**
     * 旋转图片
     * 发现拍照后存储的照片经过逆时针90度旋转，需要将顺时针90度，原因在上面分析orientation的时候讲述过，
     * 虽然调整来预览图像角度，但是并不能调整图片传感器的图片方向，所以只能保存图片后再将图片旋转：
     *
     * @param cameraId 前置还是后置
     * @param path     图片路径
     */
    @Override
    public void rotateImageView(int cameraId, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();
        //0是后置
        if (cameraId == 0) {
            if (orientation == 90) {
                matrix.postRotate(90);
            }
        }
        //1是前置
        if (cameraId == 1) {
            //顺时针旋转270度
            matrix.postRotate(270);
        }
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        File file = new File(path);
        //重新写入文件
        try {
            // 写入文件
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            //默认jpg
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            resizedBitmap.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "保存并旋转成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void recordVideo() {
        if (isRecording) {
            // stop recording and release camera
            mediaRecorder.stop();  // stop the recording
            releaseMediaRecorder(); // release the MediaRecorder object
            mCamera.lock();         // take camera access back from MediaRecorder

            // inform the user that recording has stopped
//                            setCaptureButtonText("Capture");
            isRecording = false;
        } else {
            // initialize video camera
            if (prepareVideoRecorder(mCameraId)) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
                mediaRecorder.start();

                // inform the user that recording has started
//                                setCaptureButtonText("Stop");
                isRecording = true;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                // inform user
            }
        }
    }

    @Override
    public void flash() {

    }

    @Override
    public void switchCamera() {
        //先释放资源
        releaseCamera();
        //在Android P之前 Android设备仍然最多只有前后两个摄像头，在Android p后支持多个摄像头 用户想打开哪个就打开哪个
        mCameraId = (mCameraId + 1) % Camera.getNumberOfCameras();
        //打开摄像头
        //如果支持
        Camera.open(mCameraId);
        mCamera = createCamera(mCameraId);
        startPreview();
    }

    @Override
    public void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    @Override
    public boolean prepareVideoRecorder(int cameraId) {

        if(mCamera == null){
            createCamera(cameraId);
            setCameraDisplayOrientation(mCameraId, mCamera);
        }

        mediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mediaRecorder.setCamera(mCamera);

        // Step 2: Set sources
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        // Step 4: Set output file
        mediaRecorder.setOutputFile(CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());

        // Step 5: Set the preview output
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());

        // Step 6: Prepare configured MediaRecorder
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            LogUtils.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            LogUtils.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    @Override
    public void setSurfaceView(SurfaceView surfaceView, Context context) {
        this.surfaceView = surfaceView;
        this.context = context;
    }


    /**
     * 保证预览方向正确  //默认情况下，镜头方向和屏幕方向是相反的，这个设置保持一直是正像展示
     *
     * @param cameraId 相机Id
     * @param camera   相机
     */
    private void setCameraDisplayOrientation(int cameraId, Camera camera) {
        Camera.CameraInfo info =
                new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        //rotation是预览Window的旋转方向，对于手机而言，当在清单文件设置Activity的screenOrientation="portait"时，
        //rotation=0，这时候没有旋转，当screenOrientation="landScape"时，rotation=1。
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            int rotation = activity.getWindowManager().getDefaultDisplay()
                    .getRotation();
            int degrees = 0;
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break;
                case Surface.ROTATION_90:
                    degrees = 90;
                    break;
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;
            }

            int result;
            //计算图像所要旋转的角度
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = (info.orientation + degrees) % 360;
                result = (360 - result) % 360;  // compensate the mirror
            } else {  // back-facing
                result = (info.orientation - degrees + 360) % 360;
            }
            orientation = result;
            //调整预览图像旋转角度
            camera.setDisplayOrientation(result);
        } else {
            LogUtils.i(TAG, "context not instanceof Activity");
        }

    }


    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

            // 旋转只能根据文件来，必须先将原图片写入file
            rotateImageView(0, pictureFile.getPath());

            //拍照后记得调用预览方法，不然会停在拍照图像的界面
            mCamera.startPreview();
        }
    };
}
