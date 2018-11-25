package com.example.algorithms.test;

import com.example.algorithms.graph.AdjacencyListGraph;
import com.example.algorithms.graph.ConnectedComponentsBFS;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ConnectedComponentsBFSTest {

    @Test
    public void getConnectedComponents() {

        AdjacencyListGraph graph = new AdjacencyListGraph(10);
        graph.add(0, Arrays.asList(1, 2));
        graph.add(1, Arrays.asList(0, 3));
        graph.add(2, Arrays.asList(0, 3, 4));
        graph.add(3, Arrays.asList(1, 2, 4, 5));
        graph.add(4, Arrays.asList(3, 2, 5));
        graph.add(5, Arrays.asList(3, 4));

        graph.add(6, Arrays.asList(7, 8));
        graph.add(7, Arrays.asList(6, 8));
        graph.add(8, Arrays.asList(6, 7));

        graph.add(9, Collections.emptyList());

        Assert.assertEquals(3, ConnectedComponentsBFS.getConnectedComponents(graph));
    }
}