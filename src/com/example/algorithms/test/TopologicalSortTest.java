package com.example.algorithms.test;

import com.example.algorithms.graph.AdjacencyListGraph;
import com.example.algorithms.graph.TopologicalSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TopologicalSortTest {

    @Test
    public void getTopologicalNumbersForVertex() {

        AdjacencyListGraph graph = new AdjacencyListGraph(4);
        graph.add(0, Arrays.asList(1, 2));
        graph.add(1, Arrays.asList(3));
        graph.add(2, Arrays.asList(3));
        graph.add(3, Collections.emptyList());

        Map<Integer, Integer> mapping = TopologicalSort.getTopologicalNumbersForVertex(graph);
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(0, 0);
        expected.put(1, 2);
        expected.put(2, 1);
        expected.put(3, 3);

        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), mapping.get(i));
        }
    }
}