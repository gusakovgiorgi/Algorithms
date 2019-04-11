package com.example.algorithms.dynamic_programming;

import com.example.algorithms.dynamic_programming.bellman_ford.BellManFordAlgorithm;
import com.example.algorithms.graph.dijkstra.DijkstraWithDirectedGraph;
import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;

import java.util.Arrays;

public class JohnsonsAlgorithm {

    public static int[][] computeAllShortestPaths(Graph graph) {
        // Add face vertex with zero's weiht
        int fakeVertex = graph.getVerticesSize();
        for (int i = 0; i < graph.getVerticesSize(); i++) {
            graph.addEdge(new Edge(fakeVertex, i, 0));
        }

        int[] pathLenghtFromFakeVert = BellManFordAlgorithm.computeShortestPath(graph, fakeVertex);

        for (int i = 0; i < graph.getEgesSize(); i++) {
            Edge edge = graph.getEdge(i);
            // for each edgee= (u,v)∈G, defin c'e=ce+pu−pv.
            edge.setWeight(edge.getWeight() +
                    pathLenghtFromFakeVert[edge.getTail()] - pathLenghtFromFakeVert[edge.getHead()]);
        }

        //not save fake vertex data
        int[][] allpairs = new int[graph.getVerticesSize() - 1][graph.getVerticesSize() - 1];
        for (int i = 0; i < graph.getVerticesSize() - 1; i++) {
            int[] distances = DijkstraWithDirectedGraph.computePaths(graph, i);
            restoreProperDistances(i, distances, pathLenghtFromFakeVert);
            allpairs[i] = Arrays.copyOf(distances, distances.length - 1);
        }

        return allpairs;
    }

    private static void restoreProperDistances(int startVertex, int[] distances, int[] pathLenghtFromFakeVert) {
        // for each pair (u,v)∈G, return the shortest-path distanced(u,v) :=d′(u,v)−pu+pv
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != Integer.MAX_VALUE) {
                distances[i] = distances[i] - pathLenghtFromFakeVert[startVertex] + pathLenghtFromFakeVert[i];
            }
        }
    }
}
