package takepic.recordvideo.save.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.base.util.LogUtils;
import com.example.base.util.SizeUtils;
import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 预览
 * 拍照 - 保存
 * 录制 - 合成音视频 - 保存
 * 创建db - 保存
 */
public class TakePicRecordActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "TakePicRecordActivity";
    private Button mTake_pic;
    private Button mBtn_record;
    private Button mBtn_preview;
    private SurfaceView mSurface_view;
    private ImageView imageView;
    private VideoView videoView;
    private String currentPhotoPath;
    private File imageFile;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    //直接拍照获取缩略图，intent时不提供url，onActivityResult通过data来获取缩略图
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    //拍照intent跳转时并提供uri(7.0前后)，拍照后图片直接保存在uri中，onActivityResult中的data返回为空
    private static final int REQUEST_TAKE_PHOTO = 2;

    //录制视频
    private static final int REQUEST_VIDEO_CAPTURE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepic_record);

        mTake_pic = (Button) findViewById(R.id.take_pic);
        mBtn_record = (Button) findViewById(R.id.btn_record);
        mBtn_preview = (Button) findViewById(R.id.btn_preview);
        mTake_pic.setOnClickListener(this);
        mBtn_record.setOnClickListener(this);
        mBtn_preview.setOnClickListener(this);
        mSurface_view = findViewById(R.id.surface_view);

        mHolder = mSurface_view.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                LogUtils.i(TAG, "surfaceCreated----------");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                LogUtils.i(TAG, "surfaceChanged--width-------" + width + ";  height-------" + height);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                LogUtils.i(TAG, "surfaceDestroyed----------");
                if (mCamera != null) {
                    // Call stopPreview() to stop updating the preview surface.
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
            }
        });

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        int width = SizeUtils.getScreenWidth(this);
        int height = SizeUtils.dip2px(this, 500);
        int realWidth = 0;
        int realHeight = 0;
        if ((height / width) > (16f / 9)) {
            realHeight = height;
            realWidth = (int) (height / 16f * 9);
        } else {
            realWidth = width;
            realHeight = (int) (width * 16f / 9);
        }


        //media要求
        mSurface_view.setLayoutParams(new LinearLayout.LayoutParams(realWidth, realHeight));
//        mSurface_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        imageView = findViewById(R.id.imageView);

        videoView = findViewById(R.id.videoView);

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //这张来自 "data" 的缩略图可能适用于某个图标，需要完整图片尺寸需要保存本地
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);


        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            LogUtils.i(TAG, "currentPhotoPath---------" + currentPhotoPath);
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                saveBitmapToGallery(imageFile, currentPhotoPath);
            }


        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            videoViewShow();
            Uri videoUri = data.getData();
            String realFilePath = getRealFilePath(TakePicRecordActivity.this, videoUri);
            LogUtils.i(TAG, "realFilePath------------------" + realFilePath);
            //  4.  videoview 的设置
            //  4.1  获取MediaController对象，控制媒体播放，这里应该是获取 android.widget.MediaController 的对象
            MediaController mediaController = new MediaController(this);
            //  4.2  绑定到 Video View
            videoView.setMediaController(mediaController);

            videoView.setVideoPath(realFilePath);
            //  4.4  开始播放
            videoView.start();
            //  4.5  获取焦点
            videoView.requestFocus();


        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                Uri photoURI;
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    photoURI = FileProvider.getUriForFile(this,
                            "com.example.zhangtuo.learndeme",
                            photoFile);
                } else {
                    photoURI = Uri.fromFile(photoFile);
                }

                //拍照时给传递存储路径后拍完后图片会直接存到对应url中，而onActivityResult中data为null，不会保留任何缩略图
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    public void saveBitmapToGallery(File file, String fileName) {
        //把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            mediaScanIntent.setData(uri);
                            sendBroadcast(mediaScanIntent);
                        }
                    });
        } else {
            String relationDir = file.getParent();
            File file1 = new File(relationDir);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
        }
    }


    /**
     * 录制视频
     */
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.take_pic:
                dispatchTakePictureIntent();
                imageShow();
                break;

            case R.id.btn_record:
                dispatchTakeVideoIntent();
                break;

            case R.id.btn_preview:
                surfaceShow();
                preview();
                break;

            default:
                break;

        }
    }

    private void preview() {
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        for (int i = 0; i < supportedPreviewSizes.size(); i++) {
            Camera.Size size = supportedPreviewSizes.get(i);
            LogUtils.i(TAG, "size-------" + size.width + "*" + size.height);
        }

        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    private void surfaceShow() {
        mSurface_view.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
    }

    private void imageShow() {
        mSurface_view.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
    }

    private void videoViewShow() {
        mSurface_view.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.VISIBLE);
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
