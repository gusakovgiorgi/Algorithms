package com.example.algorithms.test;

import com.example.algorithms.greedy.huffman.HuffmanCode;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanCodeTest {

    @Test
    public void maxLengthOfCodeword() {

        String fileName = "/home/qvark/Desktop/huffman.txt";
        int[] frequencies = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int symbolsCount = Integer.valueOf(reader.readLine());
            frequencies = new int[symbolsCount];
            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = Integer.valueOf(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int[] frequencies = new int[]{60,25,10,5};
        System.out.println(HuffmanCode
                .maxLengthOfCodeword(frequencies));
        System.out.println(HuffmanCode
                .minLengthOfCodeword(frequencies));
    }
}