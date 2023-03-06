package generic.a1;

import android.view.View;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clazz.T;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/3/3
 */

class genericTest<T> {
    public static void main(String[] args) {
        GModel<? extends Number> model  = new GModel<Number>();
//        model.add(1);
//        model.add(Long.valueOf("111111111"));
        model.print();
        model.get();


        ArrayList<? extends Number> list1 = new ArrayList<Integer>(); //子类
//        list1.add(1);    //编辑报错，因为通配符?可能是T或T的子类
        list1.add(null); //null可以，因为null可以为任何类型，但无意义

        Number number = list1.get(0); //默认是T类型，从声明泛型集合时我们知道，可以声明T和T的子类任何一个，所以返回默认只能是T，如果是子类再强转
        Integer integer = (Integer) list1.get(0); //可以强转成子类型，但如果不是Integer会包运行错误java.lang.ClassCastException

        /**
         * 下界通配符，通配符?只能是 T 和 T 的父类
         * add(): 只能添加类型为 T 和 T 的子类
         * get():返回的值默认是 Object类型
         */
        ArrayList<? super Number> list2 = new ArrayList<Number>();
        list2.add(new Integer(1));
        list2.add(new Long(1));
        list2.add(1.1f);
        list2.add(2.5d);

        Object object1 = list2.get(0);
        Integer object = (Integer) list2.get(0); //默认是Object类型，因为在add()时可以是T和T的子类，所以返回默认是Object

//        GModel<Integer> model  = new GModel<Integer>();
//        GModel<Long> model1  = new GModel<Long>();

//        System.out.println(model.equals(model1));//false
//        System.out.println(model.getClass().equals(model1.getClass()));//true ,编译时把泛型擦除了

//        System.out.println(model.getTClass());
//        System.out.println(model1.getTClass());





        //类型推断是JDK1.7之后推出的，而泛型是1.5推出的，所以在1.7之前使用泛型都必须写完成的泛型.
//例如ArrayList<String> = new ArrayList<String>();
        class Book<T> {
            public <U> void readBook(U book){}
        }
//完整的创建泛型类
        Book<String> book = new Book<String>();
//类型判断之后可以这样写，不需要再创建实例的时候再去参数化泛型
        Book<String> book1 = new Book<>();

//完整的使用泛型方法
        book.<String>readBook("123");
//类型推断之后可以这样写
        book.readBook("123");

    }


    public void test(ArrayList<T> list){
        // 因为add的参数是 T 所以不能使用，因为不知道add的类型是什么
//        list.add("123");
        list.size();
        list.add(null);
        list.get(0);
        list.contains(2);

        for (Object o : list) {
            System.out.println(o);
        }

        /**
         * 泛型优点：
         * 1、编译期类型检查，
         * 2、避免类型强转(编译器内部帮我们做了推断和强转)
         * 3、类型参数化，不同类型参数执行相同逻辑，方便代码复用
         */
        List l1 = new ArrayList(); //因为没引入泛型
        l1.add(1);// 编译通过
        l1.add("ss"); // 编译通过
        l1.get(0); // 编译通过

        List<T> l2 = new ArrayList<>(); // 引入泛型
//        l2.add(1); // todo 编译失败。。因为不确定类型
        l2.get(0); // 编译通过

        List<?> l3 = new ArrayList<>(); // 引入泛型
//        l3.add(1); // todo 编译失败。。因为不确定类型
        l3.get(0); // 编译通过

        List<String> l4 = new ArrayList<String>(); // 引入泛型
        l4.add("4"); // 编译通过
//        l4.add(11); // todo 编译失败 ， 因为编译期间有类型检查
        l4.get(0); // 编译通过

//        List<String> l5 = new ArrayList<Object>(); //  todo 泛型类型不可变，这是为了保证运行时安全，否则
//        List<Object> l6 = new ArrayList<String>();// todo 编译失败 ， 这是为了保证运行时安全。假设这里编译通过 - l6.add(1);// 这里我们把一个整数放入一个字符串列表 String s = strs.get(0);// ！！！ ClassCastException：无法将整数转换为字符串

//        在List<? extends Fruit>的泛型集合中，对于元素的类型是Fruit或者它的子类，编译器只能知道元素是继承自Fruit，具体是Fruit的哪个子类是无法知道的。 所以「向一个无法知道具体类型的泛型集合中插入元素是不能通过编译的」。但是由于知道元素是继承自Fruit，所以从这个泛型集合中取Fruit类型的元素是可以的。
//        在List<? super Apple>的泛型集合中，元素的类型是Apple的父类，但无法知道是哪个具体的父类，因此「读取元素时无法确定以哪个父类进行读取」。 插入元素时可以插入Apple与Apple的子类，因为这个集合中的元素都是Apple的父类，子类型是可以赋值给父类型的。
//
//        有一个比较好记的口诀：
//        1.只读不可写时,使用List<? extends Fruit>:Producer，读到之后给其他地方调用所以是生产者
//        2.只写不可读时,使用List<? super Apple>:Consumer
//
//        作者：程序员江同学
//        链接：https://juejin.cn/post/6978833860284907527
//        来源：稀土掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }



}


