package weak;

import java.lang.ref.WeakReference;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-05
 */
public class WeakReferenceDemo {

    public static WeakReference<String> weakReference1;
    public static WeakReference<String> weakReference2;

    public static void main(String[] args) {
        test1();
        //可以输出hello值，此时两个弱引用扔持有对象，而且未进行gc
        System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

        //此时已无强一用执行"value"所在内存区域，gc时会回收弱引用
        System.gc();

        //此时输出都为nuill
        System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());
    }

    public static void test1() {
        String hello = new String("value");

        weakReference1 = new WeakReference<>(hello);

        System.gc();
        //此时gc不会回收弱引用，因为字符串"value"仍然被hello对象强引用
        System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference1.get());

    }
}
