package l_thread.final_;

import java.util.Random;

/**
 * private所修饰的方法是隐式的final，也就是无法被继承，所以更不用说是覆盖了
 */
public class Father {
    final int a = new Random().nextInt();
    private void test() {
    }
}

class Son extends Father {
    public void test() {
    }
    public static void main(String[] args) {
        Son son = new Son();
        Father father = son;
//        father.test();
    }
}
