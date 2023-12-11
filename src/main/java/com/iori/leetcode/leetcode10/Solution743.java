package com.iori.leetcode.leetcode10;

import java.util.Arrays;
import java.util.List;


/**
 * 网络延迟时间
 */
public class Solution743 {

    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4, k = 2;
        System.out.println(networkDelayTime0(times, n, k));
    }

    public static int networkDelayTime0(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        //邻接矩阵存储边信息
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(edges[i], INF);
        }
        for (int[] time : times) {
            //边序号从0开始
            int x = time[0] - 1;
            int y = time[1] - 1;
            edges[x][y] = time[2];
        }
        //从源点到某点的距离数组
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        //由于从k开始 所以该点距离设为0,也即源点
        dist[k - 1] = 0;
        //节点是否被更新 数组
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            //在还未确定最短的点中 寻找距离最小的点
            int x = -1;
            for (int y = 0; y < n; y++) {
                if (!visited[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            //用该点更新所有其他点的距离
            visited[x] = true;
            for (int y = 0; y < n; y++) {
                dist[y] =Math.min(dist[y], dist[x] + edges[x][y]);
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }


}
