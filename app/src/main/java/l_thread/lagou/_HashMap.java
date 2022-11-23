package l_thread.lagou;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/16
 */

class _HashMap {
   public static void main(String[] args) {
      HashMap hashMap = new HashMap();
      ReflectUtils.getFildValue(hashMap);
   }
}

 class ReflectUtils {
   public static void getFildValue(Object object) {
      Field[] fields = object.getClass().getDeclaredFields();
      System.out.println("----------------------start----------------------------------------------------------------------------");
      System.out.println("class :" + object.getClass());
      for(Field f : fields){
         f.setAccessible(true);
         int mod = f.getModifiers();

         Object o = null;
         try {
            o = f.get(object);
            System.out.println( Modifier.toString(mod) + " " + f.getType().getName() + " " + f.getName() + " == " + o);
            if (o != null) {
               Class<?> type = o.getClass();
               if (type.isArray()) {
                  System.out.println("是数组："+type.isArray());
                  Class<?> elementType = type.getComponentType();
                  System.out.println("Array of: " + elementType);
                  System.out.println("Array length: " + Array.getLength(o));
               }
            }
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }
         System.out.println("------------------------");
      }
      System.out.println("----------------------end-------------------------------------------------------------------------");
   }

    public static void getFildValueNoFinal(Object object) {
       Field[] fields = object.getClass().getDeclaredFields();
       System.out.println("----------------------start----------------------------------------------------------------------------");
       System.out.println("class :" + object.getClass());
       for(Field f : fields){
          f.setAccessible(true);

          int mod = f.getModifiers();
          String modifierString = Modifier.toString(mod);
          if (!modifierString.contains("final")){
             Object o = null;
             try {
                o = f.get(object);
                System.out.println(modifierString + " " + f.getType().getName() + " " + f.getName() + " == " + o);
                if (o != null) {
                   Class<?> type = o.getClass();
                   if (type.isArray()) {
                      Class<?> elementType = type.getComponentType();
                      System.out.println("Array of: " + elementType);
                      System.out.println("Array length: " + Array.getLength(o));
                   }
                }
             } catch (IllegalAccessException e) {
                e.printStackTrace();
             }
             System.out.println("------------------------");
          }
       }
       System.out.println("----------------------end-------------------------------------------------------------------------");

    }
}
