package com.iori.algorithm.leetcode;

/**
 * <h3>最小覆盖字串</h3>
 */
public class MinWindowLeetcode76 {

    public static void main(String[] args) {
        //System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow("ab", "a")); // abbbbbcdd
        //System.out.println(minWindow("aaabbbbbcdd", "abcdd")); // abbbbbcdd
    }



    //看题解的滑动窗口
    public static String minWindow(String s, String t) {
        //首先创建的是need数组表示每个字符在t中需要的数量，用ASCII码来保存
        //加入need[76] = 2，表明ASCII码为76的这个字符在目标字符串t中需要两个，如果是负数表明当前字符串在窗口中是多余的，需要过滤掉
        int[] need = new int[128];
        //按照字符串t的内容向need中添加元素
        char[] chars = t.toCharArray();
        for (char c : chars) {
            need[c]++;
        }
        //left是当前左边界, right是当前右边界, size记录窗口大小
        //count是需求额的字符个数 start是最小覆盖串开始的index
        int left = 0, right = 0, size = Integer.MAX_VALUE;
        int count = t.length(), start = 0;

        char[] first = s.toCharArray();
        //遍历所有字符
        while (right < first.length) {
            char c = first[right];
            //表示t中包含当前遍历到的这个c字符，更新目前所需要的count数大小，应该减少一个
            if (need[c] > 0) {
                count--;
            }
            //无论这个字符是否包含在t中，need[]数组中对应那个字符的计数都减少1，利用正负区分这个字符是多余的还是有用的
            need[c]--;
            //count==0说明当前的窗口已经满足了包含t所需所有字符的条件
            if (count == 0) {
                //如果左边界这个字符对应的值在need[]数组中小于0，说明他是一个多余元素，不包含在t内
                while (left < right && need[first[left]] < 0) {
                    //在need[]数组中维护更新这个值，增加1
                    need[first[left]]++;
                    //左边界向右移，过滤掉这个元素
                    left++;
                }
                //如果当前的这个窗口值比之前维护的窗口值更小，需要进行更新
                if (right - left + 1 < size) {
                    //更新窗口值
                    size = right - left + 1;
                    //更新窗口起始位置，方便之后找到这个位置返回结果
                    start = left;
                }
                //先将left位置的字符计数重新加1
                need[first[left]]++;
                //重新维护左边界值和当前所需字符的值count
                left++;
                count++;
            }
            //右移边界，开始下一次循环
            right++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);


    }


    private static int leftIndex = 0;
    private static int rightIndex = 0;
    //自己写的  滑动窗口  只能跑通部分测试用例
    public static String minWindow0(String s, String t) {

        leftIndex = 0;
        rightIndex = 0;
        int left = 0;
        char[] first = s.toCharArray();
        int min = Integer.MAX_VALUE;
        //循环一次左指针
        while (left < first.length - t.length() + 1) {
            //用来记录出现次数
            int count = 0;
            int right = left;
            String newChar2 = t;
            //每次循环右指针
            while (right < first.length) {
                //根据右指针去找t字符是否出现过 出现就置空 并 加1
                for (int i = 0; i < newChar2.length(); i++) {
                    if (first[right] == newChar2.charAt(i)) {
                        newChar2 = newChar2.replace(newChar2.charAt(i), ' ');
                        count++;
                    }
                }
                //出现次数 等于 t字符的长度 就退出循环
                if (count == t.length()) {
                    break;
                }
                right++;
            }
            //将找到的字串与min进行比较 比min小就将索引赋值给对应的变量
            if (right - left < min) {
                leftIndex = left;
                rightIndex = right;
                min = right - left;
            }
            left++;

            /*if (count == 0) {
                leftIndex++;
                rightIndex--;
            }
            if (rightIndex == -1) {
                rightIndex = 0;
                leftIndex--;
            }*/
        }
        return new String(first, leftIndex, rightIndex - leftIndex + 1);
    }
}
