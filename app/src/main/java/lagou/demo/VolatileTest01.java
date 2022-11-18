package lagou.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对于原子性，需要强调一点，也是大家容易误解的一点：对volatile变量的单次读/写操作可以保证原子性的，
 * 如long和double类型变量，但是并不能保证i++这种操作的原子性，因为本质上i++是读、写两次操作。
 * <p>
 * i++其实是一个复合操作，包括三步骤：读取i的值。对i加1。将i的值写回内存。
 * volatile是无法保证这三个操作是具有原子性的，我们可以通过AtomicInteger或者Synchronized来保证+1操作的原子性。
 * 注：上面几段代码中多处执行了Thread.sleep()方法，目的是为了增加并发问题的产生几率，无其他作用
 * * ------
 * * 著作权归@pdai所有
 * * 原文链接：https://pdai.tech/md/java/thread/java-thread-x-key-volatile.html
 */
public class VolatileTest01 {

    //    volatile int i;
//    public void addI(){
//        i++;
//    }


//    int i;
//    public synchronized void addI() {
//        i++;
//    }

    AtomicInteger i = new AtomicInteger();
    public void addI() {
        i.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest01 test01 = new VolatileTest01();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test01.addI();
                }
            }).start();
        }
        Thread.sleep(11000);//等待10秒，保证上面程序执行完成
        System.out.println(test01.i);
    }
}
