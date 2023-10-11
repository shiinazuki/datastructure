package com.iori.algorithm.recursion_multi;

/**
 * 杨辉三角
 */
public class E03PascalTriangle {


    public static void main(String[] args) {
        //pascal1(10);
        //System.out.println(element(4, 2));
        //print(10);
        //print1(10);
        print2(10);

    }




    /**
     * 使用一维数组来打印杨辉三角
     * @param row
     * @param i
     */
    public static void createRow(int[] row, int i) {
        if (i == 0) {
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
        }
    }


    /**
     * 使用一维数组来打印杨辉三角
     *
     * @param n
     */
    public static void print2(int n) {
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            createRow(row, i);
            //printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", row[j]);
            }
            System.out.println();
        }
    }


    /**
     * 记忆法优化 二维数组
     *
     * @param i
     * @param j
     * @return
     */
    public static int element1(int[][] triangle, int i, int j) {
        //如果已经有值 说明计算过了
        if (triangle[i][j] > 0) {
            return triangle[i][j];
        }
        //如果是第一列或者最后一个 直接给1
        if (j == 0 || i == j) {
            triangle[i][j] = 1;
            return 1;
        }
        //递归计算第 i行 第 j 列的值 并保存到二维数组中
        triangle[i][j] = element1(triangle, i - 1, j - 1) + element(i - 1, j);
        //返回
        return triangle[i][j];

    }

    /**
     * 记忆法优化 二维数组
     *
     * @param n
     */
    public static void print1(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            //printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element1(triangle, i, j));
            }
            System.out.println();
        }
    }


    public static int element(int i, int j) {

        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);

    }

    private static void printSpace(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }


    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j));
            }
            System.out.println();
        }
    }


    public static void pascal1(int n) {
        int[][] array = new int[n][];
        for (int i = 0; i < array.length; i++) {//遍历 array 的每个元素
            //给每个一维数组(行) 开空间
            array[i] = new int[i + 1];
            //给每个一维数组(行) 赋值
            for (int j = 0; j < array[i].length; j++) {
                //每一行的第一个元素和最后一个元素都是 1
                if (j == 0 || j == array[i].length - 1) {
                    array[i][j] = 1;
                } else {//中间的元素
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }

            }
        }
        //输出杨辉三角
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {//遍历输出该行
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();//换行. }
        }

    }

}
