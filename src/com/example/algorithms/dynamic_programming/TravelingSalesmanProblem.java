package com.example.algorithms.dynamic_programming;


import java.util.*;

public class TravelingSalesmanProblem {

    private Map<Set<Integer>, Double[]> aPrevious;
    private Map<Set<Integer>, Double[]> aCurrent;
    private double[][] adjMatr;
    private Set<Integer> lastSet;
    private int vertSize;

    public double solve(double[][] adjacencyMatrix) {
        this.adjMatr = adjacencyMatrix;
        vertSize = adjMatr.length;
        aPrevious = new HashMap<>();
        aCurrent = new HashMap<>();
        // Base case
        Double[] vertValues = new Double[vertSize];
        vertValues[0] = 0D;
        Set<Integer> S = new HashSet<>();
        S.add(0);
        aPrevious.put(S, vertValues);

        for (int m = 2; m <= vertSize; m++) {
            forEachSet(vertSize, m);
            System.out.println("try solving subproblem of size " + m);
            aPrevious = aCurrent;
            aCurrent = new HashMap<>(m, 1.01f);
        }
        return minimumResult();
    }

    private double minimumResult() {
        double min = Double.MAX_VALUE;
        Double[] vertArrValues = aPrevious.get(lastSet);
        for (int j = 1; j < vertArrValues.length; j++) {
            double value = vertArrValues[j];
            value += adjMatr[0][j];
            if (min > value) {
                min = value;
            }
        }
        return min;
    }

    /* arr[] ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index ---> Current index in data[]
    r ---> Size of a combination to be printed */
    private void combinationUtil(int[] data, int start,
                                 int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            Set<Integer> set = new HashSet<>(r, 1.01f);
            for (int j = 0; j < r; j++) {
//                System.out.print(data[j] + " ");
                set.add(data[j]);
            }
            Iterator<Integer> iterator = set.iterator();
            Double[] vertArr = new Double[vertSize];
            while (iterator.hasNext()) {
                int j = iterator.next();
                if (j == 0) continue;
                vertArr[j] = minimun(j, set);
            }
            aCurrent.put(set, vertArr);
//            System.out.println();
            if (set.size() == vertSize) {
                lastSet = set;
            }
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            if (index == 0 && i > 0) return;
            data[index] = i;
            combinationUtil(data, i + 1, end, index + 1, r);
        }
    }

    private Double minimun(int j, Set<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        Set<Integer> previousSet = new HashSet<>(set);
        previousSet.remove(j);
        double min = Double.MAX_VALUE;
        while (iterator.hasNext()) {
            int k = iterator.next();
            if (k == j) continue;
            Double previousValue = aPrevious.get(previousSet)[k];
            if (previousValue != null) {
                previousValue += adjMatr[k][j];
                if (min > previousValue) {
                    min = previousValue;
                }
            }
        }
        return min;
    }


    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    private void forEachSet(int n, int r) {
        // aPrevious temporary arr to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(data, 0, n - 1, 0, r);
    }
}
