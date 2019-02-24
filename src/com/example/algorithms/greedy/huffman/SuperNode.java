package com.example.algorithms.greedy.huffman;

public class SuperNode extends Node {
    private int depth;
    private Node node1;
    private Node node2;

    public SuperNode(Node node1, Node node2) {
        super(concatSymbols(node1, node2), node1.getWeight() + node2.getWeight());
        this.node1 = node1;
        this.node2 = node2;

        depth = 1;
        int node1Depth = 0;
        int node2Depth = 0;
        if (node1 instanceof SuperNode) {
            node1Depth += ((SuperNode) node1).depth;
        }

        if (node2 instanceof SuperNode) {
            node2Depth += ((SuperNode) node2).depth;
        }

        depth += Math.max(node1Depth, node2Depth);
    }

    private static String concatSymbols(Node node1, Node node2) {
        return node1.getSymbol() + node2.getSymbol();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    @Override
    public String toString() {
        return "SuperNode{" +
                "depth=" + depth +
                "} " + super.toString();
    }
}
