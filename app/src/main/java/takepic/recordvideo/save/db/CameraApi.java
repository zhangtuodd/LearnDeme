package takepic.recordvideo.save.db;


import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public interface CameraApi {

    Camera createCamera(int cameraId);

    void releaseCamera();

    void startPreview();

    void capturePic();

    void savePicToFile();

    void rotateImageView(int cameraId, String path);

    void recordVideo();

    void flash();

    void switchCamera();

    void releaseMediaRecorder();

    boolean prepareVideoRecorder(int cameraId);

    void setSurfaceView(SurfaceView surfaceView,Context context);
}
