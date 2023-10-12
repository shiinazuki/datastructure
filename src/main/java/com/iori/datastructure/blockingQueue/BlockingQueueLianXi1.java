package com.iori.datastructure.blockingQueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁实现 阻塞队列
 *
 * @param <E>
 */
public class BlockingQueueLianXi1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueueLianXi1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }


    @Override
    public void offer(E e) throws InterruptedException {

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {

        return true;
    }

    @Override
    public E poll() throws InterruptedException {

        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        queue.offer("任务1");

        new Thread(() -> {
            try {
                queue.offer("任务2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(() -> {
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }


}
