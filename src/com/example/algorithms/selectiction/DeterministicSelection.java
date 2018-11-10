package com.example.algorithms.selectiction;

import com.example.algorithms.sorting.mergesort.MergeSortAlgorithm;

import java.math.BigDecimal;
import java.util.Arrays;

public class DeterministicSelection {

    private static final int CHOOSE_GROUPS_SIZE = 5;

    public static int getIthOrderElementInArray(int[] arr, int i) {
        return DSelect(arr, 0, arr.length - 1, i);
    }

    private static int DSelect(int[] arr, int l, int r, int i) {
        int n = r - l + 1;
        if (n <= 1) return arr[l];
        int pivotIndex = getPivotIndex(arr, l, r);
        int pivotIndexInPartitionedArray = partitioningAroundPivot(arr, l, r, pivotIndex);
        if (pivotIndexInPartitionedArray == i) return arr[i];
        if (pivotIndexInPartitionedArray > i) {
            return DSelect(arr, l, pivotIndexInPartitionedArray - 1, i);
        } else {
            return DSelect(arr, pivotIndexInPartitionedArray + 1, r, i);
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
        int median = getMedianOfMedians(arr, l, r);
        for (int i = l; i <= r; i++) {
            if (median == arr[i]) {
                return i;
            }
        }
        throw new RuntimeException("This code shoudn't be reached");
    }

    private static int getMedianOfMedians(int[] arr, int l, int r) {
        int n = r - l + 1;
        // n can be [2,5], because if it is 1, that's mean that on previous call there was one group,
        // but one group is base case
        if (n <= CHOOSE_GROUPS_SIZE) {
            int middleIndex = n % 2 == 0 ? n / 2 - 1 : n / 2;
            return MergeSortAlgorithm.sort(Arrays.copyOfRange(arr, l, r + 1))[middleIndex];
        }
        int groups = new BigDecimal(Math.ceil((double) n / CHOOSE_GROUPS_SIZE)).intValue();
        int[] groupsArr = new int[groups];
        for (int i = 0; i < groups; i++) {
            int groupSize = (n - (i + 1) * CHOOSE_GROUPS_SIZE) >= 0 ? CHOOSE_GROUPS_SIZE : n - i * CHOOSE_GROUPS_SIZE;
            int[] group = new int[groupSize];
            System.arraycopy(arr, l + i * CHOOSE_GROUPS_SIZE, group, 0, groupSize);
            int middleIndex = groupSize % 2 == 0 ? groupSize / 2 - 1 : groupSize / 2;
            if (middleIndex < 0) {
                groupsArr[i] = group[0];
            } else {
                groupsArr[i] = MergeSortAlgorithm.sort(group)[middleIndex];
            }
        }

        return getMedianOfMedians(groupsArr, 0, groupsArr.length - 1);
    }


    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
