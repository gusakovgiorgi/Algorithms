package com.example.algorithms.graph.kosaraju;

import java.util.*;

public class KosarajusAlgorithm {

    private Graph graph;
    private int t;
    private int s;
    private Map<Integer, Integer> verticesMap;
    private boolean[] explored;
    private int[] leader;
    private int numberOfComponentsInSCC;
    private List<Integer> numberOfSCCs;

    public KosarajusAlgorithm(Graph graph) {
        this.graph = graph;
        verticesMap = new HashMap<>(graph.getVerticesSize(), 1.01F);
        numberOfSCCs = new LinkedList<>();
    }

    public void searchSCCs() {
        DFSLoop(graph, true);
        DFSLoop(graph, false);
        Arrays.sort(leader);
    }

    private void DFSLoop(Graph g, boolean reversed) {
        explored = new boolean[g.getVerticesSize()];
        leader = new int[g.getVerticesSize()];
        numberOfSCCs.clear();
        t = 0;
        s = -1;
        for (int i = graph.getVerticesSize() - 1; i >= 0; i--) {
            numberOfComponentsInSCC = 0;
            // If second pass (i.e not reversed graph) the use mapping function
            int current = reversed ? i : verticesMap.get(i);
            if (!explored[current]) {
                s = current;
                DFS(graph, current, reversed);
                numberOfSCCs.add(numberOfComponentsInSCC);
            }
        }
    }

    private void DFS(Graph graph, int i, boolean reversed) {
        explored[i] = true;
        leader[i] = s;
        numberOfComponentsInSCC++;
        IncidentEdges incidentEdges = graph.getIncidentEdges(i);
        List<Integer> edgesIndices = reversed ? incidentEdges.getIncomeEdgesIndices() : incidentEdges.getOutcomeEdgesIndices();
        for (int j = 0; j < edgesIndices.size(); j++) {
            Edge edge = graph.getEdge(edgesIndices.get(j));
            int destNode = reversed ? edge.getTail() : edge.getHead();
//            destNode = reversed ? destNode : verticesMap.get(destNode);
            if (!explored[destNode]) {
                DFS(graph, destNode, reversed);
            }
        }
        // if first pass
        if (reversed) verticesMap.put(t++, i);
    }

    public void printLeadersAndMostFive() {
        Collections.sort(numberOfSCCs, (o1, o2) -> o2 - o1);
//        System.out.println(Arrays.toString(leader));

//        if (leader.length < 2) return;
//        List<Integer> numberOfComponents = new ArrayList<>();
//
//        int componentsCount = 0;
//        for (int i = 0; i < leader.length - 1; i++) {
//            componentsCount++;
//            if (leader[i] != leader[i + 1]) {
//                numberOfComponents.add(componentsCount);
//                componentsCount = 0;
//            }
//        }
//        componentsCount++;
//        numberOfComponents.add(componentsCount);
//
//        Collections.sort(numberOfComponents, (o1, o2) -> o2 - o1);
        int printSize = Math.min(5, numberOfSCCs.size());
        for (int i = 0; i < printSize; i++) {
            System.out.print(numberOfSCCs.get(i));
            System.out.print(" ");
        }

        for (int i = printSize; i < 5; i++) {
            System.out.print("0 ");
        }

    }
}
