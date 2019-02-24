package com.example.algorithms.greedy.huffman;

public class Node {
    private String symbol;
    private long weight;

    public Node(String symbol, long weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "symbol='" + symbol + '\'' +
                ", weight=" + weight +
                '}';
    }
}