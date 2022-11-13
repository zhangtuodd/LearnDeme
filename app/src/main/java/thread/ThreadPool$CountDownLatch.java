package thread;

import static java.lang.Thread.sleep;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/10
 */

class ThreadPool$CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                example.add();
                System.out.println("------"+example.get());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

}

class ThreadUnsafeExample {

    private volatile int cnt = 0;

//    synchronized
    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }
}
