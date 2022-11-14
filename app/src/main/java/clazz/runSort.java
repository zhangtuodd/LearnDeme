package clazz;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/14
 * 子类父类相关执行顺序：实例变量 、代码块、构造
 * 子类或者父类中：m = [实例变量/代码块 -> 构造函数]
 * 整体为：父类m -> 子类m
 * 如果有静态则：父类静态 -> 子类静态 -> 父类m -> 子类m
 * 注；代码块和变量顺序取决于代码书写顺序
 */

public class runSort {
   public static void main(String[] args) {
      Child child = new Child();
   }
}

 class Parent {
   static {
      System.out.println("Parent static block");
   }

   {
      System.out.println("Parent non static block");
   }

   final static Value i = new Value("Parent static value");

   Value j = new Value("Parent non static value");

   Parent() {
      System.out.println("Parent Constructor");
   }
}

 class Child extends Parent {
   static {
      System.out.println("Child static block");
   }

   final static Value i = new Value("Child static value");

   {
      System.out.println("Child non static block");
   }

   Value j = new Value("Child non static value");

   Child() {
      System.out.println("Child Constructor");
   }
}

class Value {
   public Value(String value) {
      System.out.println(value);
   }
}

