package jetpack.lifecycler;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.base.util.LogUtils;

public class MyObserver implements LifecycleObserver {

    private static final String TAG = "MyObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onConnection(){
        LogUtils.e(TAG, "onConnection---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDisConnection(){
        LogUtils.e(TAG, "onDisConnection---");
    }
}
