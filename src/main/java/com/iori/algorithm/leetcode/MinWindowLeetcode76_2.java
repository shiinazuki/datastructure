package com.iori.algorithm.leetcode;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

/**
 * <h3>最小覆盖字串</h3>
 */
public class MinWindowLeetcode76_2 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
//        System.out.println(minWindow("aaabbbbbcdd", "abcdd")); // abbbbbcdd
    }

    static class Result {
        int left;
        int right;

        public Result(int i, int j) {
            this.left = i;
            this.right = j;
        }
    }



    /*
        1. 统计目标串需要各种字符个数， 统计原始串 i~j 范围各种字符个数
        2. 如果原始串 i~j 范围内不满足条件，j++ 扩大范围，直到满足条件 j 停下来
        3. 一旦满足条件 i++ 缩小范围，直到再次不满足条件 ...
        4. 重复 2. 3. 两步直至 j 到达原始串末尾
     */


    public static String minWindow(String s, String t) {

        char[] target = t.toCharArray();
        int[] targetCount = new int[128];
        //统计出现的字符
        for (char ch : target) {
            targetCount[ch]++;
        }
        int passTotal = 0; //条件总数
        for (int count : targetCount) {
            if (count > 0) {
                passTotal++;
            }
        }
        int passed = 0; //已通过条件数
        char[] source = s.toCharArray();
        //保存原始串 i~j范围内字符各出现几次
        int[] sourceCount = new int[128];
        int left = 0, right = 0;
        Result res = null;
        while (right < source.length) {
            //统计原始串 i~j 范围各种字符个数
            char rightCh = source[right];
            sourceCount[rightCh]++;
            //原始串right处字符 等于 目标串right字符  passed加1
            if (sourceCount[rightCh] == targetCount[rightCh]) {
                passed++;
            }
            // 若已满足所有条件，缩小 i 范围，更新范围内字符计数 和 通过条件数
            while (passed == passTotal && left <= right) {
                if (res == null) {
                    res = new Result(left, right);
                } else {
                    if (right - left < res.right - res.left) {
                        res = new Result(left, right);
                    }
                }
                //拿到左边的字符
                char leftCh = source[left];
                sourceCount[leftCh]--;
                if (sourceCount[leftCh] < targetCount[leftCh]) {
                    passed--;
                }
                left++;
            }
            right++;
        }
        return res == null ? "" : new String(source, res.left, res.right - res.left + 1);
    }

    public static void print(int[] count) {
        System.out.println(IntStream.range(0, count.length)
                .filter(i -> count[i] != 0)
                .boxed()
                .collect(toMap(i -> (char) i.intValue(), i -> count[i])));
    }
}
