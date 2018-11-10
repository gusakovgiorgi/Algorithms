package com.example.algorithms.test;

import com.example.algorithms.selectiction.DeterministicSelection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class SelectionTest {

    private int numberOfElementsInFile = 10000;

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