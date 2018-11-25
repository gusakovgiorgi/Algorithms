package com.example.algorithms;

import com.example.algorithms.graph.kosaraju.Edge;
import com.example.algorithms.graph.kosaraju.Graph;
import com.example.algorithms.graph.kosaraju.KosarajusAlgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String fileName = "/home/qvark/Desktop/SCC.txt";
        Graph graph = new Graph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                int tail = Integer.parseInt(tokenizer.nextToken()) - 1;
                int head = Integer.parseInt(tokenizer.nextToken()) - 1;
                graph.addEdge(new Edge(tail, head));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        KosarajusAlgorithm algorithm = new KosarajusAlgorithm(graph);
        algorithm.searchSCCs();
        algorithm.printLeadersAndMostFive();

    }
}
