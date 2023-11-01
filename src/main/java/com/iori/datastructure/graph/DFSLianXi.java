package com.iori.datastructure.graph;

import java.util.List;
import java.util.Stack;

/**
 * 深度优先搜索 Depth-first search
 */
public class DFSLianXi {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        dfs(v1);
        //dfs2(v1);

    }

    /**
     * 深度优先遍历 迭代
     *
     * @param v
     */
    private static void dfs2(Vertex v) {


    }

    /**
     * 深度优先遍历  递归
     *
     * @param v
     */
    private static void dfs(Vertex v) {

    }


}
