package leetcode.sort;

import android.util.Log;

import java.util.Arrays;

/**
 * 冒泡排序：
 * 每轮相邻两个元素比大小，每轮在数组的最后端产生一个最大值（比较的元素中）
 * 优化方向：
 * 1，若一轮比较中无位置交换发生，说明已经排好序
 * 2，后段已经排好序的无须再次比较排序（内循环中记录最后一次交换的位置最为内循环新的数组大小）
 */
public class bubble {
    public static void main(String[] args) {
        int[] array = {5, 8, 6, 3, 9, 2, 1, 1, 7};
        sort(array);
        System.out.printf("array:" + Arrays.toString(array));
    }

    /**
     * 每轮排序会将最大数放在最右侧，比较n-1轮
     *
     * @param array
     */
    public static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }

        }

    }

    /**
     * 针对上一种如果已经排好序还是会去比较
     *
     * @param array
     */
    public static void sort1(int[] array) {

        for (int i = 0; i < array.length; i++) {

            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    isSorted = false;
                }

            }

            if (isSorted) {//如果一轮中未发生位置交换，则说明排好序轮
                break;
            }
        }
    }

    /**
     * 如果后面部分已经排好序，则无须在比较，如34215678
     * 主要需记录排序好的边界
     *
     * @param array
     */
    public static void sort2(int[] array) {

        //无须数列的边界，每次只比较到这里为止
        int sortBorder = array.length - 1;

        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        for (int i = 0; i < array.length; i++) {

            boolean isSorted = true;

            for (int j = 0; j < sortBorder; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    isSorted = false;

                    //最后一次交换元素的位置
                    lastExchangeIndex = j;

                }
            }

            sortBorder = lastExchangeIndex;

            if (isSorted) {//如果一轮中未发生位置交换，则说明排好序轮
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序法，
     */
    public static void sort3() {

    }


}
