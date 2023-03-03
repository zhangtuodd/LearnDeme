package leet.string;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/23
 */

class 最长回文字段 {

    public static void main(String[] args) {
        longestPalindrome("babad");
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        // List<String> list = new ArrayList<Stirng>();
        int len = s.length();
        String maxStr = "";
        for (int i = 0; i < len; i++) {
            int k = i;
            StringBuilder sb = new StringBuilder();
            while (k < len) {
                //    list.add(sb.append(s.charAt(i));)
                String ss = sb.append(s.charAt(k)).toString();
                System.out.println(ss);
                if (isPalindrome(ss) && ss.length() > maxStr.length()) {
                    maxStr = ss;
                }

                k++;
            }
        }
        System.out.println("-----------------------");
        System.out.println("maxStr:}"+maxStr);
        return maxStr;

    }

    private static Boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
