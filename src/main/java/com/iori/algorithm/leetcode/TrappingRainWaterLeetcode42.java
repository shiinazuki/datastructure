package com.iori.algorithm.leetcode;

import java.util.Stack;

/**
 * <h3>接雨水</h3>
 */
public class TrappingRainWaterLeetcode42 {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }


    //单调栈 抄的题解 日后研究
    public static int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;



    }

    //使用双指针 看的题解 思想明白了
    public static int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int sum = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(height[left], leftMax);
                sum += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(height[right], rightMax);
                sum += rightMax - height[right];
                right--;
            }
        }

        return sum;
    }


    //使用双指针试试 只使用部分例子
    public static int trap1(int[] height) {
        int left = 0;
        int right = 1;
        int sum = 0;
        while (right < height.length) {
            if (height[right] > height[left]) {
                left = right;
                right++;
                continue;
            }
            sum += height[left] - height[right];
            left++;
            right++;
        }
        return sum;
    }

}
