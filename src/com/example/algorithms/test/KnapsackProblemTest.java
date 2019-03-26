package com.example.algorithms.test;

import com.example.algorithms.dynamic_programming.knapsack.Item;
import com.example.algorithms.dynamic_programming.knapsack.KnapsackProblemAlgoOptimal;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class KnapsackProblemTest {

    @Test
    public void getOptimalValue() {
//        Item[] items = new Item[4];
//        items[0]=new Item(3,4);
//        items[1]=new Item(2,3);
//        items[2]=new Item(4,2);
//        items[3]=new Item(4,3);
//
//        System.out.print(KnapsackProblem.getOptimalValue(items,6));

        String fileName = "/home/qvark/Desktop/knapsack_big.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " \t\n\r\f,");
            int knapsackSize = Integer.valueOf(tokenizer.nextToken());
            int n = Integer.valueOf(tokenizer.nextToken());
            Item[] items = new Item[n];
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine(), " \t\n\r\f,");
                items[i] = new Item(Integer.valueOf(tokenizer.nextToken()), Integer.valueOf(tokenizer.nextToken()));
            }

            System.out.print(KnapsackProblemAlgoOptimal.getOptimalValue(items, knapsackSize));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}