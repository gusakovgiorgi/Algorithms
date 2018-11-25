package com.example.algorithms.graph.kosaraju;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<IncidentEdges> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        addIncidentEdge(edge, edges.size() - 1);
    }

    private void addIncidentEdge(Edge edge, int indexOfEdge) {
        addOutcomeEdge(edge, indexOfEdge);
        addIncomeEdge(edge, indexOfEdge);
    }

    private void addIncomeEdge(Edge edge, int indexOfEdge) {
        int head = edge.getHead();
        // Increase if needed
        if (vertices.size() - 1 < head) {
            int addedSize = head - vertices.size() + 1;
            for (int i = 0; i < addedSize; i++) {
                vertices.add(new IncidentEdges());
            }
        }
        vertices.get(head).getIncomeEdgesIndices().add(indexOfEdge);
    }

    private void addOutcomeEdge(Edge edge, int indexOfEdge) {
        int tail = edge.getTail();
        // Increase if needed
        if (vertices.size() - 1 < tail) {
            int addedSize = tail - vertices.size() + 1;
            for (int i = 0; i < addedSize; i++) {
                vertices.add(new IncidentEdges());
            }
        }
        vertices.get(tail).getOutcomeEdgesIndices().add(indexOfEdge);
    }

    public int getVerticesSize() {
        return vertices.size();
    }

    public int getEgesSize() {
        return edges.size();
    }

    public IncidentEdges getIncidentEdges(int index) {
        return vertices.get(index);
    }

    public Edge getEdge(int index) {
        return edges.get(index);
    }
}
