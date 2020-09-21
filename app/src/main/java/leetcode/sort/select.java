package leetcode.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class select {

    public static void main(String[] args) {
        int[] array = {5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println("原始array:" + Arrays.toString(array));
        sort1(array);
        System.out.println("排后array:" + Arrays.toString(array));
    }

    /**
     * 选择排序
     * 每一轮找出排序元素的最小值以及最小值的位置
     * 找出后将最小值位置和当前轮比对的第一个位置进行交换。
     *
     * @param array
     */
    private static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {

            int minIndex = i;
            int minData = array[i];
            for (int j = i; j < array.length - 1; j++) {

                if (minData > array[j + 1]) {
                    minData = array[j + 1];
                    minIndex = j + 1;
                }
            }
            array[minIndex] = array[i];
            array[i] = minData;

            System.out.println("array:" + Arrays.toString(array));
        }
    }


    /**
     * 选择排序
     * 每一轮找出排序元素的最小值以及最小值的位置 和 最大值以及最大值位置
     * 找出后将最小值位置和当前轮比对的首位的值做交换，最大值和末位值做交换
     *  垃圾方案
     * @param array
     */
    private static void sort1(int[] array) {

        int left = 0;
        int right = array.length - 1;
        int minIndex = 0;//存储最小值的下标
        int maxIndex = 0;//存储最大值的下标

//        int[] array = {1, 8, 6, 3, 9, 2, 1, 7};
        while (left < right) {

            int min = array[left];
            int max = array[right];
            for (int i = left; i <= right; i++) {

                if (min > array[i]) {//找本轮最小
                    min = array[i];
                    minIndex = i;
                }

                if (array[i] > max) {//找出本轮最大值
                    max = array[i];
                    maxIndex = i;
                }

            }

            if (array[minIndex] != array[left]) {
                array[minIndex] = array[left];
                array[left] = min;
                left = left + 1;
            }

            if (array[maxIndex] != array[right]) {
                array[maxIndex] = array[right];
                array[right] = max;
                right = right - 1;
            }

            System.out.println("过程array:" + Arrays.toString(array));

        }

    }

    /**
     * 垃圾方案
     * @param arr
     */
    public static void sort2(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {//排序的趟数
            int max = i;//记录最大值得下标
            int min = i;
            for (int j = 1 + i; j < len - i - 1; j++) {//比较元素的次数，左边+，右边-
                if (arr[max] < arr[j]) {
                    max = j;
                }
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (arr[max] != arr[len - i - 1]) {//如果最大的数，没有在对应的位置才进行交换
                int tmp = arr[len - i - 1];
                arr[len - i - 1] = arr[max];
                arr[max] = tmp;
            }
            if (arr[min] != arr[i]) {//如果最小的数，没有在其对应的位置才进行交换
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
            System.out.println("过程array:" + Arrays.toString(arr));
        }
    }

}
