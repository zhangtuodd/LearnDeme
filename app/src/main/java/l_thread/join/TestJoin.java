package l_thread.join;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/20
 */

class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                System.out.println("threadA---join前-"+threadA.getState());
                threadA.join();
                System.out.println("threadA---join后-"+threadA.getState());
                System.out.println(mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
    }
}
