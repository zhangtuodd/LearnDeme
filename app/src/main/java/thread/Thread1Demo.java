package thread;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/11
 */


public class Thread1Demo {
   Object object = new Object();
   public static void main(String[] args) {

      Thread1Demo thread1Demo = new Thread1Demo();

      thread1Demo.test();

   }

   int aaa = 0;
   public void test(){
      Thread threadA =  new Thread() {
         @Override
         public void run() {
            super.run();
            print("AAA 00   -----    name:"+this.getName(), 0);
         }
      };


      Thread threadB =  new Thread() {
         @Override
         public void run() {
            super.run();
            print("BBB 00  ----     name:"+this.getName(), 1);
         }
      };
      Thread threadC =  new Thread() {
         @Override
         public void run() {
            super.run();
            print("CCC 00  ----     name:"+this.getName(), 2);
         }
      };
      threadA.start();
      threadB.start();
      threadC.start();

      Thread thread2 =  new Thread() {
         @Override
         public void run() {
            super.run();
            while (true) {
               try {
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               System.out.println("AAA   " +threadA.getState());
               System.out.println("BBB   " +threadB.getState());
            }

         }
      };
//      thread2.start();

   }

   int num = 3;
   private void print(String name, int index) {
      while (num<16) {
//         try {
//            Thread.sleep(100);
//         } catch (InterruptedException e) {
//            e.printStackTrace();
//         }
         synchronized (object) {
            while (num % 3 != index) {
               try {
                  object.wait();
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
            num++;
            System.out.println(name + "   "+ Thread.currentThread().getState() +"  ---this:"+this.getClass());
            object.notifyAll();
         }
      }

   }
}