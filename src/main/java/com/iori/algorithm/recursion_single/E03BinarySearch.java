package com.iori.algorithm.recursion_single;

/**
 * 递归二分查找
 */
public class E03BinarySearch {

    public static int search(int[] array, int target) {
        return f(0, array.length - 1, array, target);

    }

    private static int f(int i, int j, int[] array, int target) {
        if (i > j) {
            return -1;
        }
        int index = (i + j) >>> 1;
        if (target < array[index]) {
            return f(i, index - 1, array, target);
        } else if (array[index] < target) {
            return f(index + 1, j, array, target);
        } else {
            return index;
        }
    }

}
