package com.example.algorithms.minimum_cut.ramdomized_contruction;

import java.util.Objects;

public class Edge {
    private int number;
    private Vertex tail;
    private Vertex head;

    public Edge(int number, Vertex tail, Vertex head) {
        this.number = number;
        this.tail = tail;
        this.head = head;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Vertex getTail() {
        return tail;
    }

    public void setTail(Vertex tail) {
        this.tail = tail;
    }

    public Vertex getHead() {
        return head;
    }

    public void setHead(Vertex head) {
        this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return number == edge.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "number=" + number +
                '}';
    }
}
