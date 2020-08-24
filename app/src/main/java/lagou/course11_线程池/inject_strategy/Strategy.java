package lagou.course11_线程池.inject_strategy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Strategy {

//   new ThreadPoolExecutor.AbortPolicy()
//   new ThreadPoolExecutor.DiscardPolicy()
//   new ThreadPoolExecutor.DiscardOldestPolicy()
//   new ThreadPoolExecutor.CallerRunsPolicy())

    public static class Main {
        public static void main(String[] args) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.DiscardOldestPolicy());
            for (int i = 0; i < 25; i++) {
                MyTask myTask = new MyTask(i);
                executor.execute(myTask);
                System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                        executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
            }
//            executor.shutdown();
        }
    }

    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "，正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}
