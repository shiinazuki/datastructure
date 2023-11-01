package com.iori.datastructure.graph;

import java.util.Arrays;

/**
 * <h3>不相交集合（并查集合）</h3>
 */
public class DisjointSetUnionBySize {
    int[] s;
    int[] size;
    // 索引对应顶点
    // 元素是用来表示与之有关系的顶点
    /*
        索引  0  1  2  3  4  5  6
        元素 [0, 1, 2, 3, 4, 5, 6] 表示一开始顶点直接没有联系（只与自己有联系）

    */

    public DisjointSetUnionBySize(int size) {
        s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }
    }

    // find 是找到老大 - 优化1：路径压缩
    /*
        find(2) 0
          find(1) 0
           find(0)
     */
    public int find(int x) { // x = 2
        if (s[x] == x) {
            return x;
        }
        return s[x] = find(s[x]);
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    public void union(int x, int y) {
/*        if (size[x] < size[y]) {
            s[x] = y;
            size[y] = size[y] + size[x];
        } else {
            s[y] = x;
            //更新老大的元素个数
            size[x] = size[x] + size[y];
        }*/

        if (size[x] < size[y]) {
            int t = x;
            x = y;
            y = t;
        }
        s[y] = x;
        //更新老大的元素个数
        size[x] = size[x] + size[y];
    }

    @Override
    public String toString() {
        return "内容："+Arrays.toString(s) + "\n大小：" + Arrays.toString(size);
    }


    public static void main(String[] args) {
        DisjointSetUnionBySize set = new DisjointSetUnionBySize(5);

        set.union(1, 2);
        set.union(3, 4);
        set.union(1, 3);
        System.out.println(set);

        set.union(0, 1);
        System.out.println(set);


    }


}
