package com.example.algorithms.graph.clustering

import com.example.algorithms.graph.dijkstra.Edge

class ClusteringGraph(val nodes: Int, edges: List<Edge>) {
    private val edges = edges.sortedWith(compareBy(Edge::getWeight))

    fun getEdge(index: Int) = edges[index]

    fun edgesSize() = edges.size
}