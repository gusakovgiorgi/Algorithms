package com.example.algorithms.test;

import com.example.algorithms.minimum_cut.ramdomized_contruction.MinCutGraph;
import com.example.algorithms.minimum_cut.ramdomized_contruction.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

public class MinCutGraphTest {


    @Test
    public void addVertexWithNeighbours() {
        MinCutGraph graph = new MinCutGraph();
        Vertex vert1 = new Vertex(1);
        Vertex vert2 = new Vertex(2);
        Vertex vert3 = new Vertex(3);
        Vertex vert4 = new Vertex(4);
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert1, vert2, vert3));
        Assert.assertEquals(graph.getAllVertices().size(), 4);
        Assert.assertEquals(graph.getVertex(1).getConnectedVertices().size(), 2);
        Assert.assertTrue(graph.getVertex(1).getConnectedVertices().contains(vert2));
        Assert.assertTrue(graph.getVertex(1).getConnectedVertices().contains(vert4));
        //No duplicates
        Set<Vertex> vertSet = new HashSet<>(Arrays.asList(vert1, vert2, vert3, vert4));
        for (Vertex vertex : graph.getAllVertices()) {
            if (!vertSet.contains(vertex)) {
                fail("find duplicates");
            }
            for (Vertex neighbour : vertex.getConnectedVertices()) {
                if (!vertSet.contains(neighbour)) {
                    fail("find duplicates");
                }
            }

        }
    }

    @Test
    public void connect() {
        MinCutGraph graph = new MinCutGraph();
        Vertex vert1 = new Vertex(1);
        Vertex vert2 = new Vertex(2);
        Vertex vert3 = new Vertex(3);
        Vertex vert4 = new Vertex(4);
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert1, vert2, vert3));
        graph.connect(vert1, vert2);
        Assert.assertTrue(graph.getVertex(1).getConnectedVertices().size() == 3);

    }

    @Test
    public void contraction() {
        MinCutGraph graph = new MinCutGraph();
        Vertex vert1 = new Vertex(1);
        Vertex vert2 = new Vertex(2);
        Vertex vert3 = new Vertex(3);
        Vertex vert4 = new Vertex(4);
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert1, vert2, vert3));
        graph.contraction(vert1, vert4);

        Assert.assertTrue(graph.getVertex(1).getConnectedVertices().size() == 3);
        Assert.assertEquals(graph.getVertex(1).getVertex(3), vert3);
        Assert.assertEquals(graph.getAllVertices().size(), 3);

        for (Vertex vertex : graph.getAllVertices()) {
            if (vertex.equals(vert4)) {
                fail("find duplicates");
            }
            for (Vertex neighbour : vertex.getConnectedVertices()) {
                if (neighbour.equals(vert4)) {
                    fail("find duplicates");
                }
            }

        }
    }

    @Test
    public void deleteVertex() {
        MinCutGraph graph = new MinCutGraph();
        Vertex vert1 = new Vertex(1);
        Vertex vert2 = new Vertex(2);
        Vertex vert3 = new Vertex(3);
        Vertex vert4 = new Vertex(4);
        graph.addVertexWithNeighbours(vert1, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert2, Arrays.asList(vert1, vert4));
        graph.addVertexWithNeighbours(vert3, Arrays.asList(vert2, vert4));
        graph.addVertexWithNeighbours(vert4, Arrays.asList(vert1, vert2, vert3));
        graph.deleteVertex(vert4);

        Assert.assertTrue(graph.getVertex(1).getConnectedVertices().size() == 1);
        Assert.assertEquals(graph.getVertex(1).getVertex(4), null);
        Assert.assertEquals(graph.getVertex(3).getVertex(4), null);
        Assert.assertEquals(graph.getAllVertices().size(), 3);

        for (Vertex vertex : graph.getAllVertices()) {
            if (vertex.equals(vert4)) {
                fail("find duplicates");
            }
            for (Vertex neighbour : vertex.getConnectedVertices()) {
                if (neighbour.equals(vert4)) {
                    fail("find duplicates");
                }
            }

        }
    }

}