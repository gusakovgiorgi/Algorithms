package com.example.algorithms.dynamic_programming.bellman_ford;

import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;

public class BellManFordAlgorithm {

    public static int[] computeShortestPath(Graph graph, int vertexIndex) {
        int A[][] = new int[graph.getEgesSize()][graph.getVerticesSize()];
        for (int i = 0; i < graph.getVerticesSize(); i++) {
            if (i == vertexIndex) continue;
            A[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < graph.getVerticesSize() - 1; i++) {
            for (int v = 0; v < graph.getVerticesSize(); v++) {
                A[i][v] = Math.min(A[i - 1][v], getMinimumFromEdge(i, v, graph, A));
            }
        }
        return A[graph.getVerticesSize() - 2];
    }

    private static int getMinimumFromEdge(int i, int v, Graph graph, int[][] A) {
        int min = Integer.MAX_VALUE;
        for (int incomeEdgeIndex : graph.getIncidentEdges(v).getIncomeEdgesIndices()) {
            Edge incomeEdge = graph.getEdge(incomeEdgeIndex);
            int adjacentVertex = incomeEdge.getHead() == v ? incomeEdge.getTail() : incomeEdge.getHead();
            int currentLength = A[i - 1][adjacentVertex] == Integer.MAX_VALUE ? Integer.MAX_VALUE :
                    A[i - 1][adjacentVertex] + incomeEdge.getWeight();
            if (currentLength < min) {
                min = currentLength;
            }
        }
        return min;
    }
}
