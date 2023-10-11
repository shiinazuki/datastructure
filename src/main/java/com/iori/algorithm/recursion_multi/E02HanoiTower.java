package com.iori.algorithm.recursion_multi;

import org.springframework.util.StopWatch;

import java.util.LinkedList;

/**
 * 汉诺塔
 */
public class E02HanoiTower {

    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

     static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.addLast(i);
        }
    }


    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        int n = 3;
        init(n);
        print();
        sw.start("n=3");
        move(n, a, b, c);
        sw.stop();
        print();
        System.out.println(sw.prettyPrint());

        //init(3);
        //print();
        //move(3, a, b, c);
    }


    /**
     * @param n 圆盘个数
     * @param a 源
     * @param b 借
     * @param c 目标
     */
    public static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if(n == 0) {
            return;
        }
        move(n - 1, a, c, b);
        c.addLast(a.removeLast()); //中间
        print();
        move(n - 1, b, a, c);
    }

    private static void print() {
        System.out.println("----------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
