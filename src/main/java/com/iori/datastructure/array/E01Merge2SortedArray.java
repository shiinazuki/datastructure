package com.iori.datastructure.array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 */
public class E01Merge2SortedArray {


    public static void main(String[] args) {
        int[] a1 = {1, 5, 6, 2, 4, 10, 11};
        int[] a2 = new int[a1.length];
        //merge2(a1, 0, 2, 3, 6, a2, 0);
        merge3(a1, 0, 2, 3, 6, a2);
        System.out.println(Arrays.toString(a2));
        System.arraycopy(a2, 0, a1, 0, a2.length);
        System.out.println(Arrays.toString(a1));

        //逆向双指针测试
        //int[] nums1 = {1, 2, 3, 0, 0, 0};
        //int[] nums2 = {2, 5, 6};
        //merge3(nums1, 3, nums2, 3);
        //System.out.println(Arrays.toString(nums1));
    }


    /**
     * 逆向双指针法
     */
    public static int[] merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int tail = m + n - 1;
        int cur;
        while (i >= 0 || j >= 0) {
            if (i == -1) {
                cur = nums2[j];
                j--;
            } else if (j == -1) {
                cur = nums1[i];
                i--;
            } else if (nums1[i] > nums2[j]) {
                cur = nums1[i];
                i--;
            } else {
                cur = nums2[j];
                j--;
            }
            nums1[tail] = cur;
            tail--;
        }

        return nums1;

    }

    /**
     * 双指针方法
     *
     * @param array1
     * @param i
     * @param iend
     * @param j
     * @param jend
     * @param array2
     */
    public static void merge3(int[] array1, int i, int iend, int j, int jend, int[] array2) {
        int k = i;
        while (i <= iend && j <= jend) {
            if (array1[i] < array1[j]) {
                array2[k] = array1[i];
                i++;
            } else {
                array2[k] = array1[j];
                j++;
            }
            k++;
        }
        if (i > iend) {
            System.arraycopy(array1, j, array2, k, jend - j + 1);
        }
        if (j > jend) {
            System.arraycopy(array1, i, array2, k, iend - i + 1);
        }


    }


    /**
     * 递归解决
     *
     * @param array1
     * @param i
     * @param iend
     * @param j
     * @param jend
     * @param array2
     * @param n
     */

        /*
        a1 原始数组  a2 结果数组 (k)
        i, iEnd 第一个有序区间的起点终点, j, jEnd 第二个有序区间的起点终点

        merge([1,5,6] [2,4,10,11], a2=[]) {
            merge([5,6] [2,4,10,11], a2=[1]) {
                merge([5,6] [4,10,11], a2=[1,2]) {
                    merge([5,6] [10,11], a2=[1,2,4]) {
                        merge([6] [10,11], a2=[1,2,4,5]) {
                            merge([] [10,11], a2=[1,2,4,5,6]) {
                                 merge([], [], a2=[1,2,4,5,6,10,11])
                            }
                        }
                    }
                }
            }
        }

     */
    public static void merge2(int[] array1, int i, int iend, int j, int jend, int[] array2, int n) {
        if (i > iend) {
            System.arraycopy(array1, j, array2, n, jend - j + 1);
            return;
        }
        if (j > jend) {
            System.arraycopy(array1, i, array2, n, iend - i + 1);
            return;
        }

        if (array1[i] < array1[j]) {
            array2[n] = array1[i];
            merge2(array1, i + 1, iend, j, jend, array2, n + 1);
        } else {
            array2[n] = array1[j];
            merge2(array1, i, iend, j + 1, jend, array2, n + 1);
        }

    }


    /**
     * 直接将数组2放入到数组1的尾部 然后对数组进行排序
     */
    public static int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        return sort(nums1);
    }

    /**
     * 排序方法
     *
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
        return nums;
    }

}
