package com.iori.algorithm.sort.lianxi;

import java.awt.dnd.DropTargetEvent;
import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSortLianXi {

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        // 1. 选择轮数 a.length - 1
        // 2. 交换的索引位置(right) 初始 a.length - 1, 每次递减


    }


}
