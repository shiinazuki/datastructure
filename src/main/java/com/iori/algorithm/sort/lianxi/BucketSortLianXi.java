package com.iori.algorithm.sort.lianxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>桶排序</h3>
 */
public class BucketSortLianXi {
    public static void main(String[] args) {
        int[] ages = {20, 18, 28, 66, 25, 31, 67, 30}; // 假设人类年龄 1~99
        System.out.println(Arrays.toString(ages));
        sort(ages);
        System.out.println(Arrays.toString(ages));
    }

    /*
        0
        1   18
        2   20 25 28
        3   31
        4
        5
        6   66 67
        7
        8
        9
     */
    public static void sort(int[] ages) {




    }
}
