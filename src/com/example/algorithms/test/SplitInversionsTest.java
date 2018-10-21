package com.example.algorithms.test;

import com.example.algorithms.sorting.mergeSort.SplitInversions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class SplitInversionsTest {

    @Test
    public void inversionsCount() {
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            int size = random.nextInt(10000);
            int[] arr = new int[size];
            for (int j = 0; j < arr.length; j++){
                arr[j] = random.nextInt();
            }
            Assert.assertEquals(SplitInversions.inversionsCountNaive(arr),SplitInversions.inversionsCount(arr));
        }
    }
}