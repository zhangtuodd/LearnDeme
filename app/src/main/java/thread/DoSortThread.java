package thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程顺序执行
 *
 * @author zhangtuo
 * @date 2022/11/10
 */

class DoSortThread {

    public static void main(String[] args) {

    }

    int a = 0;
    Thread t1 = new Thread(){
        @Override
        public void run() {
            super.run();
            add(a);
        }
    };

    Thread t2 = new Thread(){
        @Override
        public void run() {
            super.run();
            add(a);
        }
    };

    Thread t3 = new Thread(){
        @Override
        public void run() {
            super.run();
            add(a);
        }
    };

    public void add(int a){
        a = a+1;
    }

    public void doSort(){
        CountDownLatch latch1 = new CountDownLatch(1);
        t1.start();
        latch1.countDown();
        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
