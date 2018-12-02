package com.example.algorithms.graph.dijkstra;

import java.util.List;

public class DijkstraNaive {

    public static int[] computePaths(UndirectedGraph graph, int startVertex) {
        boolean[] x = new boolean[graph.getVerticesSize()];
        int[] distances = new int[graph.getVerticesSize()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = 1000000;
        }
        x[startVertex] = true;
        distances[startVertex] = 0;
        int remainNodes = graph.getVerticesSize() - 1;
        while (remainNodes > 0) {
            List<Edge> edges = graph.getEdges();
            int addedVertex = -1;
            int lengthToAddedVertex = 0;
            int minimumPath = Integer.MAX_VALUE;
            for (Edge edge : edges) {
                if (isEligibleEdge(x, edge)) {
                    int v, w;
                    if (x[edge.getHead()]) {
                        v = edge.getHead();
                        w = edge.getTail();
                    } else {
                        v = edge.getTail();
                        w = edge.getHead();
                    }
                    int pathLength = distances[v] + edge.getWeight();
                    if (pathLength < minimumPath) {
                        addedVertex = w;
                        minimumPath = pathLength;
                        lengthToAddedVertex = pathLength;
                    }
                }
            }
            x[addedVertex] = true;
            distances[addedVertex] = lengthToAddedVertex;
            remainNodes--;
        }

        return distances;
    }

    private static boolean isEligibleEdge(boolean[] x, Edge edge) {
        return ((!x[edge.getHead()] && x[edge.getTail()])
                || (x[edge.getHead()] && !x[edge.getTail()]));
    }
}
