package com.example.algorithms.test

import com.example.algorithms.graph.clustering.ClusteringAlgo
import com.example.algorithms.graph.clustering.ClusteringGraph
import com.example.algorithms.graph.dijkstra.Edge
import org.junit.Test
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*

class ClusteringAlgoTest2 {

    @Test
    fun calculateMinimumSpacing() {
        val fileName = "/home/qvark/Desktop/clustering1.txt"
        val edgesList = mutableListOf<Edge>()
        try {
            val reader = BufferedReader(FileReader(fileName))
            var line: String?
            val numerOfNodes = reader.readLine().toInt()
            line = reader.readLine()
            while (line != null) {
                val tokenizer = StringTokenizer(line, " \t\n\r,")

                val head = tokenizer.nextToken().toInt() - 1
                val tail = tokenizer.nextToken().toInt() - 1
                val weight = tokenizer.nextToken().toInt()

                edgesList.add(Edge(tail, head, weight))
                line = reader.readLine()
            }

            val graph = ClusteringGraph(numerOfNodes, edgesList)

            val clusteringAlgo = ClusteringAlgo()
            System.out.println(clusteringAlgo.calculateMinimumSpacing(4, graph))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}