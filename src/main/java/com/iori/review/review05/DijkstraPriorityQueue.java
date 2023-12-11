package com.iori.review.review05;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 狄克斯特拉 单源最短路径算法
 */
public class DijkstraPriorityQueue {

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

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        dijkstra(graph, v1);

    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        source.dist = 0;
        for (Vertex vertex : graph) {
            queue.offer(vertex);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue);
            //拿到距离最短的节点
            Vertex cur = queue.peek();
            //更新距离最短节点附近的边
            if (!cur.visited) {
                updateNeighboursDist(cur,queue);
            }
            //删除已处理的节点
            queue.poll();
            cur.visited = true;
        }
        print(graph);
    }

    private static void updateNeighboursDist(Vertex cur, PriorityQueue<Vertex> queue) {
        for (Edge edge : cur.edges) {
            Vertex n = edge.linked;
            if (!n.visited) {
                int dist = cur.dist  + edge.weight;
                if (n.dist > dist) {
                    n.dist = dist;
                    n.prev = cur;
                    queue.offer(n);
                }
            }
        }
    }


    //打印方法
    public static void print(List<Vertex> graph) {
        for (Vertex v : graph) {
            System.out.println(v.name + " " + v.dist + " " + (v.prev != null ? v.prev.name : "null"));
        }

    }

}
