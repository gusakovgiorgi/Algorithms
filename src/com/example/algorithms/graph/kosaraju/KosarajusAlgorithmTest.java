package com.example.algorithms.graph.kosaraju;

import org.junit.Test;

public class KosarajusAlgorithmTest {

    @Test
    public void searchSCCs() {
        Graph graph = new Graph();
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 6));
        graph.addEdge(new Edge(2, 3));
        graph.addEdge(new Edge(2, 4));
        graph.addEdge(new Edge(3, 1));
        graph.addEdge(new Edge(3, 4));
        graph.addEdge(new Edge(4, 5));
        graph.addEdge(new Edge(5, 4));
        graph.addEdge(new Edge(6, 6));
        graph.addEdge(new Edge(6, 5));
        graph.addEdge(new Edge(7, 7));
        graph.addEdge(new Edge(7, 4));
        graph.addEdge(new Edge(7, 6));


        KosarajusAlgorithm algorithm = new KosarajusAlgorithm(graph);
        algorithm.searchSCCs();
        algorithm.printLeadersAndMostFive();
    }
}