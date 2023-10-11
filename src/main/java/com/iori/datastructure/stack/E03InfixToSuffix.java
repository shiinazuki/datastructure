package com.iori.datastructure.stack;

import java.util.LinkedList;

/**
 * 中缀表达式转后缀
 */
public class E03InfixToSuffix {


    public static void main(String[] args) {
        //System.out.println(infixToSuffix("a+b"));
        //System.out.println(infixToSuffix("a+b-c"));
        //System.out.println(infixToSuffix("a+b*c"));
        //System.out.println(infixToSuffix("a*b-c"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));
    }

    public static void test() {
        int a = 1;
        int b = 2;
        int c = a + b;
    }

    /*

        a+b             ab+
        a+b-c           ab+c-
        a*b+c           ab*c+
        a+b*c           abc*+
        a+b*c-d         abc*+d-
        (a+b)*c         ab+c*
        (a+b*c-d)*e     abc*+d-e*
        a*(b+c)         abc+*


        1. 遇到非运算符 直接拼串
        2. 遇到 + - * /
            - 它的优先级比栈顶运算符高, 入栈, 如: 栈中是 + 当前是 *
            - 否则把栈里优先级 >= 它 的都出栈, 它再入栈, 如: 栈中是 +*, 当前是 -
        3. 遍历完成, 栈里剩余运算符依次出栈
        4. 带()
            - 左括号直接入栈, 左括号优先设置为0
            - 右括号就把栈里到左括号为止的所有运算符出栈
     */
    static String infixToSuffix(String exp) {
        //创建一个栈 用来存储运算符
        LinkedList<Character> stack = new LinkedList<>();
        //用于字符串的拼接
        StringBuilder stringBuilder = new StringBuilder(exp.length());
        //开始循环进行操作
        for (int i = 0; i < exp.length(); i++) {
            //将拿到的字符串转为字符
            char c = exp.charAt(i);
            //使用switch进行判断
            switch (c) {
                //如果字符 不是数字 是符号
                case '+', '-', '*', '/' -> {
                    //先判断栈是否为 空 如果是 直接放入栈中
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        //否则比较当前符号的优先级 和栈中符号的优先级
                        //如果当前符号的优先级高 直接放入栈中
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            //否则 将栈中的元素取出来 拼接到字符串上
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                stringBuilder.append(stack.pop());
                            }
                            //将当前字符加入到栈中
                            stack.push(c);
                        }

                    }
                }
                //如果是左括号直接入栈
                case '(' -> {
                    stack.push(c);
                }
                //如果是有括号
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        stringBuilder.append(stack.pop());
                    }
                    //这一步是把左括号弹出来
                    stack.pop();
                }
                default -> {
                    stringBuilder.append(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();
    }

    static int priority(char c) {
        //switch 新语法
        return switch (c) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("不合法的运算符:" + c);
        };

        //自己写一个
       /* if ('*' == c || '/' == c) {
            return  2;
        }else if ('+' == c || '-' == c) {
            return 1;
        }else if ('(' == c) {
            return 0;
        }else  {
            throw new IllegalArgumentException("不合法的运算符:" + c);
        }
        */

    }

}
