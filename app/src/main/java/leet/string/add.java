package leet.string;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/1/11
 */

class add {

    public static void main(String[] args) {
        bigSumAdd("6666", "555");
    }

    private static void bigSumAdd(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int[] array = new int[length1 > length2 ? length1 + 1 : length2 + 1];
        StringBuffer s1 = new StringBuffer();

        StringBuffer s2 = new StringBuffer();
        for (int i = 0; i < array.length - num1.length(); i++) {
            s1.append("0");
        }
        s1.append(num1);

        for (int i = 0; i < array.length - num2.length(); i++) {
            s2.append("0");
        }
        s2.append(num2);
        char[] chars1 = s1.toString().toCharArray();
        char[] chars2 = s2.toString().toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] += (chars1[i] - '0') + (chars2[i] - '0');
        }
        int temp = 0;
        for (int i = array.length-1; i >= 0 ; i--) {
            array[i]+=temp;
            temp = array[i] / 10;
            array[i] = array[i] % 10;
        }


//        for (int i = array.length - 1; i >= 0; i--) {
//            int value1 = 0;
//            int value2 = 0;
//
//            value1 = s1.charAt(i) - '0';
//
//
//            value2 = s2.charAt(i) - '0';
//
//
//            int curResult = value1 + value2;
//
//            if (curResult + array[i] >= 10) {
//                array[i] = curResult % 10 + array[i];
//                if (i > 0) {
//                    array[i - 1] = curResult / 10;
//                }
//            } else {
//                array[i] = curResult + array[i];
//            }

//
//            if (curResult + array[i] < 10) {
//                array[i] = curResult + array[i];
//            } else {
//                int lowV = curResult % 10;
//                int higtV = curResult / 10;
//                array[i] = lowV + array[i];
//                if (i > 0) {
//                    array[i - 1] = higtV;
//                }
//            }
//        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (sb.length() == 0 && array[i] == 0) {
                continue;
            }
            sb.append(array[i]);
        }
        System.out.printf(sb.toString());

    }
}
