package com.example.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {
    /**
     * Vertices
     */
    private int n;

    private List<List<Integer>> verticesList;

    public AdjacencyListGraph(int vertices) {
        this.n = vertices;
        verticesList = new ArrayList<>(n + 2);
    }

    public void add(int vetex, List<Integer> connectedVertices) {
        verticesList.add(vetex, connectedVertices);
    }

    public List<Integer> getConnectedVerticies(int vertex) {
        return verticesList.get(vertex);
    }

    public int getN() {
        return n;
    }
}
