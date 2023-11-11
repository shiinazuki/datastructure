package com.iori.algorithm.leetcode;

import java.util.Arrays;

/**
 * <h3>盛最多水的容器</h3>
 */
public class MostWaterLeetcode11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea0(height));
    }

    public static int maxArea0(int[] height) {

        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {

            //max = height[i] < height[j] ? Math.max(max, (j - 1) * height[i++]) : Math.max(max, (j - 1) * height[j--]);
            if (height[i] < height[j]) {
                int k = j - i;
                int sum = height[i] * k;
                if (sum > max) {
                    max = sum;
                }
                i++;
            } else {
                int k = j - i;
                int sum = height[j] * k;
                if (sum > max) {
                    max = sum;
                }
                j--;
            }

        }

        return max;
    }


}
