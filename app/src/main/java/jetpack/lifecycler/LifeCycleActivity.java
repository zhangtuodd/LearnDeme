package jetpack.lifecycler;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.base.util.LogUtils;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "MyObserver";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new MyObserver());


    }

    @Override
    protected void onResume() {
        super.onResume();
        Lifecycle.State currentState = getLifecycle().getCurrentState();
        LogUtils.e(TAG, "currentState---"+currentState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
