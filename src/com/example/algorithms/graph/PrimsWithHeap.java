package com.example.algorithms.graph;

import com.example.algorithms.graph.dijkstra.Edge;
import com.example.algorithms.graph.dijkstra.UndirectedGraph;
import com.example.datastructure.MinHeapForDijkstra;

import java.util.Comparator;
import java.util.Objects;

public class PrimsWithHeap {

    public static long computePaths(UndirectedGraph graph, int startVertex) {
        boolean[] x = new boolean[graph.getVerticesSize()];
        long costsOfMSP = 0;
        MinHeapForDijkstra<VertexKey> heap = new MinHeapForDijkstra<>(Comparator.comparingInt(o -> o.vertexKey));
        x[startVertex] = true;
        for (Edge edge : graph.getIncidentEdges(startVertex).getEdges()) {
            int vertexIndex = edge.getHead() != startVertex ? edge.getHead() : edge.getTail();
            maintainHeap(new VertexKey(vertexIndex, edge.getWeight()), heap);
        }
        while (heap.size() > 0) {
            VertexKey currentVertexKey = heap.poll();
            int w = currentVertexKey.vertexIndex;

            x[w] = true;
            costsOfMSP += currentVertexKey.vertexKey;

            for (Edge edge : graph.getIncidentEdges(w).getEdges()) {
                int vIndex = edge.getHead() != w ? edge.getHead() : edge.getTail();
                if (x[vIndex]) continue;
                maintainHeap(new VertexKey(vIndex, edge.getWeight()), heap);
            }
        }

        return costsOfMSP;
    }

    private static void maintainHeap(VertexKey vertexKey, MinHeapForDijkstra<VertexKey> heap) {
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
