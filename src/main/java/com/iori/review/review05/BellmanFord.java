package com.iori.review.review05;

import java.util.List;

/**
 * Bellman-Ford 算法 可以处理负边
 */
public class BellmanFord {

    public static void main(String[] args) {
        // 正常情况
      /*  Vertex v1 = new Vertex("v1");
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
        List<Vertex> graph = List.of(v4, v5, v6, v1, v2, v3);
*/

        // 负边情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        // 负环情况
      /*  Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -4));
        v3.edges = List.of(new Edge(v4, 1), new Edge(v1, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);*/

        bellmanFord(graph, v1);
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        for (int i = 0; i < graph.size() - 1; i++) {
            for (Vertex vertex : graph) {
                for (Edge edge : vertex.edges) {
                    Vertex n = edge.linked;
                    if (vertex.dist != Integer.MAX_VALUE && n.dist > vertex.dist + edge.weight) {
                        n.dist = vertex.dist + edge.weight;
                        n.prev = vertex;
                    }
                }
            }
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex);
        }
    }


}
