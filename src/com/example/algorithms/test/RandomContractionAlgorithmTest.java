package com.example.algorithms.test;

import com.example.algorithms.minimum_cut.ramdomized_contruction.MinCutGraph;
import com.example.algorithms.minimum_cut.ramdomized_contruction.RandomContractionAlgorithm;
import com.example.algorithms.minimum_cut.ramdomized_contruction.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class RandomContractionAlgorithmTest {

    @Test
    public void getMinCutEdgesCount() throws IOException, ClassNotFoundException {
        MinCutGraph graph = new MinCutGraph();
        Vertex vert1 = new Vertex(1);
        Vertex vert2 = new Vertex(2);
        Vertex vert3 = new Vertex(3);
        Vertex vert4 = new Vertex(4);
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert3));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert3, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert1, vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert2, vert3));

        for (int i = 0; i > 1000; i++) {
            MinCutGraph experGraph = graph.deepCopy(graph);
            int number = RandomContractionAlgorithm.getMinCutEdgesCount(experGraph);
            Assert.assertTrue(number == 2 || number == 3);
        }

        vert1 = new Vertex(1);
        vert2 = new Vertex(2);
        vert3 = new Vertex(3);
        vert4 = new Vertex(4);
        Vertex vert5 = new Vertex(5);
        Vertex vert6 = new Vertex(6);
        Vertex vert7 = new Vertex(7);
        Vertex vert8 = new Vertex(8);

        graph = new MinCutGraph();
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert3, vert4, vert7));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert3, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert1, vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert1, vert2, vert3, vert5));
        graph.addVertexWithNeighbours(vert5, Arrays.asList(vert4, vert6, vert7, vert8));
        graph.addVertexWithNeighbours(vert6, Arrays.asList(vert5, vert7, vert8));
        graph.addVertexWithNeighbours(vert7, Arrays.asList(vert1, vert5, vert6, vert8));
        graph.addVertexWithNeighbours(vert8, Arrays.asList(vert5, vert6, vert7));

        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            MinCutGraph experGraph = graph.deepCopy(graph);
            int number = RandomContractionAlgorithm.getMinCutEdgesCount(experGraph);
            if (number < minCut) {
                minCut = number;
            }
        }
        Assert.assertTrue(minCut == 2);

    }
}