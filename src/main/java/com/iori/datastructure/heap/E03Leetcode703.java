package com.iori.datastructure.heap;


/**
 * 求数据流中第 K 大的元素
 * 使用小顶堆解决最方便
 */

public class E03Leetcode703 {


    private MinHeap heap;

    public E03Leetcode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {

        E03Leetcode703 test = new E03Leetcode703(3,
                new int[]{4,5,8,2});
        //小顶堆
        System.out.println(test.add(3)); // [3] 3
        System.out.println(test.add(5)); // [3 5] 3
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));


    }



}
