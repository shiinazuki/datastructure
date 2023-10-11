package com.iori.algorithm.recursion_single;

import java.util.Arrays;

/**
 * 递归插入排序
 */
public class E05InsertionSort {


    public static void sort(int[] array) {
        insertion(array);
        System.out.println(Arrays.toString(array));
    }


    /**
     * 插入排序 循环法
     *
     * @param array
     */
    private static void insertion(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int temp = array[i];
            int j = i - 1; //已排序区域指针

            //没找到插入位置
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j]; //空出插入位置
                j--;
            }

            array[j + 1] = temp;
        }
    }


    public static void insertionLianXi(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }

    }


    /**
     * 递归插入排序
     *
     * @param array
     * @param low
     */
    private static void insertion1(int[] array, int low) {

        if (low == array.length) {
            return;
        }
        int temp = array[low];
        int i = low - 1; //已排序区域指针

        //没找到插入位置
        while (i >= 0 && array[i] > temp) {
            array[i + 1] = array[i]; //空出插入位置
            i--;
        }
        //找到位置了
        if (i + 1 != low) {
            array[i + 1] = temp;
        }

        insertion1(array, low + 1);

    }


    public static void insertionLianxi(int[] array, int i) {

        if (i == array.length) {
            return;
        }

        int temp = array[i];
        int j = i - 1;

        while (j >= 0 && array[j] > temp) {
            array[j + 1] = array[j];
            j--;
        }

        if (j + 1 != i) {
            array[j + 1] = temp;
        }
        insertionLianxi(array, i);


    }

     /*
      递归推导过程
      */
  /*  private static void insertion(int[] array, int low = 0) {


        int temp = array[low]; // 5
        int i = low - 1; //已排序区域指针 -1

        //没找到插入位置
        while (i >= 0 && array[i] > temp) {
            array[i + 1] = array[i]; //空出插入位置
            i--;
        }
        //找到位置了
        if (i + 1 != low) {
            array[i + 1] = temp;
        }

        private static void insertion(int[] array, int low = 1) {

            int temp = array[low]; //4
            int i = low - 1; //已排序区域指针 //0

            //没找到插入位置
            while (i >= 0 && array[i] > temp) {
                //[5 5 3 2 1]
                array[i + 1] = array[i]; //空出插入位置
                i--; //-1
            }
            //找到位置了
            if (i + 1 != low) {
                //[4 5 3 2 1]
                array[i + 1] = temp;
            }


            private static void insertion(int[] array, int low = 2) {

                int temp = array[low]; //3
                int i = low - 1; //已排序区域指针 1

                //没找到插入位置
                while (i >= 0 && array[i] > temp) {
                    //[4 5 5 2 1 ]  [4 4 5 2 1]
                    array[i + 1] = array[i]; //空出插入位置
                    i--; //0 -1
                }
                //找到位置了
                if (i + 1 != low) {
                    array[i + 1] = temp; //[3 4 5 2 1]
                }

                private static void insertion(int[] array, int low = 3) {


                    int temp = array[low]; //2
                    int i = low - 1; //已排序区域指针 2

                    //没找到插入位置
                    while (i >= 0 && array[i] > temp) {
                        //[3 4 5 5 1] [3 4 4 5 1] [3 3 4 5 1]
                        array[i + 1] = array[i]; //空出插入位置
                        i--; //1 0 -1
                    }
                    //找到位置了
                    if (i + 1 != low) {
                        //[2 3 4 5 1]
                        array[i + 1] = temp;
                    }

                    private static void insertion(int[] array, int low = 4) {


                        int temp = array[low]; //1
                        int i = low - 1; //已排序区域指针 3

                        //没找到插入位置
                        while (i >= 0 && array[i] > temp) {
                            //[2 3 4 5 5] [2 3 4 4 5] [2 3 3 4 5] [2 2 3 4 5]
                            array[i + 1] = array[i]; //空出插入位置
                            i--; //2 1 0 -1
                        }
                        //找到位置了
                        if (i + 1 != low) {
                            array[i + 1] = temp; // [1 2 3 4 5]
                        }


                    }

                }

            }



        }

    }*/


}
