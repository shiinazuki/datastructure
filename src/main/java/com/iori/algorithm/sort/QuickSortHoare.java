package com.iori.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h3>双边循环快排</h3>
 * <ol>
 * <li>选择最左元素作为基准点元素</li>
 * <li>j 指针负责从右向左找比基准点小或等的元素，i 指针负责从左向右找比基准点大的元素，一旦找到二者交换，直至 i，j 相交</li>
 * <li>最后基准点与 i（此时 i 与 j 相等）交换，i 即为分区位置</li>
 * </ol>
 */
public class QuickSortHoare {


    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 排序方法
     *
     * @param a
     */
    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    /**
     * 递归方法
     *
     * @param a
     * @param left
     * @param right
     */
    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
        注意事项
        1. 为啥要加内层循环的 i<j 条件
        2. 为啥要先处理 j，再处理 i
        3. 随机元素作为基准点
        4. 如果有大量重复元素
     */

    /**
     * 分区方法
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] a, int left, int right) {
        int index = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        //[0 - 9] right - left + 1 [0,2] + 左边界 = [4,6]
        swap(a, index, left);
        int pv = a[left];
        int i = left;
        int j = right;

        while (i < j) {
            //1.j从右向左找比基本点小的值
            while (i < j && a[j] > pv) {
                j--;
            }
            //2.i从左向右找比基准点大的值
            while (i < j && a[i] <= pv) {
                i++;
            }
            //3.交换位置
            swap(a, i, j);
        }
        swap(a, i, left);
        return i;
    }


    /**
     * 交换方法
     *
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
