package com.iori.datastructure.heap;


import java.util.Arrays;

/**
 * 1. 向小顶堆放入前k个元素
 * 2. 剩余元素
 * 若 <= 堆顶元素 则略过
 * 若 > 堆顶元素, 则替换堆顶元素
 * 3. 这样小顶堆始终保留的是目前为止 前 K 大的元素
 * 4. 循环结束，堆顶元素即为第 k 大元素
 */

public class E02Leetcode215 {


    public static void main(String[] args) {
        E02Leetcode215 code = new E02Leetcode215();
        // 应为5
        System.out.println(code.searchLianXi(new int[]{3, 2, 1, 5, 6, 4}, 2));
        // 应为4
        System.out.println(code.searchLianXi(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(code.searchLianXi(new int[]{2, 1}, 2));
    }


    /**
     * 我这什么垃圾想法 遍历交换后 不就成小顶堆了 真垃圾
     *
     * @param nums
     * @param k
     * @return
     */
    public int searchLianXi(int[] nums,int k) {
        int size = nums.length - k;
        MaxHeap heap = new MaxHeap(nums);
        while (heap.size > 1) {
            heap.swap(0,heap.size - 1);
            heap.size--;
            heap.down(0);
        }
        System.out.println(Arrays.toString(nums));
        return nums[size];

    }

    /**
     * 使用小顶堆
     *
     * @param nums
     * @param k
     * @return
     */
    private int findKthLargest(int[] nums, int k) {

        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.replace(nums[i]);
            }
        }

        return minHeap.peek();

    }


}
