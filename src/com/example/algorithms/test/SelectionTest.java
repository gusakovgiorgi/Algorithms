package com.example.algorithms.test;

import com.example.algorithms.selectiction.DeterministicSelection;
import com.example.algorithms.selectiction.RandomizedSelection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class SelectionTest {

    private int numberOfElementsInFile = 10000;

    @Test
    public void getIthOrderRandomTest() {
        int arrSize = 20;
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int[] arr = new int[arrSize];
            for (int j = 0; j < arrSize; j++) {
                arr[j] = random.nextInt(10);
            }
            System.out.println("Arr is " + Arrays.toString(arr));
            int[] sortedArr = new int[arrSize];
            System.arraycopy(arr, 0, sortedArr, 0, arr.length);
            Arrays.sort(sortedArr);

            for (int k = 0; k < arrSize; k++) {
                int[] arrToFind = new int[arrSize];
                System.arraycopy(arr, 0, arrToFind, 0, arr.length);
//                System.out.println("find order statistic number " + k);
                Assert.assertEquals(sortedArr[k],
                        RandomizedSelection.getIthOrderElementInArray(arrToFind, k));
                Assert.assertEquals(sortedArr[k],
                        DeterministicSelection.getIthOrderElementInArray(arrToFind, k));
            }
        }
    }

    @Test
    public void getIthOrderInArray() {

        String fileNamePath = new File("src/com/example/algorithms/test/quicksort.txt")
                .getAbsolutePath();
        int[] arr = new int[numberOfElementsInFile];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileNamePath));
            for (int i = 0; i < 10000; i++) {
                arr[i] = Integer.valueOf(reader.readLine());
            }

            for (int i = 0; i < numberOfElementsInFile; i++) {
//                int ithNumberInOrderedArrRandomizedAlg = RandomizedSelection.getIthOrderElementInArray(arr, i);
                int ithNumberInOrderedArrDeterministicAlg = DeterministicSelection.getIthOrderElementInArray(arr, i);
//                Assert.assertEquals("randomized check",i + 1, ithNumberInOrderedArrRandomizedAlg);
                Assert.assertEquals("deterministic check", i + 1, ithNumberInOrderedArrDeterministicAlg);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}