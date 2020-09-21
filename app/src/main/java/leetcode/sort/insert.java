package leetcode.sort;

import java.util.Arrays;

public class insert {
    public static void main(String[] args) {
        int[] array = {5, 8, 6, 3, 9, 2, 1, 1, 7};
        System.out.println("原始array:" + Arrays.toString(array));
        sort(array);
        System.out.println("排后array:" + Arrays.toString(array));
    }


    /**
     * 要插入的元素和前一个元素进行比较，插入到合适的位置
     * 第一次循环用第二个位置的元素作为插入点，向前比对
     * <p>
     * 1，3，5，2（记录插入值2）
     * 1，3，5，5
     * 1，3，3，5
     *
     * @param array
     */
    private static void sort(int[] array) {
        if (array.length > 1) {

            int temp = 0;
            int j = 0;
            for (int i = 1; i < array.length; i++) {

                temp = array[i];//记录插入值


                for (j = i; j > 0 && array[j - 1] > temp; j--) {//前值大于后值
                    array[j] = array[j - 1];//将前值赋予后值
                }

                array[j] = temp;//将记录值赋值到结束位置

                System.out.println("过程array:" + Arrays.toString(array));

            }
        }
    }
}
