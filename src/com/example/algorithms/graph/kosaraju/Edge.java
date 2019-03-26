package com.example.algorithms.graph.kosaraju;

import java.util.Objects;

public class Edge {
    private int tail;
    private int head;
    private int weight;

    public Edge(int tail, int head) {
        this(tail, head, 0);
    }

    public Edge(int tail, int head, int weight) {
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return tail == edge.tail &&
                head == edge.head &&
                weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tail, head, weight);
    }
}
