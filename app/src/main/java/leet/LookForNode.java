package leet;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-24
 */
public class LookForNode {

    static int[][] array = new int[2][4];

    public static void main(String[] args) {
        look(array, 5);

    }

    private static boolean look(int[][] array, int mark) {
        if (array == null) {
            return false;
        }
        int moddle;
        if (array[0].length % 2 == 0) {
            moddle = array[0].length % 2;
        } else {
            moddle = array[0].length % 2 + 1;
        }


        for (int i = 0; i < array.length; i++) {

//            if (array[i][moddle]  > mark)
            for (int j = i; j < array[i].length; j++) {
                if (mark == array[i][j]) {
                    return true;
                }
            }


        }
        return false;
    }
}
