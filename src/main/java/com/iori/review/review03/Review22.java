package com.iori.review.review03;

import com.iori.datastructure.blockingQueue.BlockingQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列 双锁实现
 */
public class Review22<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    public Review22(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock headLock = new ReentrantLock(); //给poll方法使用
    private ReentrantLock tailLock = new ReentrantLock(); //给offer方法使用
    private Condition headWait = headLock.newCondition(); //给poll方法使用
    private Condition tailWait = tailLock.newCondition(); //给offer 方法使用


    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c;//添加前元素个数
        try {
            while (isFull()) {
                tailWait.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            //返回添加前的值 如果已经添加一个了 此时size == 1 则会返回 0
            c = size.getAndIncrement();
            //自增前 + 1 小于数组长度 说明还有空位
            if (c + 1 < array.length) {
                tailWait.signal();
            }

        } finally {
            tailLock.unlock();
        }


        //通知等待线程 难点
        if (c == 0) { //如果c 在添加后等于0 就唤醒
            headLock.unlock();
            try {
                headWait.signal();
            } finally {
                headLock.unlock();
            }
        }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e = null;
        int c; //取走前元素个数
        headLock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWait.await();
            }
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            //如果取走后 size == 1 那么会返回 2
            c = size.getAndDecrement();
            //如果取走元素前 个数 大于 1 说明有剩余元素 则唤醒
            if (c > 1) {
                headWait.signal();
            }

        } finally {
            headLock.unlock();
        }

        //通知等待线程 难点
        if (c == array.length) {
            tailLock.lock();
            try {
                tailWait.signal();
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    public boolean isEmpty() {
        return size.get() == 0;
    }

    public boolean isFull() {
        return size.get() == array.length;
    }

}
