package com.example.algorithms.graph.kosaraju;

import java.util.ArrayList;
import java.util.List;

public class IncidentEdges {
    private List<Integer> incomeEdgesIndices;
    private List<Integer> outcomeEdgesIndices;

    public IncidentEdges() {
        incomeEdgesIndices = new ArrayList<>();
        outcomeEdgesIndices = new ArrayList<>();
    }

    public List<Integer> getIncomeEdgesIndices() {
        return incomeEdgesIndices;
    }

    public List<Integer> getOutcomeEdgesIndices() {
        return outcomeEdgesIndices;
    }
}
