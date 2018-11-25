package com.example.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentsBFS {

    public static int getConnectedComponents(AdjacencyListGraph graph) {
        int componentNumbers = 0;
        boolean[] explored = new boolean[graph.getN()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getN(); i++) {
            if (!explored[i]) {
                componentNumbers++;
                explored[i] = true;
                queue.add(i);
                while (!queue.isEmpty()) {
                    Integer currVert = queue.poll();
                    List<Integer> connectedVerticies = graph.getConnectedVerticies(currVert);
                    for (Integer connectedVert : connectedVerticies) {
                        if (!explored[connectedVert]) {
                            explored[connectedVert] = true;
                            queue.add(connectedVert);
                        }
                    }
                }
            }
        }
        return componentNumbers;
    }
}
