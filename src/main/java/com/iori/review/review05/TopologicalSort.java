package com.iori.review.review05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序
 */
public class TopologicalSort {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("网业基础");
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

        ArrayList<Vertex> graph = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6, v7));

        //统计每个节点的入度
        for (Vertex vertex : graph) {
            for (Edge edge : vertex.edges) {
                edge.linked.inDegree++;
            }
        }
        //将入度为0的节点放入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) {
                queue.offer(vertex);
            }
        }
        //将入度为0的节点从队列删除 并使邻边度数减1 减到0在入队
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            result.add(poll.name);
            for (Edge edge : poll.edges) {
                edge.linked.inDegree--;
                if (edge.linked.inDegree == 0) {
                    queue.offer(edge.linked);
                }
            }
        }

        if (result.size() != graph.size()) {
            System.out.println("有环");
        }else  {
            for (String s : result) {
                System.out.println(s);
            }
        }
        System.out.println(result.size());
        System.out.println(graph.size());

    }

}
