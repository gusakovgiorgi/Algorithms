package com.example.algorithms.test;

import com.example.algorithms.graph.AdjacencyListGraph;
import com.example.algorithms.graph.ShortestPathsBFS;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ShortestPathsBFSTest {

    @Test
    public void findShortestPaths() {
        AdjacencyListGraph graph = new AdjacencyListGraph(6);
        graph.add(0, Arrays.asList(1, 2));
        graph.add(1, Arrays.asList(0, 3));
        graph.add(2, Arrays.asList(0, 3, 4));
        graph.add(3, Arrays.asList(1, 2, 4, 5));
        graph.add(4, Arrays.asList(3, 2, 5));
        graph.add(5, Arrays.asList(3, 4));

        int[] expectedPathesFrom0 = new int[]{0, 1, 1, 2, 2, 3};
        ShortestPathsBFS shortestPathsBFS = new ShortestPathsBFS();
        shortestPathsBFS.findShortestPaths(graph, 0);
        assertArrayEquals(expectedPathesFrom0, shortestPathsBFS.getVertexPathNumbers());
    }
}