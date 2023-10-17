package com.iori.kmp;

public class TestKMP {

    public static void main(String[] args) {

        System.out.println(KMP("ababcabcdabcdeabcdef", "abcd", 0));
        System.out.println(KMP("ababcabcdabcdeabcdef", "abcdf", 0));
        System.out.println(KMP("ababcabcdabcdeabcdef", "ab", 0));

    }

    /**
     * @param str 主串
     * @param sub 字串
     * @param pos 从字串的pos位置开始匹配
     * @return 找到字串在主串的下标
     */
    public static int KMP(String str, String sub, int pos) {
        if (str == null || sub == null) {
            return -1;
        }
        int lenStr = str.length();
        int lenSub = sub.length();

        if (lenStr == 0 || lenSub == 0) {
            return -1;
        }
        if (pos < 0 || pos >= lenStr) {
            return -1;
        }

        int[] next = new int[lenSub];
        //得到next数组
        getNext(sub, next);

        int i = pos; //遍历主串
        int j = 0; //遍历字串

        while (i < lenStr && j < lenSub) {
            if (j == -1 || str.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= lenSub) {
            return i - j;
        }

        return -1;

    }

    private static void getNext(String sub, int[] next) {
        next[0] = -1;
        next[1] = 0;
        int i = 2; //提前走了一步
        int k = 0; //前一项的 k
        //遍历字串
        while (i < sub.length()) {
            if (k == -1 || sub.charAt(i - 1) == sub.charAt(k)) {
                next[i] = k + 1;
                k++;
                i++;
            } else {
                k = next[k];
            }
        }

    }


}
