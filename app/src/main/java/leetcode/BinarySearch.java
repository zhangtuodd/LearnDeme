package leetcode;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/11/3
 */

class BinarySearch {

    public static void main(String[] args) {
        int[] array = {3,6,9};
    }

    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }
}
