package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/3
 */

class GenericTest<T> {
    T tt = null;

    public static void main(String[] args) {

    }

    ArrayList<String> a = new ArrayList<>();

    void add() {
//        a.add(1);
//        a.add("");
    }

    //泛型方法,不依赖与泛型类，独立使用，方法内的T和类中的T可以不是一个类型
    public <T> void setValue(T t) {
//        this.tt = t;
        if (t instanceof String) {

        }
    }


    private void test() {
        ArrayList<String> stringList = new ArrayList<String>();

        ArrayList<Object> objList = new ArrayList<Object>();

//        ArrayList<Object> qq = new ArrayList<String>(); //--报错
//        ArrayList<String> ww = new ArrayList<Object>(); //--报错

        /**
         * 1.以下两种操作都编译不通过，因为java 把泛型类型定义为不可变，<String>和<Object>不是同一种类型
         */
//        objList = stringList;
//        stringList = objList;

        /**
         * 针对1的情况，现实中确实有需要把stringList赋值给objList，怎么操作呢？
         * 看以下代码。
         *
         * 解释：
         *  ? extends 通配符使得泛型可以协变，也就是说 string类型的列表可以视为object类型的子列表，因此变得合法，通过协变使得泛型对数据类型的约束变得宽松
         *  协变的代价是：不能再往协变的数据类型中添加数据了。obj1List.add("");-报错。
         */
        ArrayList<? extends Object> obj1List = new ArrayList<Object>();
        obj1List = stringList;

        Object o = obj1List.get(0);
//        obj1List.add("");报错

    }

    public static void covariantGeneric() {
        List<? extends Animal> objList = new ArrayList<Dog>() {{
            add(new Dog());
            add(new Dog());
        }};
        Animal animal = objList.get(0); // no
//        Dog dog = objList.get(0);
//        objList.add(new Animal());
//        objList.add(new Dog());
    }

}

class Animal {
}

class Dog extends Animal {
}
