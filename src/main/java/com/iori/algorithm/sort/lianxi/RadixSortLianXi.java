package com.iori.algorithm.sort.lianxi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <h3>基数排序 最低有效数字 LSD(Least significant digit)</h3>
 */
public class RadixSortLianXi {
    /*
        110 088 009

        0   088 009
        1   110
        2
        3
        4
        5
        6
        7
        8
        9
        088 009 110 第一轮

        0   009
        1   110
        2
        3
        4
        5
        6
        7
        8   088
        9
        009 110 088 第二轮

        0   110
        1
        2
        3
        4
        5
        6
        7
        8   088
        9   009
        110 088 009
     */


    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];  // 0~127
        phoneNumbers[0] = "13812345678";  // int long
        phoneNumbers[1] = "13912345678";
        phoneNumbers[2] = "13612345678";
        phoneNumbers[3] = "13712345678";
        phoneNumbers[4] = "13512345678";
        phoneNumbers[5] = "13412345678";
        phoneNumbers[6] = "15012345678";
        phoneNumbers[7] = "15112345678";
        phoneNumbers[8] = "15212345678";
        phoneNumbers[9] = "15712345678";

        /*String[] phoneNumbers = new String[6];
        phoneNumbers[0] = "158";
        phoneNumbers[1] = "151";
        phoneNumbers[2] = "235";
        phoneNumbers[3] = "138";
        phoneNumbers[4] = "139";
        phoneNumbers[5] = "159";*/

        /*
            0
            1   151
            2
            3
            4
            5   135
            6
            7
            8   158 138
            9   139 159
            151 135 158 138 139 159  按个位排

            0
            1
            2
            3   135 138 139
            4
            5   151 158 159
            6
            7
            8
            9
            135 138 139 151 158 159  按十位排
         */

        RadixSortLianXi.radixSort(phoneNumbers, 11);
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }

    public static void radixSort(String[] a, int length) {

    }

    public static void sort(int[] a) {

    }


}
