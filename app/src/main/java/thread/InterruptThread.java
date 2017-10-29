package thread;

import android.util.Log;

/**
 * Created by zhangtuo on 2017/10/10.
 * <p>
 * 中断一个线程
 */

public class InterruptThread extends Thread {
    private boolean running;

    public InterruptThread(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Log.i("tag", "run--------");
        while (running) {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    public static void test() {
        InterruptThread thread = new InterruptThread(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.setRunning(false);
        Log.i("tag", "thread---------------");
    }
}
