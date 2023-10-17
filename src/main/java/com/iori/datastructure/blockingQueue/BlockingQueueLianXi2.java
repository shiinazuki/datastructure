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

    private E[] array;
    private int head;
    private int tail;
    private AtomicInteger size;


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

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {

        return null;
    }


}
