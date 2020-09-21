package jetpack.lifecycler;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

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
