package com.example.algorithms.dynamic_programming.knapsack;

public class KnapsackProblemAlgoOptimal {

    public static long getOptimalValue(Item[] items, int knapsackSize) {

        long[][] A = new long[2][knapsackSize + 1];
        int firstIndex = 1;
        int secondIntex = 0;
        for (int i = 1; i <= items.length; i++) {
            Item currItem = items[i - 1];
            System.arraycopy(A[secondIntex], 0, A[firstIndex], 0, currItem.getWeight());
            for (int x = currItem.getWeight(); x <= knapsackSize; x++) {
                A[firstIndex][x] = Math.max(A[secondIntex][x], A[secondIntex][x - currItem.getWeight()] + currItem.getValue());
            }
            int tempIndex = firstIndex;
            firstIndex = secondIntex;
            secondIntex = tempIndex;
        }
        return A[secondIntex][knapsackSize];
    }
}
