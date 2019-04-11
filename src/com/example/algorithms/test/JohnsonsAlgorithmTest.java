package com.example.algorithms.test;

import com.example.algorithms.dynamic_programming.JohnsonsAlgorithm;
import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class JohnsonsAlgorithmTest {

    @Test
    public void computeAllShortestPaths() {
        String fileName = "/home/qvark/Desktop/large.txt";
        Graph graph = new Graph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            // missing first line with vert nuber and edge number
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
                int tail = Integer.parseInt(tokenizer.nextToken()) - 1;
                int head = Integer.parseInt(tokenizer.nextToken()) - 1;
                int weight = Integer.parseInt(tokenizer.nextToken());
                graph.addEdge(new Edge(tail, head, weight));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] distances = JohnsonsAlgorithm.computeAllShortestPaths(graph);
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            System.out.print("distances from vert " + i + " = ");
            for (int j = 0; j < distances[i].length; j++) {
                if (distances[i][j] < minimum) {
                    minimum = distances[i][j];
                }
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("The smallest distance is " + minimum);
    }
}