package com.example.algorithms.selectiction;

import java.util.Random;

public class RandomizedSelection {

    public static int getIthOrderElementInArray(int[] arr, int i) {
        return RSelect(arr, 0, arr.length - 1, i);
    }

    private static int RSelect(int[] arr, int l, int r, int i) {
        int n = r - l + 1;
        if (n <= 1) return arr[l];
        int pivotIndex = getPivotIndex(arr, l, r);
        int pivotIndexInPartitionedArray = partitioningAroundPivot(arr, l, r, pivotIndex);
        if (pivotIndexInPartitionedArray == i) return arr[i];
        if (pivotIndexInPartitionedArray > i) {
            return RSelect(arr, l, pivotIndexInPartitionedArray - 1, i);
        } else {
            return RSelect(arr, pivotIndexInPartitionedArray + 1, r, i);
        }
    }


    /**
     * partition around pivot
     *
     * @return pivot index in partitioned array
     */
    private static int partitioningAroundPivot(int[] arr, int l, int r, int pivotIndex) {
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

    private static int getPivotIndex(int[] arr, int l, int r) {
        Random random = new Random();
        return l + random.nextInt(r - l + 1);
    }

    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
