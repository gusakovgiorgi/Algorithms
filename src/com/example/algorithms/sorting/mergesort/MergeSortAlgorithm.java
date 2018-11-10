package com.example.algorithms.sorting.mergesort;

import java.lang.reflect.Array;
import java.util.Comparator;

public class MergeSortAlgorithm {

    public static int[] sort(int[] arr) {
        if (arr == null) throw new NullPointerException("array is null");
        if (arr.length == 0 || arr.length == 1) return arr;
        return sort(arr, 0, arr.length);
    }

    public static <T> T[] sort(T[] arr, Comparator<T> comparator) {
        if (arr == null) throw new NullPointerException("array is null");
        if (arr.length == 0 || arr.length == 1) return arr;
        return sort(arr, 0, arr.length, comparator);
    }

    private static <T> T[] sort(T[] arr, int start, int end, Comparator<T> comparator) {
        // If we have one element return it
        int length = end - start;
        if (length == 1) {
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Array.newInstance(arr.getClass().getComponentType(), length);
            result[0] = arr[start];
            return result;
        }

        int middle = length / 2;
        T[] leftArr = sort(arr, start, start + middle, comparator);
        T[] rightArr = sort(arr, start + middle, end, comparator);
        return merge(leftArr, rightArr, comparator);
    }

    private static <T> T[] merge(T[] leftArr, T[] rightArr, Comparator<T> comparator) {
        @SuppressWarnings("unchecked")
        T[] mergedArr = (T[]) Array.newInstance(leftArr.getClass().getComponentType(), leftArr.length + rightArr.length);
        int i = 0;
        int j = 0;
        for (int k = 0; k < mergedArr.length; k++) {
            if (comparator.compare(leftArr[i], rightArr[j]) < 0) {
                mergedArr[k] = leftArr[i];
                i++;
                if (i == leftArr.length) {
                    System.arraycopy(rightArr, j, mergedArr, k + 1, rightArr.length - j);
                    break;
                }
            } else {
                mergedArr[k] = rightArr[j];
                j++;
                if (j == rightArr.length) {
                    System.arraycopy(leftArr, i, mergedArr, k + 1, leftArr.length - i);
                    break;
                }
            }
        }
        return mergedArr;
    }

    private static int[] sort(int[] arr, int start, int end) {
        // If we have one element return it
        int length = end - start;
        if (length == 1) {
            return new int[]{arr[start]};
        }

        int middle = length / 2;
        int[] leftArr = sort(arr, start, start + middle);
        int[] rightArr = sort(arr, start + middle, end);

        return merge(leftArr, rightArr);
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] mergedArr = new int[leftArr.length + rightArr.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < mergedArr.length; k++) {
            if (leftArr[i] < rightArr[j]) {
                mergedArr[k] = leftArr[i];
                i++;
                if (i == leftArr.length) {
                    System.arraycopy(rightArr, j, mergedArr, k + 1, rightArr.length - j);
                    break;
                }
            } else {
                mergedArr[k] = rightArr[j];
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
