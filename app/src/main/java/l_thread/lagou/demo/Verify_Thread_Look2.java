package l_thread.lagou.demo;

/**
 * 验证可见性
 * 从本质上来说，当线程释放一个锁时会强制性的将工作内存中之前所有的写操作都刷新到主内存中去，
 * 而获取一个锁则会强制性的加载可访问到的值（个人实测为有效值【其他线程操作变量成功的有效值/如果失效则主存）到线程工作内存中来。虽然锁操作只对同步方法和同步代码块这一块起到作用，
 * 但是影响的却是线程执行操作所使用的所有字段
 *
 * @author zhangtuo
 * @date 2022/11/17
 */
public class Verify_Thread_Look2 {
    private boolean flag = false;

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public static void main(String[] args) {
        Verify_Thread_Look2 threadSafeCache = new Verify_Thread_Look2();

        Thread t1 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (threadSafeCache.getFlag()) {
                    //如果没有等待刷新则输出i为0
                    System.out.println(Thread.currentThread() + "其他线程可见了（其他线程从主存刷新到工作内存了）---"+i);
                    break;
                }
//                System.out.println( "等待刷新主存。。。");
                i++;
                /**
                 * 参考：https://juejin.cn/post/6844903749933072392
                 * println内部是加锁实现的，获取锁会强制性的加载可访问到的值（个人实测为有效值【其他线程操作变量成功的有效值/如果失效则主存）到线程工作内存中来，释放锁会强制性将工作内存所有的写操作刷新到主内存
                 * 解释了为什么加print后能读取到新值
                 */
                //System.out.println( "等待刷新主存。。。");
            }
        });



        Thread t2 = new Thread(() -> {
            threadSafeCache.setFlag(true);
//            System.out.println("值更新了---");
        });
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
