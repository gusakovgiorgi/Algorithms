package com.example.algorithms.test;

import com.example.algorithms.graph.AdjacencyListGraph;
import com.example.algorithms.graph.BFS;
import org.junit.Test;

import java.util.Arrays;

public class BFSTest {

    @Test
    public void searchBFS() {
        AdjacencyListGraph graph = new AdjacencyListGraph(6);
        graph.add(0, Arrays.asList(1, 2));
        graph.add(1, Arrays.asList(0, 3));
        graph.add(2, Arrays.asList(0, 3, 4));
        graph.add(3, Arrays.asList(1, 2, 4, 5));
        graph.add(4, Arrays.asList(3, 2, 5));
        graph.add(5, Arrays.asList(3, 4));

        BFS.searchBFS(graph, 0);

    }
}