package com.iori.datastructure.heap;

import java.util.Arrays;

/**
 * 堆排序
 */
public class LianXi {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        sort1(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }


    private static void sort(int[] array) {
        int size = array.length;
        heapify(array, size);
        while (size > 1) {
            swap(array, 0, size - 1);
            size--;
            down(array, 0, size);
        }
    }

    private static void heapify(int[] array, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(array, 0, size);
        }
    }

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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
