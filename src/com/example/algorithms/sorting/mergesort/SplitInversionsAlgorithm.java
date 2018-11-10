package com.example.algorithms.sorting.mergesort;

/**
 * Number of inversions O(n*log(n))
 */
public class SplitInversionsAlgorithm {
    long inversionsCount = 0;

    /**
     * naive algorithm with O(n^2) running time
     */
    public static long inversionsCountNaive(int[] arr) {
        SplitInversionsAlgorithm splitInversions = new SplitInversionsAlgorithm();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    splitInversions.inversionsCount++;
                }
            }
        }
        return splitInversions.inversionsCount;
    }

    public static long inversionsCount(int[] arr) {
        if (arr == null) throw new NullPointerException("array is null");
        if (arr.length == 0 || arr.length == 1) return 0;
        SplitInversionsAlgorithm splitInversions = new SplitInversionsAlgorithm();
        splitInversions.sortAndCount(arr, 0, arr.length);
        return splitInversions.getInversionsCount();
    }

    public long getInversionsCount() {
        return inversionsCount;
    }

    private int[] sortAndCount(int[] arr, int start, int end) {
        // If we have one element return it
        int length = end - start;
        if (length == 1) {
            return new int[]{arr[start]};
        }

        int middle = length / 2;
        int[] leftArr = sortAndCount(arr, start, start + middle);
        int[] rightArr = sortAndCount(arr, start + middle, end);

        return mergeAndCount(leftArr, rightArr);
    }

    private int[] mergeAndCount(int[] leftArr, int[] rightArr) {
        int[] mergedArr = new int[leftArr.length + rightArr.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < mergedArr.length; k++) {
            if (leftArr[i] <= rightArr[j]) {
                mergedArr[k] = leftArr[i];
                i++;
                if (i == leftArr.length) {
                    System.arraycopy(rightArr, j, mergedArr, k + 1, rightArr.length - j);
                    break;
                }
            } else {
                mergedArr[k] = rightArr[j];
                inversionsCount += leftArr.length - i;
                j++;
                if (j == rightArr.length) {
                    System.arraycopy(leftArr, i, mergedArr, k + 1, leftArr.length - i);
                    break;
                }
            }
        }
        return mergedArr;
    }
}
