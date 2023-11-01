package com.iori.datastructure.stack;

import com.iori.datastructure.queue.ArrayQueue3;

/**
 * 单队列模拟栈
 * 1. 调用push pop等方法的次数最多100
 * 2. 每次调用pop和top都能保证栈不为空
 */
public class E05Leetcode225LianXi {


    /*

        栈顶      栈底
        d    c    b    a
        队列头    队列尾

        queue.offer(a)
        queue.offer(b)
        queue.offer(c)

        push 添加
            - 将新加入元素，前面的所有元素从队列头移动到队列尾
        pop 移除
            - 直接移除队列头元素

     */
  ArrayQueue3<Integer> stack = new ArrayQueue3<>(10);
  int size = 0;

  public void push(int x) {
      stack.offer(x);
      for (int i = 0; i < size; i++) {
          stack.offer(stack.poll());
      }
      size++;
  }

  public Integer pop() {
      size--;
      return stack.poll();
  }

  public Integer peek() {
      return stack.peek();
  }
  public boolean isEmpty() {
      return stack.isEmpty();
  }



}
