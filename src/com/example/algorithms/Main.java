package com.example.algorithms;

import com.example.algorithms.sorting.mergeSort.SplitInversionsAlgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        String fileName = "/home/qvark/Desktop/_bcb5c6658381416d19b01bfc1d3993b5_IntegerArray.txt";
        int[] arr = new int[100000];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (int i = 0; i < 100000; i++) {
                arr[i] = Integer.valueOf(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(SplitInversionsAlgorithm.inversionsCountNaive(arr));


//        Point[] points = new Point[]{new Point(2, 1), new Point(-2, 2), new Point(4, 4),
//                new Point(-3, -1), new Point(0, 1),new Point(0,2)
//        };
//
//        Point[] closestPairsNaive = new ClosestPairAlgorithm().findClosetPairNaive(points);
//        System.out.println("[Naive] Closest pairs are : " + closestPairsNaive[0] + " and " + closestPairsNaive[1] +
//                ". Distance = " +
//                ClosestPairAlgorithm.getEuclideanDistance(closestPairsNaive[0], closestPairsNaive[1]));
//
//        Point[] closestPairs = new ClosestPairAlgorithm().findClosetPairQuick(points);
//        System.out.println("[Quick] Closest pairs are : " + closestPairs[0] + " and " + closestPairs[1] +
//                ". Distance = " +
//                ClosestPairAlgorithm.getEuclideanDistance(closestPairs[0], closestPairs[1]));
    }
}
