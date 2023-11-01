package com.iori.datastructure.graph;

import java.util.Arrays;

/**
 * <h3>不相交集合（并查集合）</h3>
 */
public class DisjointSetUnionBySizeLianXi {
   int[] s;
   int[] size;
    // 索引对应顶点
    // 元素是用来表示与之有关系的顶点
    /*
        索引  0  1  2  3  4  5  6
        元素 [0, 1, 2, 3, 4, 5, 6] 表示一开始顶点直接没有联系（只与自己有联系）

    */

    public DisjointSetUnionBySizeLianXi(int size) {
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

        return 0;
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    public void union(int x, int y) {

    }

    @Override
    public String toString() {
        return "内容：" + Arrays.toString(s) + "\n大小：" + Arrays.toString(size);
    }


    public static void main(String[] args) {
        DisjointSetUnionBySizeLianXi set = new DisjointSetUnionBySizeLianXi(5);

        set.union(1, 2);
        set.union(3, 4);
        set.union(1, 3);
        System.out.println(set);

        set.union(0, 1);
        System.out.println(set);


    }


}
