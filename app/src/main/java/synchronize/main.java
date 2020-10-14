package synchronize;

import deep_copy.Person;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-10-14
 */
public class main {
    static Person person = new Person();

    public static void main(String[] args) throws InterruptedException {
        doSth();
        doSth();
        doSth();
    }

    private static void doSth() throws InterruptedException {
        int a = 1;
        synchronized (person) {
            a++;
            System.out.println(a);
            Thread.sleep(1000);
        }
    }
}
