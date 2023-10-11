package com.iori.datastructure.priorityqueue;


import com.iori.datastructure.linkedlist.ListNode;

/**
 * 小顶堆
 * 数字越小 优先级越高
 */
public class MinHeap {

    /*
              min
        1->4->5->null
        1->3->4->null
        2->6->null

        小顶堆
            1 2 4
        新链表
            s->1
     */

    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        array = new ListNode[capacity];
    }

    public boolean offer(ListNode offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.val < array[parent].val) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;

        return true;
    }

    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        ListNode listNode = array[size];
        array[size] = null;
        //下潜
        down(0);

        return listNode;
    }

    /**
     * 下潜方法
     *
     * @param parent
     */
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left].val < array[min].val) {
            min = left;
        }
        if (right < size && array[right].val < array[min].val) {
            min = right;
        }
        if (min != parent) {
            swap(min, parent);
            down(min);
        }

    }

    /**
     * 交换元素
     *
     * @param head
     * @param tail
     */
    private void swap(int head, int tail) {
        ListNode temp = array[head];
        array[head] = array[tail];
        array[tail] = temp;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
