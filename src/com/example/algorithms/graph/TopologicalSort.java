package com.example.algorithms.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort {

    private boolean[] explored;
    private Map<Integer, Integer> mapping;
    private int currentLabel;
    private AdjacencyListGraph graph;

    TopologicalSort(AdjacencyListGraph graph) {
        explored = new boolean[graph.getN()];
        mapping = new HashMap<>(graph.getN(), 1.01F);
        currentLabel = graph.getN() - 1;
        this.graph = graph;
    }

    public static Map<Integer, Integer> getTopologicalNumbersForVertex(AdjacencyListGraph graph) {
        return new TopologicalSort(graph).getTopologicaNumbersForVertexNonStatic();
    }

    Map<Integer, Integer> getTopologicaNumbersForVertexNonStatic() {
        for (int vertex = 0; vertex < graph.getN(); vertex++) {
            if (!explored[vertex]) {
                DFSRecursive(graph, vertex);
            }
        }
        return mapping;
    }

    private void DFSRecursive(AdjacencyListGraph graph, int startVertex) {
        explored[startVertex] = true;
        List<Integer> connectedVerticies = graph.getConnectedVerticies(startVertex);
        for (Integer connectedVert : connectedVerticies) {
            if (!explored[connectedVert]) {
                DFSRecursive(graph, connectedVert);
            }
        }
        mapping.put(startVertex, currentLabel--);
    }
}
