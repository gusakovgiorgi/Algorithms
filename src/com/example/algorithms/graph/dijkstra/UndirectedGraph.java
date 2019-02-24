package com.example.algorithms.graph.dijkstra;

import java.util.*;

public class UndirectedGraph {
    private List<IncidentEdges> vertices;
    private Map<Edge, Edge> edges;

    public UndirectedGraph() {
        vertices = new ArrayList<>();
        edges = new HashMap<>();
    }

    public void addEdge(Edge edge) {
        if (!edges.containsKey(edge)) {
            edges.put(edge, edge);
            addIncidentEdge(edge);
        }
    }

    private void addIncidentEdge(Edge edge) {
        addEdgeToTail(edge);
        addEdgeToHead(edge);
    }

    private void addEdgeToHead(Edge edge) {
        int head = edge.getHead();
        // Increase if needed
        if (vertices.size() - 1 < head) {
            int addedSize = head - vertices.size() + 1;
            for (int i = 0; i < addedSize; i++) {
                vertices.add(new IncidentEdges());
            }
        }
        vertices.get(head).getEdges().add(edge);
    }

    private void addEdgeToTail(Edge edge) {
        int tail = edge.getTail();
        // Increase if needed
        if (vertices.size() - 1 < tail) {
            int addedSize = tail - vertices.size() + 1;
            for (int i = 0; i < addedSize; i++) {
                vertices.add(new IncidentEdges());
            }
        }
        vertices.get(tail).getEdges().add(edge);
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


    public List<Edge> getEdges() {
        return new LinkedList<>(edges.keySet());
    }
}

