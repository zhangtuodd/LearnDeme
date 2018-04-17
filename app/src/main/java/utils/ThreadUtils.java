package utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zhangtuo on 2018/3/19.
 */

public class ThreadUtils {


    public Type type;

    private ThreadListener listener;

    public void setThreadListener(ThreadListener listener) {
        this.listener = listener;
    }

    private ExecutorService mCachedThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;
    private ExecutorService mSingleThreadPool;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public enum Type {
        /**
         * 可灵活回收空闲线程，若无可回收，则新建线程
         */
        CACHED,

        /**
         * 它只会用唯一的工作线程来执行任务 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
         */
        CHAIN,

        /**
         * 支持定时及周期性任务执行
         */
        SCHEDULED
    }

    public ThreadUtils get(Type type) {
        ThreadUtils util = new ThreadUtils();
        util.type = type;
        return util;
    }


    public ExecutorService build(Type type) {

        ExecutorService service = null;
        switch (type) {
            case CACHED:
                if (mCachedThreadPool == null || mCachedThreadPool.isShutdown()) {
                    mCachedThreadPool = Executors.newCachedThreadPool();
                }
                service = mCachedThreadPool;
                break;
            case CHAIN:
                if (mSingleThreadPool == null || mSingleThreadPool.isShutdown()) {
                    mSingleThreadPool = Executors.newSingleThreadExecutor();
                }
                service = mSingleThreadPool;
                break;
            case SCHEDULED:
                if (mScheduledThreadPool == null || mScheduledThreadPool.isShutdown()) {
                    mScheduledThreadPool = Executors.newScheduledThreadPool(10);
                }
                service = mScheduledThreadPool;
        }

        service.execute(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.response();
                }
            }
        });
        return service;
    }

    public interface ThreadListener {
        void response();
    }

}
