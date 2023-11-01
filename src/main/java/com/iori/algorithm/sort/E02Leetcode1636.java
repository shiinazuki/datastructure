package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * 按出现频率排序 数据范围在[-100,100]内
 */
public class E02Leetcode1636 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 3};
        System.out.println(Arrays.toString(new E02Leetcode1636().frequencySort1(nums)));
    }

    /**
     * 视频讲解
     *
     * @param nums
     * @return
     */
    private int[] frequencySort1(int[] nums) {

        //统计出现率
        int[] count = new int[201];
        for (int i : nums) {
            count[i + 100]++;
        }
        //比较器 按频率升序 在按数值降序
        return Arrays.stream(nums).boxed().sorted((a, b) -> {
            int af = count[a + 100];
            int bf = count[b + 100];
            if (af < bf) {
                return -1;
            } else if (af > bf) {
                return 1;
            } else {
                return b - a;
            }
        }).mapToInt(Integer::intValue).toArray();

    }


    /**
     * 自己想的
     *
     * @param nums
     * @return
     */
    private int[] frequencySort(int[] nums) {

        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        //创建计数数组
        int[] count = new int[max - min + 1];
        for (int v : nums) {
            count[v - min]++;
        }


        //采用倒序排列的想法 先找出现频率最高的 然后倒叙插入到最后
        int k = nums.length - 1;
        for (int j = 0; j < nums.length; j++) {

            int ma = count[0];
            int z = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > ma) {
                    ma = count[i];
                    z = i;
                }
            }
            int num = count[z];
            while (num > 0) {
                nums[k--] = z + min;
                num--;
            }
            count[z] = 0;
        }

        return nums;

    }

}
