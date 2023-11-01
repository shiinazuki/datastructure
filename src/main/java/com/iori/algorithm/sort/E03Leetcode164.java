package com.iori.algorithm.sort;

import java.util.ArrayList;

/**
 * 最大间距
 */
public class E03Leetcode164 {

    public static void main(String[] args) {
        int[] nums = {9, 1, 3, 5};
        //int[] nums = {1, 10000000};
        int r = new E03Leetcode164().maximumGap(nums);
        System.out.println(r);
    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        //sort2(nums);
        //int max = 0;
        //for (int i = 1; i < nums.length; i++) {
        //    max = Math.max(max, nums[i] - nums[i - 1]);
        //}
        //return max;
        return sort4(nums);
    }


    /**
     * 使用基数排序解决
     *
     * @param a
     */
    public static void sort(int[] a) {
        //拿到最大值
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        //准备桶
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList();
        }
        int m = 1;
        while (m <= max) {
            //一轮基数排序
            for (int i : a) {
                buckets[i / m % 10].add(i);
            }
            //取出元素 放入原来的数组中
            int k = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (int v : bucket) {
                    a[k++] = v;
                }
                bucket.clear();
            }
            m *= 10;
        }

    }


    /**
     * 桶排序
     */
    public void sort1(int[] nums) {

        // 2. 桶排序
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if (nums[i1] > max) {
                max = nums[i1];
            }
            if (nums[i1] < min) {
                min = nums[i1];
            }
        }
        // 1. 准备桶
        ArrayList<Integer>[] buckets = new ArrayList[(max - min) / 1 + 1];
        for (int i1 = 0; i1 < buckets.length; i1++) {
            buckets[i1] = new ArrayList();
        }
        // 2. 放入数据
        for (int age : nums) {
            buckets[(age - min) / 1].add(age);
        }
        int k = 0;
        for (ArrayList<Integer> bucket : buckets) {
            // 3. 排序桶内元素
            bucket.sort(null);
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (Integer v : bucket) {
                nums[k++] = v;
            }
        }

    }


    /**
     * 改进版桶排序
     */
    public void sort2(int[] nums) {

        //桶排序
        int max = nums[0];
        int min = nums[0];
        for (int i1 = 1; i1 < nums.length; i1++) {
            if (nums[i1] > max) {
                max = nums[i1];
            }
            if (nums[i1] < min) {
                min = nums[i1];
            }
        }
        int range = Math.max(1, (max - min) / (nums.length - 1));
        // 1. 准备桶
        ArrayList<Integer>[] buckets = new ArrayList[(max - min) / range + 1];
        for (int i1 = 0; i1 < buckets.length; i1++) {
            buckets[i1] = new ArrayList();
        }
        // 2. 放入数据
        for (int age : nums) {
            buckets[(age - min) / range].add(age);
        }
        int k = 0;
        for (ArrayList<Integer> bucket : buckets) {
            // 3. 排序桶内元素
            bucket.sort(null);
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (Integer v : bucket) {
                nums[k++] = v;
            }
        }

    }


    /**
     * 改进版  桶排序
     * 在桶内求出差值
     */
    public int sort4(int[] nums) {

        // 2. 桶排序
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        // 1. 准备桶
        /*
            计算桶个数                   期望桶个数
            (max - min) / range + 1 = nums.length + 1
            (max - min) / range = nums.length

         */
        int range = Math.max((max - min) / nums.length, 1); //让桶的个数比元素多一个
        int bucketSize = (max - min) / range + 1;
        Pair[] buckets = new Pair[(max - min) / range + 1];
        // 2. 放入数据
        for (int age : nums) {
            int idx = (age - min) / range;
            if (buckets[idx] == null) {
                buckets[idx] = new Pair();
            }
            buckets[idx].add(age);
        }

        int k = 0;
        int lastMax = buckets[0].max;
        for (int i = 0; i < buckets.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null) {
                k = Math.max(k, bucket.min - lastMax);
                lastMax = bucket.max;
            }
        }
        return k;

    }

    // 数据范围 0 <= nums[i] <= 1000_000_000
    static class Pair {
        int max = 0;
        int min = 1000_000_000;

        void add(int v) { // 桶内最大最小
            max = Math.max(v, max);
            min = Math.min(v, min);
        }

        @Override
        public String toString() {
            return "[" + max +
                    "," + min +
                    ']';
        }
    }


}
