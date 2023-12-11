package com.iori.review.review05;

import java.util.Arrays;

/**
 * <h3>不相交集合（并查集合）</h3>
 */
public class DisjointSet {

    int[] s;

    public DisjointSet(int size) {
        s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
    }

    public int find(int x) {
        if (x == s[x]) {
            return x;
        }
        return s[x] = find(s[x]);
    }

    public void union(int x, int y) {
        s[y] = x;
    }

    @Override
    public String toString() {
        return Arrays.toString(s);
    }


}
