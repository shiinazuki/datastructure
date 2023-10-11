package com.iori.datastructure.blockingQueue;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现 阻塞队列
 * 读写锁
 *
 * @param <E>
 */
public class BlockingQueue2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    /**
     * 判断队列是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return size.get() == 0;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    private boolean isFull() {
        return size.get() == array.length;
    }

    /**
     * 添加方法
     *
     * @param e
     * @throws InterruptedException
     */
    @Override
    public void offer(E e) throws InterruptedException { //poll 等待队列非空
        tailLock.lockInterruptibly();
        int num;//代表新增前 元素个数
        try {
            // 队列满则等待
            while (isFull()) {
                tailWaits.await();
            }
            //队列不满 则入队
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            //修改size
             /*
                1. 读取成员变量size的值
                2. 自增
                3. 结果写回成员变量size
             */
            //原子自增
            num = size.getAndIncrement();
            //自增前加1 不满 仍有空位 唤醒别的offer线程
            if(num + 1 < array.length) {
                tailWaits.signal();
            }

        } finally {
            tailLock.unlock();
        }
        //放在平级 避免死锁
        //唤醒等待非空的poll线程
        if (num == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    /**
     * 添加元素 在有限的时间等待
     *
     * @param e
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        int num; //取走前的元素个数
        headLock.lockInterruptibly();
        try {
            //先判断是否为空
            while (isEmpty()) {
                headWaits.await();
            }
            //取出头元素 并置空
            e = array[head];
            array[head] = null; //help gc
            if (++head == array.length) {
                head = 0;
            }
            //修改size
             /*
                1. 读取成员变量size的值
                2. 自增减
                3. 结果写回成员变量size
             */
            //原子自减
            num = size.getAndDecrement();
            // 如果有元素 则调用poll来取走元素  级联唤醒
            if (num > 1) {
                headWaits.signal();
            }

        } finally {
            headLock.unlock();
        }
        //放在平级 避免死锁
        //唤醒等待不满的offer线程
        //加个判断 队列从满 -> 不满 由poll唤醒等待不满的offer线程
        if(num == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return e;

    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> queue = new BlockingQueue2<>(3);
        queue.offer("元素1");
        queue.offer("元素2");

        new Thread(() -> {
            try {
                queue.offer("元素3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }


}
