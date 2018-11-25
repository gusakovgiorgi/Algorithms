package com.example.algorithms.graph.minimum_cut.ramdomized_contruction;

public class MinCutSets {
    Vertex[] first;
    Vertex[] second;
    int crosses;

    public MinCutSets() {
    }

    public MinCutSets(Vertex[] first, Vertex[] second, int crosses) {
        this.first = first;
        this.second = second;
        this.crosses = crosses;
    }

    public Vertex[] getFirst() {
        return first;
    }

    public void setFirst(Vertex[] first) {
        this.first = first;
    }

    public Vertex[] getSecond() {
        return second;
    }

    public void setSecond(Vertex[] second) {
        this.second = second;
    }

    public int getCrosses() {
        return crosses;
    }

    public void setCrosses(int crosses) {
        this.crosses = crosses;
    }
}
