package com.example.algorithms.graph.clustering

import com.example.datastructure.UnionFind

class ClusteringAlgo {

    fun calculateMinimumSpacing(k: Int, graph: ClusteringGraph): Long {
        var clusters = graph.nodes
        val unionFind = UnionFind(graph.nodes)
        var startIndex = 0;
        while (clusters != k) {
            val edge = graph.getEdge(startIndex)
            if (unionFind.unionIfNeed(edge.head, edge.tail)) {
                clusters--;
            }
            startIndex++
        }
        for (i in startIndex..graph.edgesSize()) {
            val edge = graph.getEdge(i)
            if (unionFind.find(edge.tail) != unionFind.find(edge.head)) {
                return edge.weight.toLong()
            }
        }
        return -1
    }
}