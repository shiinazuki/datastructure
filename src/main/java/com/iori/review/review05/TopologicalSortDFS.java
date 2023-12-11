package com.iori.review.review05;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序 递归版
 */
public class TopologicalSortDFS {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = List.of(new Edge(v3));
        v2.edges = List.of(new Edge(v3));
        v3.edges = List.of(new Edge(v4));
        v6.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        v7.edges = List.of();

        List<Vertex> graph = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6, v7));

        LinkedList<String> stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfs(vertex,stack);
        }
        System.out.println(stack);


    }

    private static void dfs(Vertex vertex, LinkedList<String> stack) {
        if (vertex.status == 2) {
            return;
        }
        if (vertex.status == 1) {
            throw new RuntimeException("有环");
        }
        vertex.status = 1;
        for (Edge edge : vertex.edges) {
            dfs(edge.linked,stack);
        }
        stack.push(vertex.name);
        vertex.status = 2;
    }


}
