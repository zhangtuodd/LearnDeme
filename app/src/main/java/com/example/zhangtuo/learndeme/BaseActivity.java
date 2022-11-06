package com.example.zhangtuo.learndeme;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;;

import com.umeng.analytics.MobclickAgent;

import widget.ProgressDialog;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/3/19
 */

public class BaseActivity extends Activity {
    private ProgressDialog dialog;

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void showProgress() {
        App.mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(mActivity);
                }
                dialog.show();
            }
        });
    }

    public void hideProgress() {
        App.mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
