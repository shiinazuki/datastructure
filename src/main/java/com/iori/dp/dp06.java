package com.iori.dp;


import java.util.HashMap;

public class dp06 {


     /*
        给定一个字符串str，给定一个字符串类型的数组arr，
        出现的字符都是小写英文arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，
        目的是拼出str来返回需要至少多少张贴纸可以完成这个任务。
        例子 : str="babac"，arr = ["ba","c","abcd"}至少需要两张贴纸”ba”和“abcd”，
        因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
      */


    public static void main(String[] args) {
        String str = "babac";
        String[] arr = {"ba", "c", "abcd"};
        System.out.println(minStickers1(arr, str));
        System.out.println(minStickers2(arr, str));

    }

    /*-----------------------------*/
    //记忆法优化
    public static int minStickers2(String[] stickers, String target) {
        int n = stickers.length;
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char ch : chars) {
                counts[i][ch - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process2(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        if (target.isEmpty()) {
            return 0;
        }
        char[] chars = target.toCharArray();
        int[] tcounts = new int[26];
        for (char ch : chars) {
            tcounts[ch - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[chars[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process2(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }


    /*-----------------------------*/
    //暴力递归

    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //所有贴纸 stickers 每一张贴纸都有无穷张
    //target
    //最少张数
    public static int process1(String[] stickers, String target) {
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String str) {
        char[] chars1 = target.toCharArray();
        char[] chars2 = str.toCharArray();
        int[] count = new int[26];
        for (char c : chars1) {
            count[c - 'a']++;
        }
        for (char c : chars2) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sb.append((char) (i + 'a'));
                count[i]--;
            }
        }
        return sb.toString();
    }


}
