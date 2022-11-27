package l_thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 必须先让t2先进行启动 使用wait 和 notify 进行相互通讯，wait会释放锁
 */
public class ThreadLeet2 {


    public static void main(String[] args) {

        Container t2 = new Container();

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                if (t2.getSize() != 5) {
                    try {
                        /**会释放锁*/
                        lock.wait();
                        System.out.println("t2 结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 启动");
                for (int i = 0; i < 9; i++) {
                    t2.add(i);
                    System.out.println("add" + i);
                    if (t2.getSize() == 5) {
                        /**不会释放锁*/
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}

class Container{
    volatile List list = new ArrayList();

    public void add(int i) {
        list.add(i);
    }

    public int getSize() {
        return list.size();
    }
}
