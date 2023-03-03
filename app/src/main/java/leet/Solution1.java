package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二维数组螺旋便利
 *
 * @author zhangtuo
 * @date 2023/1/12
 */

class Solution1 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {10, 11, 12, 5},
                {9, 8, 7, 6},
        };
        int hang = arr.length;
        int row = arr[0].length;
        int i = arr[1][2];
        System.out.println("hang" + hang + "---row" + row + "---arr[1][2]" + arr[1][2]);

        int t = 0, l = 0;
        int b = hang - 1;
        int r = row - 1;
        List<Integer> list = new ArrayList<>();

        extracted(arr, hang, row, t, l, b, r, list);

        System.out.println("\n\n---list:" + list.toString());
    }

    private static void extracted(int[][] arr, int hang, int row, int t, int l, int b, int r, List<Integer> list) {
        for (int j = l; j <= r; j++) {
            list.add(arr[t][j]);
        }
        if (list.size() >= hang * row) {
            return;
        }
        t++;
        System.out.println("list:" + list.toString());
        while (list.size() < hang * row) {
            for (int j = t; j <= b; j++) {
                list.add(arr[j][r]);
            }
            if (list.size() >= hang * row) {
                return;
            }
            r--;
            System.out.println("list:" + list.toString());

            for (int j = r; j >= l; j--) {
                list.add(arr[b][j]);
            }
            if (list.size() >= hang * row) {
                return;
            }
            b--;
            System.out.println("list:" + list.toString());

            for (int j = b; j >= t; j--) {
                list.add(arr[j][l]);
            }
            if (list.size() >= hang * row) {
                return;
            }
            l++;
            System.out.println("list:" + list.toString());
        }
    }
}
