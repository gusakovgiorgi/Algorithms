package com.example.algorithms;

import com.example.algorithms.matrix.MatrixMultiplicationStrassens;

import java.util.Formatter;

public class Main {

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2},
                {3, 4}
        };
        int[][] b = new int[][]{
                {5, 6},
                {7, 8}
        };

//        MatrixMultipticationNaive matrixMultipticationNaive=new MatrixMultipticationNaive();
//        matrixMultipticationNaive.multiply(a,b);
//        System.out.println(matrixMultipticationNaive.getPrintableMatrix());


        MatrixMultiplicationStrassens matrixMultiplicationStrassens = new MatrixMultiplicationStrassens();
        int[][] result = matrixMultiplicationStrassens.multiply(a, b);

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        String format = "%-5d";
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                formatter.format(format, result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static double logb(double a, double b) {
        return Math.log(b) / Math.log(a);
    }
}
