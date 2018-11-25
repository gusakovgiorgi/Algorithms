package com.example.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static void searchBFS(AdjacencyListGraph graph, int startVertex) {
        System.out.println("Start BFS from " + startVertex);
        boolean[] explored = new boolean[graph.getN()];
        Queue<Integer> queue = new LinkedList<>();
        explored[startVertex] = true;
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Integer currVert = queue.poll();
            List<Integer> connectedVerticies = graph.getConnectedVerticies(currVert);
            for (Integer connectedVert : connectedVerticies) {
                if (!explored[connectedVert]) {
                    System.out.println("");
                    System.out.println("find vertex " + connectedVert);
                    explored[connectedVert] = true;
                    queue.add(connectedVert);
                }
            }
        }
    }
}
