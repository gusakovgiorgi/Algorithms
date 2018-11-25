package com.example.algorithms.graph.minimum_cut.ramdomized_contruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Serializable {
    private int number;
    private List<Vertex> connectedVertices = new ArrayList<>();

    public Vertex(int number, List<Vertex> connectedVertices) {
        this.number = number;
        this.connectedVertices = connectedVertices;
    }

    public Vertex(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public List<Vertex> getConnectedVertices() {
        return connectedVertices;
    }

    public Vertex getVertex(int number) {
        Vertex equalVertex = new Vertex(number);
        for (Vertex vertex : connectedVertices) {
            if (vertex.equals(equalVertex)) {
                return vertex;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return number == vertex.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "number=" + number +
                '}';
    }
}
