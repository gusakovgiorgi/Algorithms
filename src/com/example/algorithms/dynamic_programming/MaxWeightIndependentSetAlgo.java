package com.example.algorithms.dynamic_programming;

import java.util.LinkedList;
import java.util.List;

public class MaxWeightIndependentSetAlgo {

    public static List<Integer> getVerticesFromIndependentSet(int[] weights) {
        long[] A = new long[weights.length + 1];
        A[0] = 0;
        A[1] = weights[0];
        for (int i = 2; i < A.length; i++) {
            A[i] = Math.max(A[i - 1], A[i - 2] + weights[i - 1]);
        }

        return reconstruct(A, weights);
    }

    private static List<Integer> reconstruct(long[] A, int[] weights) {
        List<Integer> indSet = new LinkedList<>();
        int i = A.length - 1;
        while (i >= 1) {
            if (i == 1) {
                indSet.add(i);
                break;
            }
            if (A[i - 1] >= A[i - 2] + weights[i - 1]) {
                i--;
            } else {
                indSet.add(i);
                i -= 2;
            }
        }
        return indSet;
    }
}
