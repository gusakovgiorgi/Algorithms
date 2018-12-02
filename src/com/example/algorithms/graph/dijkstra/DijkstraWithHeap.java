package com.example.algorithms.graph.dijkstra;

import com.example.datastructure.MinHeap;

import java.util.Comparator;
import java.util.Objects;

public class DijkstraWithHeap {


    public static int[] computePaths(UndirectedGraph graph, int startVertex) {
        boolean[] x = new boolean[graph.getVerticesSize()];
        int[] distances = new int[graph.getVerticesSize()];
        MinHeap<VertexKey> heap = new MinHeap<>(Comparator.comparingInt(o -> o.vertexKey));
        for (int i = 0; i < distances.length; i++) {
            distances[i] = 1000000;
        }
        x[startVertex] = true;
        distances[startVertex] = 0;
        for (Edge edge : graph.getIncidentEdges(startVertex).getEdges()) {
            int vertexIndex = edge.getHead() != startVertex ? edge.getHead() : edge.getTail();
            maintainHeap(new VertexKey(vertexIndex, edge.getWeight()), heap);
        }
        while (heap.size() > 0) {
            VertexKey currentVertexKey = heap.poll();
            int w = currentVertexKey.vertexIndex;

            x[w] = true;
            distances[w] = currentVertexKey.vertexKey;

            for (Edge edge : graph.getIncidentEdges(w).getEdges()) {
                int vIndex = edge.getHead() != w ? edge.getHead() : edge.getTail();
                if (x[vIndex]) continue;
                maintainHeap(new VertexKey(vIndex, distances[w] + edge.getWeight()), heap);
            }
        }

        return distances;
    }

    private static void maintainHeap(VertexKey vertexKey, MinHeap<VertexKey> heap) {
        VertexKey containedVertexKey = heap.contains(vertexKey);
        if (containedVertexKey != null) {
            if (vertexKey.vertexKey < containedVertexKey.vertexKey) {
                heap.remove(containedVertexKey);
                heap.add(vertexKey);
            }
        } else {
            heap.add(vertexKey);
        }
    }

    private static boolean isEligibleEdge(boolean[] x, Edge edge) {
        return ((!x[edge.getHead()] && x[edge.getTail()])
                || (x[edge.getHead()] && !x[edge.getTail()]));
    }

    static class VertexKey {
        int vertexIndex;
        int vertexKey;

        public VertexKey(int vertexIndex, int vertexKey) {
            this.vertexIndex = vertexIndex;
            this.vertexKey = vertexKey;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VertexKey vertexKey = (VertexKey) o;
            return vertexIndex == vertexKey.vertexIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertexIndex);
        }

        @Override
        public String toString() {
            return "VertexKey{" +
                    "vertexIndex=" + vertexIndex +
                    ", vertexKey=" + vertexKey +
                    '}';
        }
    }
}
