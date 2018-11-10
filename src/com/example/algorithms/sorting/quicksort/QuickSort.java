package com.example.algorithms.sorting.quicksort;

import java.util.Random;

public class QuickSort {
    private int numberOfComparisons;

    public int getNumberOfComparisons() {
        return numberOfComparisons;
    }

    public int[] sort(int[] arr) {
        if (arr == null || arr.length == 1) return arr;
        numberOfComparisons = 0;
        sortRecursive(arr, 0, arr.length - 1);
        return arr;
    }

    private void sortRecursive(int[] arr, int l, int r) {
        int n = r - l + 1;
        if (n <= 1) return;
        int pivotIndex = getPivotIndex(arr, l, r);
        numberOfComparisons += n - 1;
        int pivotIndexInPartitionedArray = partitioningAroundPivot(arr, l, r, pivotIndex);
        sortRecursive(arr, l, pivotIndexInPartitionedArray - 1);
        sortRecursive(arr, pivotIndexInPartitionedArray + 1, r);
    }

    /**
     * partition around pivot
     *
     * @return pivot index in partitioned array
     */
    private int partitioningAroundPivot(int[] arr, int l, int r, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, l, pivotIndex);
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < pivot) {
                swap(arr, j, i);
                i++;
            }
        }
        swap(arr, l, i - 1);
        return i - 1;
    }

    private int getPivotIndex(int[] arr, int l, int r) {
        Random random = new Random();
        return l + random.nextInt(r - l + 1);
    }

    private void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
