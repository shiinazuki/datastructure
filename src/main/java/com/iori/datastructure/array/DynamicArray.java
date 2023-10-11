package com.iori.datastructure.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * 动态数组
 */
public class DynamicArray implements Iterable<Integer> {

    //数组的逻辑大小  控制数组有效元素的个数
    private int size = 0;
    //容量
    private int capacity = 10;

    private int[] array = {};


    /**
     * 将元素添加到数组的最后
     * 向最后位置添加元素
     *
     * @param element
     */
    public void addLast(int element) {
        add(size, element);

    }

    /**
     * 指定位置添加 元素
     *
     * @param index
     * @param element
     */
    public void add(int index, int element) {

        //扩容检查
        checkAndGrow();

        //添加逻辑
        if (index >= 0 && index < size) {
            //for (int i = index + 1; i < array.length; i++) {
            //    array[i] = array[index];
            //}
            System.arraycopy(array, index, array, index + 1, size - index);

        }
        array[index] = element;
        size++;

    }

    /**
     * 扩容检查方法
     */
    private void checkAndGrow() {
        //容量检查
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            //进行扩容, 按1.5倍扩容
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    /**
     * 按索引查询
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return -1;
    }


    /**
     * 根据索引移除元素
     *
     * @param index
     */
    public int remove(int index) {
        if (index >= 0 && index < size) {
            int removed = array[index];
            if (index < size - 1) {
                System.arraycopy(array, index + 1, array, index, size - index - 1);
            }
            size--;
            return removed;
        }
        return -1;
    }

    /**
     * 遍历元素的方法
     */
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            //System.out.println(array[i]);
            consumer.accept(array[i]);
        }
    }

    /**
     * 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() { //有没有下一个元素
                return i < size;
            }

            @Override
            public Integer next() { //返回当前元素 并移向下一个元素
                return array[i++];
            }
        };
    }


    /**
     * 流式遍历
     *
     * @return
     */
    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

}
