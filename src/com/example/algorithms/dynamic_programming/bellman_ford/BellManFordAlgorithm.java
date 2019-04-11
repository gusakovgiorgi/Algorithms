package com.example.algorithms.dynamic_programming.bellman_ford;

import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;

public class BellManFordAlgorithm {

    public static int[] computeShortestPath(Graph graph, int vertexIndex) {
        int A[][] = new int[2][graph.getVerticesSize()];
        for (int i = 0; i < graph.getVerticesSize(); i++) {
            if (i == vertexIndex) continue;
            A[0][i] = Integer.MAX_VALUE;
        }

        int previousIndex = 0;
        int currentIndex = 1;
        // Compute until n to detect negative cycle
        for (int i = 1; i < graph.getVerticesSize(); i++) {
            for (int v = 0; v < graph.getVerticesSize(); v++) {
                A[currentIndex][v] = Math.min(A[previousIndex][v], getMinimumFromEdge(v, graph, A, previousIndex));
            }
            int temp = previousIndex;
            previousIndex = currentIndex;
            currentIndex = temp;

            if (canExit(A)) {
                break;
            } else if (i == graph.getVerticesSize() - 1) {
                throw new IllegalArgumentException("graph contains negative cycle");
            }
        }

        // swap again, because we don't compute any
        currentIndex = previousIndex;
        return A[currentIndex];
    }

    private static boolean canExit(int[][] A) {
        for (int i = 0; i < A[0].length; i++) {
            if (A[0][i] != A[1][i]) {
                return false;
            }
        }
        return true;
    }

    private static int getMinimumFromEdge(int v, Graph graph, int[][] A, int previousIndex) {
        int min = Integer.MAX_VALUE;
        for (int incomeEdgeIndex : graph.getIncidentEdges(v).getIncomeEdgesIndices()) {
            Edge incomeEdge = graph.getEdge(incomeEdgeIndex);
            int adjacentVertex = incomeEdge.getHead() == v ? incomeEdge.getTail() : incomeEdge.getHead();
            int currentLength = A[previousIndex][adjacentVertex] == Integer.MAX_VALUE ? Integer.MAX_VALUE :
                    A[previousIndex][adjacentVertex] + incomeEdge.getWeight();
            if (currentLength < min) {
                min = currentLength;
            }
        }
        return min;
    }
}
