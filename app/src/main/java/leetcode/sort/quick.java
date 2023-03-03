package leetcode.sort;

import java.util.Arrays;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-17
 */
public class quick {

    public static void main(String[] args) {
        int[] array = {5, 0, 6, 3, 9, 2, 1, 1, 7};
        System.out.printf("原始array:" + Arrays.toString(array));
        sort(array, 0, array.length - 1);
        System.out.printf("快排array:" + Arrays.toString(array));
    }

    private static void sort2(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex || array == null) {
            return;
        }
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (pivot > array[i]) {
                mark++;
                if (mark == i) {
                    continue;
                }

                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;

        sort2(array, startIndex, mark - 1);
        sort2(array, mark + 1, endIndex);

    }

    private static void sort1(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (pivot > array[i]) {
                mark++;

                if (mark == i) {
                    continue;
                }

                int temp = array[i];
                array[i] = array[mark];
                array[mark] = temp;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;

        sort1(array, startIndex, mark - 1);
        sort1(array, mark + 1, endIndex);


    }

    private static int getMark(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                if (mark == i) {
                    continue;
                }
                int temp = array[i];
                array[i] = array[mark];
                array[mark] = temp;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark = mark + 1;
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    //实现以第一个数为界限，大于该数放右边，小于该数放左边
    private static void sort(int[] array) {
        int pivot = array[0];
        int mark = 0;

        //具体实现：基准值保持不变，比基准值大的不做交换，比基准值小的数和 哨兵位置交换
        //循环完成后，再将哨兵位置元素和起始位置交换
        for (int i = 1; i < array.length; i++) {
            if (array[i] < pivot) {
                mark++;
                System.out.println("mark---" + mark);
                if (mark == i) {
                    continue;
                }
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }
        }
        array[0] = array[mark];
        array[mark] = pivot;

    }

    private static void sort(int[] array, int startIndex, int endIndex) {
        if (array == null || array.length < 2) {
            return;
        }
        //递归结束
        if (startIndex >= endIndex) {
            return;
        }
        //得到基准元素
        int pivotIndex = partition(array, startIndex, endIndex);
        sort(array, startIndex, pivotIndex - 1);
        sort(array, pivotIndex + 1, endIndex);

    }


}
