package com.iori.datastructure.graph;

import org.checkerframework.checker.units.qual.min;

import java.util.ArrayList;
import java.util.List;

/**
 * 狄克斯特拉 单源最短路径算法
 */
public class DijkstraLianXi {

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


    }



}
