package leet.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/23
 */

class 最长公共前缀 {

   public static void main(String[] args) {
      List<String> list = new ArrayList<>();
      list.add("aabacc");
      list.add("aabdd");
      list.add("aaba");
      list.add("aabc");

      String s = maxPrefix(list);
      System.out.println(s);
   }

   private static String maxPrefix(List<String> list) {

      // 判空
      // 竖向扫描 ，都相同右移指针
      int point  = 0;
      while (true){
         for (int j = 0; j < list.size()-1; j++) {
            String cur = list.get(j);
            String next = list.get(j+1);
            int curLen = cur.length();
            int nextLen = next.length();
            if(curLen <= point || nextLen <= point || cur.charAt(point) != next.charAt(point)){
               System.out.println("len---:"+point);
               return cur.substring(0,point);
            }
         }
         point++;
      }

   }
}
