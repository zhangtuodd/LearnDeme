package l_thread.lagou;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/10/28
 *
 * 5分钟彻底理解-Java自动装箱、拆箱
 * @LINK https://juejin.cn/post/6844903641220907016
 */

class AutoBoxing {

   public static void main(String[] args) {
      testAutoBox2();
   }

   public static void testAutoBox2() {
      //1
      int a = 100;
      Integer b = 100;
      System.out.println(a == b);//true

      //2
      Integer c = 100;
      Integer d = 100;
      System.out.println(c == d);//true

      //3
      c = 101;
      d = 101;
      System.out.println(c == d);//false
   }
}
