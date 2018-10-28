package com.example.algorithms.test;

import com.example.algorithms.closest_pair.ClosestPairAlgorithm;
import com.example.algorithms.closest_pair.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class ClosestPairAlgorithmTest {

    @Test
    public void findClosetPairTest() {
        Random random = new Random(16);
        for (int size = 4; size < 50; size++) {
            for (int i = 0; i < 1000000; i++) {
                Point[] points = new Point[size];
                for (int j = 0; j < size; j++) {
                    points[j] = new Point(random.nextInt() % 10, random.nextInt() % 10);
                }

                Point[] expectedPair = ClosestPairAlgorithm.findClosetPairNaive(points);
                double expectedDistance = ClosestPairAlgorithm.getEuclideanDistance(expectedPair[0], expectedPair[1]);
                Point[] resultPair = ClosestPairAlgorithm.findClosetPairQuick(points);
                double resultDistance = ClosestPairAlgorithm.getEuclideanDistance(resultPair[0], resultPair[1]);
                Assert.assertEquals("On points " + Arrays.toString(points), expectedDistance, resultDistance, 0.000000000000000001);
                System.out.println("test on size " + size + " passed successfully");
            }
        }
    }
}