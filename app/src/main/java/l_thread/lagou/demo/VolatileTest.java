package l_thread.lagou.demo;

public class VolatileTest {
    volatile int a = 1;
    volatile int b = 2;

    public void change() {
        a = 3;
        b = a;
    }

    public void print() {
        if ((b == 2 && a == 3)) {
            System.out.println("b=" + b + ";a=" + a);
        }
    }

    public static void main(String[] args) {
        while (true) {
            final VolatileTest test = new VolatileTest();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }
    }
}
