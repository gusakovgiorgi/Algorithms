package com.example.algorithms.graph.minimum_cut.ramdomized_contruction;

import java.util.Random;

public class RandomContractionAlgorithm {

    public static int getMinCutEdgesCount(MinCutGraph graph) {
        while (graph.getSize() > 2) {
            Vertex[] vertex = getRandomEdge(graph);
            // Find Vertex with no connection, means min-cut edges is 0
            if (vertex == null) {
                return 0;
            }
            graph.contraction(vertex[0], vertex[1]);
        }
        return graph.getAllVertices().get(0).getConnectedVertices().size();
    }

    /**
     * @param graph
     * @return two connected vertex, or null if find vertex which is not connected anyone
     */
    private static Vertex[] getRandomEdge(MinCutGraph graph) {
        Random random = new Random();
        int size = graph.getSize();
        int index = random.nextInt(size);
        Vertex first = graph.getAllVertices().get(index);
        if (first.getConnectedVertices().isEmpty()) {
            return null;
        }
        int secondIndex = random.nextInt(first.getConnectedVertices().size());
        Vertex second = first.getConnectedVertices().get(secondIndex);
        return new Vertex[]{first, second};
    }

}
