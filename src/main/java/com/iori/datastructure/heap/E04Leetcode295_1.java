package com.iori.datastructure.heap;

import java.util.Arrays;

/**
 * 数据流的中位数
 */
public class E04Leetcode295_1 {

    private Heap left = new Heap(10, true); //大顶堆
    private Heap right = new Heap(10, false); //小顶堆


    public static void main(String[] args) {
        E04Leetcode295_1 test = new E04Leetcode295_1();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(7);
        test.addNum(8);
        test.addNum(9);
        System.out.println(test);
        System.out.println(test.findMedian());
        test.addNum(10);
        System.out.println(test);
        System.out.println(test.findMedian());
        test.addNum(4);
        System.out.println(test);
        System.out.println(test.findMedian());
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return  (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    /**
     * 为了保证两边数据量的平衡
     * 两边个数一样时 左边个数加一
     * 两边个数不一样时 右边个数加一
     * 但是 随便一个数能直接加入吗?
     * 左边个数加一时 应该挑右边最小的加入
     * 右边个数加一时 应该挑左边最大的加入
     *
     * @param num
     */
    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    @Override
    public String toString() {
        int size = left.size;
        int[] left = new int[size];
        for (int i = 0; i < left.length; i++) {
            left[size - 1 - i] = this.left.array[i];
        }
        int[] right = Arrays.copyOf(this.right.array, this.right.size);
        return Arrays.toString(left) + " <-> " + Arrays.toString(right);
    }

}
