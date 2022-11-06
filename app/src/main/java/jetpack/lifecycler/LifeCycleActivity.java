package jetpack.lifecycler;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;;

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
