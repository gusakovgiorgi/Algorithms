package com.example.algorithms.dynamic_programming.knapsack;

public class KnapsackProblem {

    public static long getOptimalValue(Item[] items, int knapsackSize) {
        long[][] A = new long[items.length + 1][knapsackSize + 1];
        for (int i = 1; i <= items.length; i++) {
            Item currItem = items[i - 1];
            for (int x = 0; x <= knapsackSize; x++) {
                if (x - currItem.getWeight() >= 0) {
                    A[i][x] = Math.max(A[i - 1][x], A[i - 1][x - currItem.getWeight()] + currItem.getValue());
                } else {
                    A[i][x] = A[i - 1][x];
                }
            }
        }
        return A[items.length][knapsackSize];
    }
}
