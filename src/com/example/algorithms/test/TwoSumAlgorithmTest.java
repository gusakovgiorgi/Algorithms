package com.example.algorithms.test;

import com.example.algorithms.TwoSumAlgorithm;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TwoSumAlgorithmTest {


    @Test
    public void assignment() {
        String fileName = "/home/qvark/Desktop/prob-2sum.txt";
        TwoSumAlgorithm twoSumAlgorithm = new TwoSumAlgorithm(1000003, 1f);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                long number = Long.valueOf(line);
                twoSumAlgorithm.put(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int conditionsTrue = 0;
        for (int t = -10000; t <= 10000; t++) {
            if (twoSumAlgorithm.isTargetSum(t)) {
                conditionsTrue++;
            }
        }

        System.out.println(conditionsTrue);

    }

}