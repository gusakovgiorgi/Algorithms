package com.example.algorithms;

import com.example.algorithms.minimum_cut.ramdomized_contruction.MinCutGraph;
import com.example.algorithms.minimum_cut.ramdomized_contruction.RandomContractionAlgorithm;
import com.example.algorithms.minimum_cut.ramdomized_contruction.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "/home/qvark/Desktop/kargerMinCut.txt";
        MinCutGraph graph = new MinCutGraph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                Vertex vertex = new Vertex(Integer.parseInt(tokenizer.nextToken()));
                List<Vertex> neighbours = new ArrayList<>((int) (tokenizer.countTokens() * 1.25));
                while (tokenizer.hasMoreElements()) {
                    Vertex neighbor = new Vertex(Integer.parseInt(tokenizer.nextToken()));
                    neighbours.add(neighbor);
                }
                graph.addVertexWithNeighbours(vertex, neighbours);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int minimumEdges = Integer.MAX_VALUE;
        int numberOfTrials = (int) (graph.getSize() * graph.getSize() * Math.log10(graph.getSize()));
        numberOfTrials = 100;
        System.out.println("Number of trials = " + numberOfTrials);
        for (int i = 0; i < numberOfTrials; i++) {
            System.out.println("Trail number " + i + ", current minimum is " + minimumEdges);
            MinCutGraph graphForTrial = MinCutGraph.deepCopy(graph);
            int currentEdges = RandomContractionAlgorithm.getMinCutEdgesCount(graphForTrial);
            if (currentEdges < minimumEdges) {
                minimumEdges = currentEdges;
            }
        }

        System.out.println("Minimum cut edges = " + minimumEdges);

    }
}
