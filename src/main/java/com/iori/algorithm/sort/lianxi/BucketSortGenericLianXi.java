package com.iori.algorithm.sort.lianxi;

import com.iori.datastructure.array.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>桶排序(改进)</h3>
 */
public class BucketSortGenericLianXi {
    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7};
        System.out.println(Arrays.toString(ages));
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));
    }

    /*
        0   0,1,2
        1   3,4,5
        2   6,7,8
        3   9
     */
    public static void sort(int[] a, int range) {
    }
}
