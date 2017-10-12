package com.example.zhangtuo.learndeme;

import android.util.Log;

/**
 * Created by zhangtuo on 2017/10/10.
 *
 * 子线程 主线程执行测试
 *
 * 子线程创建集成thread 实现Runnable
 *
 * 注意一旦调用start线程进入就绪状态，cup一旦分配资源后，就开始执行run方法
 *
 * 优先级：priority 1-10  默认5，知识理论上决定优先度
 */

public class ThreadCase {



    //主线程执行task.run()
    public void testThread2() {
        for (int i = 0; i < 4; i++) {
            Task task = new Task();
            task.run();
        }
    }

    //子线程执行
    public void testThread() {
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new Task(), "任务-" + i);
            thread.start();
        }
    }


    class Task implements Runnable {
        @Override
        public void run() {
            try {
//                Thread.sleep(new Random().nextInt(300));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("tag", Thread.currentThread().getName() + ": 到达");

        }
    }
}
