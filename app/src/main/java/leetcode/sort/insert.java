package leetcode.sort;

import java.util.Arrays;

public class insert {
    public static void main(String[] args) {
        int[] array = {5, 8, 6, 3, 9, 2, 1, 1, 7};
        System.out.println("原始array:" + Arrays.toString(array));
        sort3(array);
        System.out.println("排后array:" + Arrays.toString(array));
    }

    public static void sortt(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int curIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int next = array[i + 1];
            curIndex = i;
            while (curIndex >= 0 && next < array[curIndex]) {
                int temp = array[curIndex];
                array[curIndex] = next;
                next = temp;
                curIndex = curIndex - 1;
            }
        }

    }


    private static void sort3(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int index;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            while (index >= 0 && array[index] > array[index + 1]) {
                temp = array[index + 1];
                array[index + 1] = array[index];
                array[index] = temp;
                index = index - 1;
            }
        }


    }

    private static void sort2(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int temp;
        int data;
        for (int i = 0; i < array.length - 1; i++) {
            temp = i;
            while (temp >= 0 && array[temp] > array[temp + 1]) {
                data = array[temp + 1];
                array[temp + 1] = array[temp];
                array[i] = data;
                temp = temp - 1;
            }
        }
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

//            int temp = 0;

            for (int i = 1; i < array.length; i++) {
                // 记录要插入的数据
                int tmp = array[i];

                // 从已经排序的序列最右边的开始比较，找到比其小的数
                int j = i;
                while (j > 0 && tmp < array[j - 1]) {
                    array[j] = array[j - 1];
                    j--;
                }

                // 存在比其小的数，插入
                if (j != i) {
                    array[j] = tmp;
                }

//                temp = array[i];//记录插入值
//                int j = i;
//                while (j > 0 && temp < array[j - 1]) {
//
//                    j--;
//                }
//                if (j != i){
//                    array[i] = array[j];
//                    array[j] = temp;
//                }


//                for (j = i; j > 0 && array[j - 1] > temp; j--) {//前值大于后值
//                    array[j] = array[j - 1];//将前值赋予后值
//                }
//
//                array[j] = temp;//将记录值赋值到结束位置

                System.out.println("过程array:" + Arrays.toString(array));

            }
        }
    }
}
