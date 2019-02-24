package com.example.algorithms.test;

import com.example.algorithms.dynamic_programming.MaxWeightIndependentSetAlgo;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MaxWeightIndependentSetAlgoTest {

    @Test
    public void getVerticesFromIndependentSet() {

        String fileName = "/home/qvark/Desktop/mwis.txt";
        int[] weights = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int symbolsCount = Integer.valueOf(reader.readLine());
            weights = new int[symbolsCount];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Integer.valueOf(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int[] weights = new int[]{1, 4, 5, 4};

        List<Integer> verticiesInSet = MaxWeightIndependentSetAlgo.getVerticesFromIndependentSet(weights);
        for (Integer vertex : verticiesInSet) {
            System.out.print(vertex + ", ");
        }
    }
}