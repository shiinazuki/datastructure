package com.iori.datastructure.blockingQueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现 阻塞队列
 *
 * @param <E>
 */
public class BlockingQueueLianXi2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();


    public BlockingQueueLianXi2(int capacity) {
        array = (E[]) new Object[capacity];
    }


    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();


    public boolean isEmpty() {
        return size.get() == 0;
    }

    public boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int num;
        while (isFull()) {
            tailWaits.await();
        }
        try {
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            num = size.getAndIncrement();

            //减少加锁次数
            //自增 返回原来的值  +1 比较是否小于数组长度
            if (num + 1 < array.length) {
                tailWaits.signal();
            }


        } finally {
            tailLock.unlock();
        }

        if (num == 0) {
            headLock.lock();
            try {
                headWaits.signal();
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
        headLock.lockInterruptibly();
        E e;
        int num;
        while (isEmpty()) {
            headWaits.await();
        }
        try {
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            num = size.getAndDecrement();
            //唤醒
            if (num > 1) {
                headWaits.signal();
            }

        } finally {
            headLock.unlock();
        }
        //数组长度 -1 变成不满了 返回原来的值
        if (num == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }

        return e;

    }


}
