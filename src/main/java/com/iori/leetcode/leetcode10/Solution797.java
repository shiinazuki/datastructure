package com.iori.leetcode.leetcode10;

import java.util.ArrayList;
import java.util.List;


/**
 * 所有可能的路径
 */
public class Solution797 {

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        dfs(graph,0,graph.length-1,result,cur);
        return result;
    }

    private static void dfs(int[][] graph, int x, int n, List<List<Integer>> result, List<Integer> cur) {
        if (x == n) {
            result.add(new ArrayList<>(cur));
        }
        for (int y : graph[x]) {
            cur.add(y);
            dfs(graph,y,n,result,cur);
            cur.remove(cur.size() - 1);
        }
    }


}
