package com.example.algorithms.test;

import com.example.algorithms.graph.dijkstra.DijkstraWithDirectedGraph;
import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraWithDirectedGraphTest {

    @Test
    public void computePaths() {
        String fileName = "/home/qvark/Desktop/dijkstraData.txt";
        Graph graph = new Graph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
                int tail = Integer.parseInt(tokenizer.nextToken()) - 1;
                while (tokenizer.hasMoreTokens()) {
                    int head = Integer.parseInt(tokenizer.nextToken()) - 1;
                    int weight = Integer.parseInt(tokenizer.nextToken());
                    graph.addEdge(new Edge(tail, head, weight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] distances = DijkstraWithDirectedGraph.computePaths(graph, 0);
        System.out.println(Arrays.toString(distances));
//        distances = DijkstraNaive.computePaths(graph, 0);
//        System.out.println(Arrays.toString(distances));
//        System.out.println(distances[6] + "," + distances[36] + "," + distances[58] + "," + distances[81] + ","
//                + distances[98] + "," + distances[114] + "," + distances[132] + "," +
//                distances[164] + "," + distances[187] + "," + distances[196]);
    }
}