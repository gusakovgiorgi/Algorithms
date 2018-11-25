package com.example.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathsBFS {

    private int[] vertexPathNumbers;

    public void findShortestPaths(AdjacencyListGraph graph, int fromVertex) {
        vertexPathNumbers = new int[graph.getN()];
        int dist = 0;
        boolean[] explored = new boolean[graph.getN()];
        Queue<Integer> queue = new LinkedList<>();
        vertexPathNumbers[fromVertex] = dist;
        explored[fromVertex] = true;
        queue.add(fromVertex);
        while (!queue.isEmpty()) {
            Integer currVert = queue.poll();
            dist = vertexPathNumbers[currVert];
            List<Integer> connectedVerticies = graph.getConnectedVerticies(currVert);
            for (Integer connectedVert : connectedVerticies) {
                if (!explored[connectedVert]) {
                    vertexPathNumbers[connectedVert] = dist + 1;
                    explored[connectedVert] = true;
                    queue.add(connectedVert);
                }
            }
        }
    }

    public int[] getVertexPathNumbers() {
        return vertexPathNumbers;
    }
}
