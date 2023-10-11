package com.iori.datastructure.stack;

/**
 * 有效的括号
 */
public class E02Leetcode150 {


    public static void main(String[] args) {

    }

     /*
        - 遇到数字压入栈
        - 遇到运算符, 就从栈弹出两个数字做运算, 将结果压入栈
        - 遍历结束, 栈中剩下的数字就是结果
     */

    public Integer evalRPN(String[] tokens) {

        LinkedListStack<Integer> stack = new LinkedListStack<>(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if ("+".equals(str)) {
                Integer n1 = stack.pop();
                Integer n2 = stack.pop();
                stack.push(n2 + n1);
            } else if ("-".equals(str)) {
                Integer n1 = stack.pop();
                Integer n2 = stack.pop();
                stack.push(n2 - n1);
            } else if ("*".equals(str)) {
                Integer n1 = stack.pop();
                Integer n2 = stack.pop();
                stack.push(n2 * n1);
            } else if ("/".equals(str)) {
                Integer n1 = stack.pop();
                Integer n2 = stack.pop();
                stack.push(n2 / n1);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

}
