package com.iori.datastructure.graph;

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
        for (Vertex v : graph) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue);
            //3.选取当前顶点
            Vertex cur = queue.peek();
            //4.更新当前顶点邻居距离
            if(!cur.visited) {
                updateNeighboursDist(cur, queue);
            }
            //5.移除当前顶点
            queue.poll();
            cur.visited = true;
        }
        for (Vertex v : graph) {
            System.out.println(v.name + " " + v.dist + " " + (v.prev != null ? v.prev.name : "null"));
        }

    }

    /**
     * 更新当前顶点邻居距离
     *
     * @param cur
     * @param queue
     */
    private static void updateNeighboursDist(Vertex cur, PriorityQueue<Vertex> queue) {
        for (Edge edge : cur.edges) {
            Vertex n = edge.linked;
            //如果邻居在集合里 才进行更新距离的处理
            if (!n.visited) {
                int dist = cur.dist + edge.weight;
                if (dist < n.dist) {
                    n.dist = dist;
                    n.prev = cur;
                    queue.offer(n);
                }
            }
        }
    }

}
