package l_thread.interrupt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        // 创建线程 1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 加锁操作
                    lock.lockInterruptibly();
                    System.out.println("线程 1:获取到锁.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 线程 1 未释放锁
            }
        });

        // 创建线程 2
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 先休眠 0.5s，让线程 1 先执行
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取锁
                try {
                    System.out.println("线程 2:尝试获取锁.");
                    lock.lock();
                    System.out.println("线程 2:获取锁成功.");
                } catch (Exception e) {
                    System.out.println("线程 2:执行已被中断.");
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        if (t2.isAlive()) { // 线程 2 还在执行
            System.out.println("执行线程的中断.");
            t2.interrupt();
        } else {
            System.out.println("线程 2:执行完成.");
        }
    }
}