package leet.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/22
 */

class 找两个数组中相同的最大长度 {
    public static void main(String[] args) {
        int[] arr1 = {69,53,93,37,79};
        int[] arr2 = {69,53,59,26,14};
        findLength(arr1,arr2);
    }


        public static int findLength(int[] nums1, int[] nums2) {
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            for(int i = 0; i < nums1.length; i++){
                int k = i;
                StringBuffer sb = new StringBuffer();
                while(k < nums1.length){
                    sb.append(String.valueOf(nums1[k])).append("-");
                    list1.add(sb.toString());
                    k++;
                }
            }
            System.out.println(list1.toString());

            for(int i = 0; i < nums2.length; i++){
                int k = i;
                StringBuffer sb = new StringBuffer();
                while(k < nums2.length){
                    sb.append(String.valueOf(nums2[k])).append("-");
                    list2.add(sb.toString());
                    k++;
                }
            }
            System.out.println(list2.toString());

            int maxLen = 0;
            for(String s1:list1){
                for(String s2:list2){
                    if(s1.equals(s2) && s1.length() > maxLen){
                        maxLen = s1.length();

                        System.out.println(s1);
                    }
                }
            }
            return maxLen;
        }
}
