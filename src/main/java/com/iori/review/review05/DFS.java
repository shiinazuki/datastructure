package com.iori.review.review05;

import java.util.LinkedList;
import java.util.List;

/**
 * 图的深度优先遍历
 */
public class DFS {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9),
                new Edge(v2, 7),
                new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));
        //dfs(v1);
        dfs2(v1);

    }

    //迭代
    private static void dfs2(Vertex vertex) {
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            pop.visited = true;
            System.out.println(pop.name);
            for (Edge edge : pop.edges) {
                if (!edge.linked.visited) {
                    stack.push(edge.linked);
                }
            }
        }
    }

    //递归
    private static void dfs(Vertex vertex) {
        vertex.visited = true;
        System.out.println(vertex.name);
        for (Edge edge : vertex.edges) {
            if (!edge.linked.visited) {
                dfs(edge.linked);
            }
        }
    }


}
