package synchronize;

import deep_copy.Person;

/**
 * 对象锁：前提是多线程，多个对象都可以访问加锁模块，会产生多个锁
 * 类锁：即使多线程多个对象也会阻塞，锁住该类的所有对象。只产生一个锁
 * 。。
 * 类锁实现
 * 1、静态方法句上加synchronized
 * 2、静态方法中的代码块加synchronized
 * 3、代码块中synchronized (O.class)
 *
 * 注：synchronized (this) 对象锁
 *
 * @author zhangtuo
 * @date 2020-10-14
 */
public class main {


    public static void main(String[] args) throws InterruptedException {

//        O o = new O();
//        o.doSth();
//
//        O o1 = new O();
//        o1.doSth();
//
//        O o2 = new O();
//        o2.doSth();


        new Thread(new Runnable() {
            @Override
            public void run() {
                O o = new O();
                try {
                    o.doSth();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                O o1 = new O();
                try {
                    o1.doSth();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                O o2 = new O();
                try {
                    o2.doSth();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
