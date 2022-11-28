package l_thread;

/**
 * 哲学家进餐问题
 * https://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Java%20%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%2078%20%E8%AE%B2-%E5%AE%8C/71%20%E8%AE%B2%E4%B8%80%E8%AE%B2%E7%BB%8F%E5%85%B8%E7%9A%84%E5%93%B2%E5%AD%A6%E5%AE%B6%E5%B0%B1%E9%A4%90%E9%97%AE%E9%A2%98.md
 */
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {

        private Object leftChopstick;

        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {

            this.leftChopstick = leftChopstick;

            this.rightChopstick = rightChopstick;

        }

        @Override

        public void run() {

            try {

                while (true) {

                    doAction("思考人生、宇宙、万物、灵魂...");

                    synchronized (leftChopstick) {

                        doAction("拿起左边的筷子");

                        synchronized (rightChopstick) {

                            doAction("拿起右边的筷子");

                            doAction("吃饭");

                            doAction("放下右边的筷子");

                        }

                        doAction("放下左边的筷子");

                    }

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        private void doAction(String action) throws InterruptedException {

            System.out.println(Thread.currentThread().getName() + " " + action);

            Thread.sleep((long) (Math.random() * 1000));

        }

    }

    public static void main(String[] args) {

        Philosopher[] philosophers = new Philosopher[5];

        Object[] chopsticks = new Object[philosophers.length];

        for (int i = 0; i < chopsticks.length; i++) {

            chopsticks[i] = new Object();

        }

        for (int i = 0; i < philosophers.length; i++) {

            Object leftChopstick = chopsticks[i];

            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];

//            philosophers[i] = new Philosopher(rightChopstick, leftChopstick);

            //添加这个条件，打破拿筷子规则，
            if (i == 2) {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            } else {
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            }

            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();

        }

    }

}