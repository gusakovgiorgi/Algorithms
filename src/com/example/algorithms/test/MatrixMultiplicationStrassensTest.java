package com.example.algorithms.test;

import com.example.algorithms.matrix.MatrixMultiplicationStrassens;
import com.example.algorithms.matrix.MatrixMultipticationNaive;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MatrixMultiplicationStrassensTest {

    @Test
    public void multyplyQuadraticMatrices() {
        Random random = new Random(100);
        for (int i = 1; i <= 15; i++) {
            int size = (int) Math.pow(2,i);
            int[][] x = new int[size][size];
            int[][] y = new int[size][size];
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    x[j][k] = random.nextInt() % 1000000;
                    y[j][k] = random.nextInt() % 1000000;
                }
            }
            long naiveTimeStart = System.currentTimeMillis();
            int[][] expected = new MatrixMultipticationNaive().multiply(x, y);
            System.out.println("naive time on size " + size + " equal " + (System.currentTimeMillis() - naiveTimeStart) + " ms");
            long strassensTimeStart = System.currentTimeMillis();
            int[][] tested = new MatrixMultiplicationStrassens().multiply(x, y);
            System.out.println("strassens time on size " + size + " equal " + (System.currentTimeMillis() - strassensTimeStart) + " ms");
            System.out.println("-----------------------------------------------------------------------------");

            // Compare by rows
            for (int k = 0; k < size; k++) {
                Assert.assertArrayEquals(expected[k], tested[k]);
            }

        }

    }

}