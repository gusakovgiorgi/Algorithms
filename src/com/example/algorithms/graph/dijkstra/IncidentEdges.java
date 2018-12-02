package com.example.algorithms.graph.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class IncidentEdges {
    private List<Edge> edges;

    public IncidentEdges() {
        edges = new ArrayList<>();
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
