package com.example.algorithms.test;

import com.example.algorithms.graph.PrimsWithHeap;
import com.example.algorithms.graph.dijkstra.Edge;
import com.example.algorithms.graph.dijkstra.UndirectedGraph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PrimsWithHeapTest {

    @Test
    public void computePaths() {
        String fileName = "/home/qvark/Desktop/prims.txt";
        UndirectedGraph graph = new UndirectedGraph();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            //Miss first line
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

        long cost = PrimsWithHeap.computePaths(graph, 0);
        System.out.println(cost);
//        Assert.assertEquals(15,cost);
    }
}