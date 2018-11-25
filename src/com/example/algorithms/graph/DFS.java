package com.example.algorithms.graph;

import java.util.List;
import java.util.Stack;

public class DFS {

    private static boolean[] explored;

    public static void searchDFS(AdjacencyListGraph graph, int startVertex) {
        boolean[] explored = new boolean[graph.getN()];
        Stack<Integer> stack = new Stack<>();
        explored[startVertex] = true;
        stack.add(startVertex);
        while (!stack.isEmpty()) {
            Integer currVert = stack.pop();
            List<Integer> connectedVerticies = graph.getConnectedVerticies(currVert);
            for (Integer connectedVert : connectedVerticies) {
                if (!explored[connectedVert]) {
                    System.out.println("");
                    System.out.println("find vertex " + connectedVert);
                    explored[connectedVert] = true;
                    stack.add(connectedVert);
                    break;
                }
            }
        }
    }

    public static void searchDFRecursive(AdjacencyListGraph graph, int startVertex) {

        explored = new boolean[graph.getN()];
        DFSRecursive(graph, startVertex);
    }

    private static void DFSRecursive(AdjacencyListGraph graph, int startVertex) {
        explored[startVertex] = true;
        List<Integer> connectedVerticies = graph.getConnectedVerticies(startVertex);
        for (Integer connectedVert : connectedVerticies) {
            if (!explored[connectedVert]) {
                System.out.println("");
                System.out.println("find vertex " + connectedVert);
                DFSRecursive(graph, connectedVert);
            }
        }
    }
}
