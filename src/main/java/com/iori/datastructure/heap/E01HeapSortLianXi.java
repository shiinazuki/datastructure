package com.iori.datastructure.heap;

import java.util.Arrays;

/**
 * 堆排序
 */
public class E01HeapSortLianXi {


    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        int[] nums = {2, 3, 1,1, 7, 6,1,14, 5};
        int val = 1;
        //sort(array);
        //System.out.println(Arrays.toString(array));
        int i = 0,j = 0;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        //return i;
    }

    public static void sort(int[] array) {
        int size = array.length;
        heapify(array);
        while (size > 1) {
            swap(array, 0, size - 1);
            size--;
            down(array, 0, size);
        }
    }

    // 建堆
    private static void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(array, i, array.length - 1);
        }
    }

    // 下潜
    private static void down(int[] array, int parent, int size) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {
            swap(array, max, parent);
            down(array, max, size);
        }
    }

    // 交换
    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


}
