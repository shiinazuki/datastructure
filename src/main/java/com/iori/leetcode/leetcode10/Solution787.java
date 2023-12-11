package com.iori.leetcode.leetcode10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * K 站中转内最便宜的航班
 */
public class Solution787 {

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int n = 3, src = 0, dst = 2, k = 1;
        System.out.println(findCheapestPrice(n, edges, src, dst, k));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int N = 110, INF = 0x3f3f3f3f;
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        for (int limit = 0; limit < k + 1; limit++) {
            int[] clone = dist.clone();
            for (int[] flight : flights) {
                int x = flight[0];
                int y = flight[1];
                int w = flight[2];
                dist[y] = Math.min(dist[y], clone[x] + w);
            }
        }
        return dist[dst] > INF / 2 ? -1 : dist[dst];
    }


}
