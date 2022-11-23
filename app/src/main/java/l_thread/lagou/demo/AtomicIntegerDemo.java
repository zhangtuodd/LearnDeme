package l_thread.lagou.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++其实是一个复合操作，包括三步骤：读取i的值。对i加1。将i的值写回内存。 volatile是无法保证这三个操作是具有原子性的，
 * 我们可以通过AtomicInteger或者Synchronized来保证+1操作的原子性。
 * ------
 * 著作权归@pdai所有
 * 原文链接：https://pdai.tech/md/java/thread/java-thread-x-key-volatile.html
 */
public class AtomicIntegerDemo implements Runnable {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    //增加指定数量
    public void getAndAdd() {
        atomicInteger.getAndAdd(-90);
    }
    //增加1
    public void getAndIncrement() {
        atomicInteger.getAndIncrement();
    }
    //减少1
    public void getAndDecrement() {
        atomicInteger.getAndDecrement();
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo r = new AtomicIntegerDemo();
        Thread t1 = new Thread(r);
        t1.start();
        System.out.println("aaaaaaaaaaaaaa");
        t1.join();//等待这个线程结束。
        System.out.println("AtomicInteger操作结果：" + atomicInteger.get());
//        ThreadLocal
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}