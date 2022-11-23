package l_thread.lagou.demo;

/**
 * 双重检查锁
 */
public class DoubleCheckSingleton {

    /**
     * volatile用来禁止指令重排
     * 分配内存空间 - 初始化对象 - 将内存空间的地址赋值给对应的引用。
     */
    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {//1
            synchronized (DoubleCheckSingleton.class) {//2
                //这个判空：为了防止比如三个线程都已经执行到2出，都来竞争锁，那三个线程最终都会获取到锁，就导致初始化了三次
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}