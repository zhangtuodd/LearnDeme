package leetcode.sort;

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
        sort4(array);
        System.out.printf("array:" + Arrays.toString(array));
    }

    private static void sort4(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int temp;
        int lastExchangeIndex = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {

            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {

                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;

                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }

            sortBorder = lastExchangeIndex;

            if (isSorted) {
                break;
            }
        }

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
     * @param array
     */
    public static void sort3(int[] array) {
        //虽然每一轮会排出一个最大值，但是有可能有可能最大值前的几个也已经排好序了，
        // 借助是否交换确定排好序的数组位置
        int border = array.length - 1;
        int lastExchangeIndex = 0;


        //最后一轮可以不用比较：因此array.length-1
        for (int i = 0; i < array.length; i++) {

            //如果内循环没发生一次位置交换，说明已经排好序了，无需再循环
            boolean isSorted = true;

            //内存每一次循环最右端都是最大值，因此也不用比较，因此array.length - i
//            for (int j = 0; j < array.length - i - 1; j++) {
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                    isSorted = false;
//                }
//            }

            for (int j = 0; j < border; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }

            border = lastExchangeIndex;

            if (isSorted) {
                break;
            }
        }

    }


}
