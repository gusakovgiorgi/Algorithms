package com.example.algorithms.selectiction;

import com.example.algorithms.sorting.mergesort.MergeSortAlgorithm;

import java.math.BigDecimal;

public class DeterministicSelection {

    private static final int CHOOSE_GROUPS_SIZE = 5;

    public static int getIthOrderElementInArray(int[] arr, int i) {
        return DSelect(arr, 0, arr.length - 1, i);
    }

    private static int DSelect(int[] arr, int l, int r, int i) {
        int n = r - l + 1;
        if (n == 1) return arr[l];
        int[] cArr = breakByGroupAndGetMedians(arr, l, r);
        int pivot = DSelect(cArr, 0, cArr.length - 1, n / 10);
        int pivotIndexInPartitionedArray = partitioningAroundPivot(arr, l, r, pivot);
        if (pivotIndexInPartitionedArray == i) return arr[i];
        if (pivotIndexInPartitionedArray > i) {
            return DSelect(arr, l, pivotIndexInPartitionedArray - 1, i);
        } else {
            return DSelect(arr, pivotIndexInPartitionedArray + 1, r, i);
        }
    }

    private static int[] breakByGroupAndGetMedians(int[] arr, int l, int r) {
        int n = r - l + 1;
        int groups = new BigDecimal(Math.ceil((double) n / CHOOSE_GROUPS_SIZE)).intValue();
        int[] groupsArr = new int[groups];
        for (int i = 0; i < groups; i++) {
            int groupSize = (n - (i + 1) * CHOOSE_GROUPS_SIZE) >= 0 ? CHOOSE_GROUPS_SIZE : n % CHOOSE_GROUPS_SIZE;
            int[] group = new int[groupSize];
            System.arraycopy(arr, l + i * CHOOSE_GROUPS_SIZE, group, 0, groupSize);
            groupsArr[i] = MergeSortAlgorithm.sort(group)[groupSize / 2];
        }
        return groupsArr;
    }


    /**
     * partition around pivot
     *
     * @return pivot index in partitioned array
     */
    private static int partitioningAroundPivot(int[] arr, int l, int r, int pivot) {
        int pivotIndex = findPivotIndex(arr, l, r, pivot);
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

    private static int findPivotIndex(int[] arr, int l, int r, int pivot) {
        for (int i = l; i <= r; i++) {
            if (arr[i] == pivot) {
                return i;
            }
        }
        throw new RuntimeException("This line never should be reached");
    }


    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
