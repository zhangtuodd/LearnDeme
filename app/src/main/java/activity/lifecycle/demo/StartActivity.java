package activity.lifecycle.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.App;
import com.example.zhangtuo.learndeme.R;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "SActivity";
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        editText = findViewById(R.id.edit);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AActivity.class));
            }
        });
        LogUtils.d(TAG, "StartActivity.onCreate---------------");

        // 获取activity任务栈
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        LogUtils.d(TAG, "info---------------"+info);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, "StartActivity.onStart---------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, "StartActivity.onResume---------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG, "StartActivity.onPause---------------");
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG, "StartActivity.onStop---------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "StartActivity.onDestroy---------------");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG, "StartActivity.onRestart---------------");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(TAG, "StartActivity.onConfigurationChanged---------------newConfig:" + newConfig.orientation);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d(TAG, "StartActivity.onNewIntent---------------");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.d(TAG, "StartActivity.onRestoreInstanceState---------------");
        String input_key = savedInstanceState.getString("input_key");
        if (TextUtils.isEmpty(input_key)) {
            input_key = "aa";
        }
        editText.setText(input_key);
        editText.setSelection(input_key.length());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d(TAG, "StartActivity.onSaveInstanceState---------------");
        String s = editText.getText().toString();
        outState.putString("input_key", s);
    }
}


