package com.iori.datastructure.stack;

/**
 * 双栈模拟队列
 * 调用push pop等方法的次数最多100次
 */
public class E04Leetcode232LianXi {


    /*
    队列头        队列尾
    b
    顶   底     底   顶
    s1              s2

    队列尾添加
        s2.push(a)
        s2.push(b)

    队列头移除
        先把 s2 的所有元素移动到 s1
        s1.pop()

    */
    ArrayStack<Integer> inStack = new ArrayStack<>(100);
    ArrayStack<Integer> outStack = new ArrayStack<>(100);

    public void push(int x) {
        inStack.push(x);
    }

    public Integer pop() {
        if (outStack.isEmpty()) {
            inOut();
        }
        return outStack.pop();
    }

    public Integer peek() {
        if (outStack.isEmpty()) {
            inOut();
        }
        return outStack.peek();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }


    public void inOut() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

}
