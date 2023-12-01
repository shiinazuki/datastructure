package com.iori.review.review03;

import com.iori.datastructure.blockingQueue.BlockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列 单锁实现
 */
public class Review21<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public Review21(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWait = lock.newCondition(); // 配合poll方法使用
    private Condition tailWait = lock.newCondition(); // 配合offer方法使用

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWait.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            //唤醒等待非空的线程
            headWait.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long nanos = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) {
                if (nanos <= 0) {
                    return false;
                }
                nanos = tailWait.awaitNanos(nanos); //最多等待多少纳秒
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            //唤醒等待非空的线程
            headWait.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWait.await();
            }
            E e = array[head];
            array[head] = null; //help GC
            if (head++ == array.length) {
                head = 0;
            }
            size--;
            //唤醒等待不满的offer线程
            tailWait.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

}
