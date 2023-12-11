package com.iori.review.review05;

import java.util.LinkedList;
import java.util.List;

/**
 * 图的广度优先遍历
 */
public class BFS {

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
        v6.edges = List.of(new Edge(v5));

        bfs(v1);

    }

    //迭代
    private static void bfs(Vertex vertex) {
        LinkedList<Vertex> queue = new LinkedList<>();
        vertex.visited = true;
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            System.out.println(poll.name);
            for (Edge edge : poll.edges) {
                if (!edge.linked.visited) {
                    queue.offer(edge.linked);
                    edge.linked.visited = true;
                }
            }
        }
    }

}
