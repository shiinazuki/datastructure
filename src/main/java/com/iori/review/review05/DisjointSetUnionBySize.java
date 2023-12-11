package com.iori.review.review05;

/**
 * <h3>不相交集合（并查集合）</h3>
 */
public class DisjointSetUnionBySize {

    int[] s;
    int[] size;

    public DisjointSetUnionBySize() {
    }

    public DisjointSetUnionBySize(int size) {
       s = new int[size];
       this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }
    }

    public int find(int x) {
      if (x == s[x]) {
          return x;
      }
      return s[x] = find(s[x]);
    }

    public void union(int x, int y) {
        if (size[x] < size[y]) {
            int t = x;
            x = y;
            y = t;
        }
        size[y] = x;
        size[y] = size[x] + size[y];
    }

}
