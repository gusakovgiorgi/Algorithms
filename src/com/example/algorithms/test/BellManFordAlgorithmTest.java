package com.example.algorithms.test;

import com.example.algorithms.dynamic_programming.bellman_ford.BellManFordAlgorithm;
import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BellManFordAlgorithmTest {

//    @Test
//    public void graphTest() {
//        Graph graph = new Graph();
//        graph.addEdge(new Edge(0, 2));
//        graph.addEdge(new Edge(2, 3));
//        graph.addEdge(new Edge(3, 0));
//        graph.addEdge(new Edge(3, 1));
//        graph.addEdge(new Edge(2, 3));
//        System.out.println("stop");
//    }

    @Test
    public void computeShortestPath() {
        String fileName = "/Users/giorgi/Desktop/test.txt";
        Graph graph = new Graph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
//                int tail = Integer.parseInt(tokenizer.nextToken()) - 1;
//                int head = Integer.parseInt(tokenizer.nextToken()) - 1;
                int tail = Integer.parseInt(tokenizer.nextToken());
                int head = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());
                graph.addEdge(new Edge(tail, head, weight));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add face vertex with zero's
        int fakeVertex= graph.getVerticesSize();
        for (int i=0;i<graph.getVerticesSize();i++){
            graph.addEdge(new Edge(fakeVertex,i,0));
        }


        int[] distances = BellManFordAlgorithm.computeShortestPath(graph, fakeVertex);
        System.out.println(Arrays.toString(distances));
    }
}