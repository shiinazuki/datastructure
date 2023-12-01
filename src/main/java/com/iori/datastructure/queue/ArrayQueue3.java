package com.iori.datastructure.queue;

import java.util.Iterator;

/**
 * 环形数组实现队列
 * 使用时计算索引位置 而不是存储起来
 *
 * @param <E>
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {


    /*
    求模运算:
    -   如果除数是 2 的 n次方
    -   那么被除数的后 n 位即为余数(模)
    -   求被除数的后 n位方法 与 2^n - 1 按位与
     */

    private final E[] array;
    int head = 0;
    int tail = 0;

    public ArrayQueue3(int capacity) {
        //1.如果 capacity 不是2的 n 次方 抛异常
    /*    if ((capacity & capacity - 1) != 0) {
            throw new IllegalArgumentException("capacity 必须是2的幂");
        }*/
        //2. 改成 2^n 13 -> 16
        //求离c最近 比c大的2^n(方法2)
        capacity -= 1;
        capacity |= capacity >> 1;
        capacity |= capacity >> 2;
        capacity |= capacity >> 3;
        capacity |= capacity >> 4;
        capacity |= capacity >> 16;
        capacity += 1;
        System.out.println(capacity);
        array = (E[]) new Object[capacity];
    }

    public static void main(String[] args) {
        /*
        2^4     = 16
        2^4.?   = 30
        2^5     = 32
        (int)log2(30) + 1
       2

       log2(x) = log10(x) / log10(2)
         */

        int c = 32;

      /*  int n = (int) (Math.log10(c - 1) / Math.log10(2)) + 1;
        System.out.println(n);
        System.out.println(1 << n);*/

        //求离c最近 比c大的2^n(方法2)
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 3;
        c |= c >> 4;
        c |= c >> 16;
        c += 1;
        System.out.println(c);


    }


    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        //array[(int) (Integer.toUnsignedLong(tail) % array.length)] = value;
        array[tail & array.length - 1] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        //E value = array[(int) (Integer.toUnsignedLong(head) % array.length)];
        E value = array[head & array.length - 1];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        //return array[(int) (Integer.toUnsignedLong(head) % array.length)];
        return array[head & array.length - 1];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                //E value = array[(int) (Integer.toUnsignedLong(reversePrint) % array.length)];
                E value = array[p & array.length - 1];
                p++;
                return value;
            }
        };
    }
}
