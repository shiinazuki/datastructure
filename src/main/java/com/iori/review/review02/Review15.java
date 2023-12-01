package com.iori.review.review02;

import com.iori.datastructure.stack.Stack;

import java.util.LinkedList;

/**
 * 数组实现栈
 */
public class Review15<E> implements Stack<E> {

    private final E[] array;
    private int top = 0;

    public Review15(int top) {
        this.top = top;
        array= (E[]) new Object[top];
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[top] = value;
        top++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E e = array[top - 1];
        top--;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }


}
