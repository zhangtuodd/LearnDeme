package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 二维数组螺旋遍历
 *
 * @author zhangtuo
 * @date 2020-11-23
 */
public class TwoArray {

//    static HashMap<Integer, Node> hashMap = new HashMap<>(4);

    public static void main(String[] args) {

        int[][] arrays = {
                {6, 7, 8, 9, 10, 11},
                {5, 24, 25, 26, 27, 12},
                {4, 23, 34, 35, 28, 13},
                {3, 22, 33, 36, 29, 14},
                {2, 21, 32, 31, 30, 15},
                {1, 20, 19, 18, 17, 16},
        };
        int xBegin = 0;
        int xEnd = 5;
        int yBegin = 0;
        int yEnd = 5;
        while (true) {
            for (int i = yEnd; i >= yBegin; i--) {
                System.err.println(arrays[i][yBegin]);
            }
            xBegin++;
            for (int i = xBegin; i <= xEnd; i++) {
                System.err.println(arrays[yBegin][i]);
            }
            yBegin++;
            for (int i = yBegin; i <= yEnd; i++) {
                System.err.println(arrays[i][xEnd]);
            }
            xEnd--;
            for (int i = xEnd; i >= xBegin; i--) {
                System.err.println(arrays[yEnd][i]);
            }
            yEnd--;
            if (xBegin >= xEnd) {
                break;
            }
        }
    }


}
