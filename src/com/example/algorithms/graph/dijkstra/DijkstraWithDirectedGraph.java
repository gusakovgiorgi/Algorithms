package com.example.algorithms.graph.dijkstra;

import com.example.algorithms.graph.kosaraju.Graph;
import com.example.datastructure.MinHeapForDijkstra;

import java.util.Comparator;
import java.util.Objects;

public class DijkstraWithDirectedGraph {
    public static int[] computePaths(Graph graph, int startVertex) {
        boolean[] x = new boolean[graph.getVerticesSize()];
        int[] distances = new int[graph.getVerticesSize()];
        MinHeapForDijkstra<DijkstraWithHeap.VertexKey> heap = new MinHeapForDijkstra<>(Comparator.comparingInt(o -> o.vertexKey));
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        x[startVertex] = true;
        distances[startVertex] = 0;
        for (int edgeIndex : graph.getIncidentEdges(startVertex).getOutcomeEdgesIndices()) {
            com.example.algorithms.graph.kosaraju.Edge edge = graph.getEdge(edgeIndex);
            int vertexIndex = edge.getHead() != startVertex ? edge.getHead() : edge.getTail();
            maintainHeap(new DijkstraWithHeap.VertexKey(vertexIndex, edge.getWeight()), heap);
        }
        while (heap.size() > 0) {
            DijkstraWithHeap.VertexKey currentVertexKey = heap.poll();
            int w = currentVertexKey.vertexIndex;

            x[w] = true;
            distances[w] = currentVertexKey.vertexKey;

            for (int edgeIndex : graph.getIncidentEdges(w).getOutcomeEdgesIndices()) {
                com.example.algorithms.graph.kosaraju.Edge edge = graph.getEdge(edgeIndex);
                int vIndex = edge.getHead() != w ? edge.getHead() : edge.getTail();
                if (x[vIndex]) continue;
                int vertexKey = distances[w] == Integer.MAX_VALUE ? Integer.MAX_VALUE : distances[w] + edge.getWeight();
                maintainHeap(new DijkstraWithHeap.VertexKey(vIndex, vertexKey), heap);
            }
        }

        return distances;
    }

    private static void maintainHeap(DijkstraWithHeap.VertexKey vertexKey, MinHeapForDijkstra<DijkstraWithHeap.VertexKey> heap) {
        DijkstraWithHeap.VertexKey containedVertexKey = heap.contains(vertexKey);
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
            DijkstraWithHeap.VertexKey vertexKey = (DijkstraWithHeap.VertexKey) o;
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
