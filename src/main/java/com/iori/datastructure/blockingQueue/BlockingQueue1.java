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
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    /**
     * 判断队列是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    private boolean isFull() {
        return size == array.length;
    }

    /**
     * 添加方法
     *
     * @param e
     * @throws InterruptedException
     */
    @Override
    public void offer(E e) throws InterruptedException { //poll 等待队列非空
        lock.lockInterruptibly();
        //如果队列满了 去等待
        while (isFull()) {
            tailWaits.await();
        }
        try {
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            //唤醒等待线程
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 添加元素 在有限的时间等待
     * @param e
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long nanos = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) {
                if (nanos <= 0) {
                    return false;
                }
                nanos = tailWaits.awaitNanos(nanos);//最多等待多少纳秒
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            //如果队列为空 等待
            while (isEmpty()) {
                headWaits.await();
            }
            E e = array[head];
            array[head] = null; //help gc
            if (++head == array.length) {
                head = 0;
            }
            size--;
            //通知等待线程
            tailWaits.signal();
            return e;

        } finally {
            lock.unlock();
        }
    }
    @Override
    public String toString() {
        return Arrays.toString(array);
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        queue.offer("任务1");

        new Thread(()->{
            try {
                queue.offer("任务2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(()->{
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }


}
