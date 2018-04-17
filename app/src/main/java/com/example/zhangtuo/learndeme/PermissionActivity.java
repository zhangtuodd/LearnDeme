package com.example.zhangtuo.learndeme;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/12
 */

public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }

    private void checkPermission() {
        showDialog();
    }

    Dialog cameraDialog;

    private void showDialog() {
        if (cameraDialog == null) {
            cameraDialog = new Dialog(this);
            View view = View.inflate(this, R.layout.module_biz_activity_carame_dialog, null);
            view.findViewById(R.id.module_biz_activity_id_camera_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cameraDialog.isShowing()) {
                        cameraDialog.dismiss();
                    }
                }
            });
            view.findViewById(R.id.module_biz_activity_id_camera).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cameraDialog.isShowing()) {
                        cameraDialog.dismiss();
//                        testCall();
                        checkRequiredPermission(PermissionActivity.this);
                    }
                }
            });
            view.findViewById(R.id.module_biz_activity_id_select).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cameraDialog.isShowing()) {
                        cameraDialog.dismiss();
                    }
                }
            });
            cameraDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cameraDialog.setCancelable(false);
            cameraDialog.setCanceledOnTouchOutside(false);
            cameraDialog.setContentView(view);
        }
        cameraDialog.show();
    }

    //所需要申请的权限数组
    private static final String[] permissionsArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS};
    //还需申请的权限列表
    private List<String> permissionsList = new ArrayList<>();
    //申请权限后的返回码
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    private void checkRequiredPermission(PermissionActivity activity) {
        permissionsList.clear();
        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(PermissionActivity.this, "做一些申请成功的权限对应的事！" + permissions[i], Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PermissionActivity.this, "权限被拒绝： " + permissions[i], Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
//    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
//
//    public void testCall() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CALL_PHONE},
//                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
//        } else {
//            callPhone();
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    public void callPhone() {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + "10086");
//        intent.setData(data);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
//    {
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
//        {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                callPhone();
//            } else
//            {
//                // Permission Denied
//                Toast.makeText(PermissionActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }


}
