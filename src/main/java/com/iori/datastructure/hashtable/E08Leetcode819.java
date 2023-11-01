package com.iori.datastructure.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 出现次数最多的单词
 * 答案会唯一
 *
 * <p>s 中仅包含小写字符</p>
 */
public class E08Leetcode819 {

    /*
       1. 将 paragraph 截取为一个个单词
       2. 将单词加入 map 集合，单词本身作为 key，出现次数作为 value，避免禁用词加入
       3. 在 map 集合中找到 value 最大的，返回它对应的 key 即可
    */
    public static void main(String[] args) {
        //String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        //String paragraph = "a.";
        //String paragraph = "Bob";
        String paragraph = "Bob. hIt, baLl";
        String[] banned = {"bob", "hit"};
        //System.out.println(mostCommonWord1(paragraph, banned));
        //System.out.println(mostCommonWord2(paragraph, banned));
        //System.out.println(mostCommonWord3(paragraph, banned));
        System.out.println(mostCommonWord4(paragraph, banned));
    }


    /**
     * 自己多练练
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public static String mostCommonWord4(String paragraph, String[] banned) {
        //先把违禁词放入set集合 方便判断是否往map集合中添加
        HashSet<String> set = new HashSet<>();
        for (String key : banned) {
            set.add(key);
        }
        //添加完后在添加一个空字符串 避免map集合中添加的是空 !!!!
        set.add("");

        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = paragraph.toLowerCase().toCharArray();
        for (char ch : chars) {
            //如果遇到字符就拼接 否则就加入到map集合
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            } else {
                String key = sb.toString();
                //如果set集合里不存在这个key  说明不是违禁词 可以放入map集合
                if (!set.contains(key)) {
                    //先取出值判断是不是null 如果是就给个默认值
                    Integer value = map.get(key);
                    if (value == null) {
                        value = 0;
                    }
                    map.put(key, value + 1);
                }
                //重置StringBuilder的长度
                sb.setLength(0);
            }
        }
        //执行完判断 StringBuilder的长度是否大于0
        //防止最后一个字符不是字母 走完拼接就结束了循环
        if (sb.length() > 0) {
            String key = sb.toString();
            //如果set集合里不存在这个key  说明不是违禁词 可以放入map集合
            if (!set.contains(key)) {
                //先取出值判断是不是null 如果是就给个默认值
                Integer value = map.get(key);
                if (value == null) {
                    value = 0;
                }
                map.put(key, value + 1);
            }
        }
        //走到这里添加并过滤完成 开始寻找出现次数最多的
        int max = 0;
        String maxStr = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                maxStr = entry.getKey();
            }
        }
        return maxStr;
    }


    /**
     * 看视频解法
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public static String mostCommonWord3(String paragraph, String[] banned) {

        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            } else {
                String key = sb.toString();
                if (!set.contains(key)) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                //sb = new StringBuilder();
                sb.setLength(0);
            }
        }
        if (sb.length() > 0) {
            String key = sb.toString();
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value > max) {
                max = value;
                maxKey = e.getKey();
            }
        }
        return maxKey;
    }


    /**
     * 看视频解法
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public static String mostCommonWord2(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        for (String key : split) {
            //Integer value = map.get(key);
            //if (value == null) {
            //    value = 0;
            //}
            //map.put(key,value + 1);
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value > max) {
                max = value;
                maxKey = e.getKey();
            }
        }
        return maxKey;
    }

    /**
     * 看视频解法
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public static String mostCommonWord1(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        for (String key : split) {
            //Integer value = map.get(key);
            //if (value == null) {
            //    value = 0;
            //}
            //map.put(key,value + 1);
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }


    /**
     * 漏洞百出  不改了 改好一个 另一个又出
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public static String mostCommonWord(String paragraph, String[] banned) {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = paragraph.toCharArray();
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',' || chars[i] == '!') {
                continue;
            }
            if (chars[i] == ' ' || chars[i] == '.' || i == chars.length - 1) {
                if (i == chars.length - 1 && chars[i] != '.') {
                    sb.append(chars[i]);
                }
                String key = sb.toString().toLowerCase();
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
                sb = new StringBuilder();
                flag = false;
            } else {
                sb.append(chars[i]);
            }
        }
        if (flag) {
            map.put(sb.toString().toLowerCase(), 1);
        }
        Set<String> keys = map.keySet();
        int max = -1;
        String k = "";
        for (String key : keys) {
            if (banned != null) {
                for (String s : banned) {
                    if (key.equals(s)) {
                        map.put(key, 0);
                    }
                }
            }

            if (map.get(key) > max) {
                max = map.get(key);
                k = key;
            }
        }
        return k;
    }


}
