package com.example.algorithms.matrix;

import java.util.Formatter;

public class MatrixMultipticationNaive {

    private int[][] result;
    private int maxNumberSize;

    public int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) throw new IllegalArgumentException("matrix a rows (" + a[0].length + ") " +
                "doesn't match b matrix columns (" + b.length + ")");

        result = new int[a.length][b[0].length];

        int maxNumber=Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                // Get max number for pretty print purpose
                if (result[i][j]>maxNumber){
                    maxNumber = result[i][j];
                }
            }
        }

        maxNumberSize=String.valueOf(maxNumber).length();

        return result;
    }

    public String getPrintableMatrix() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        String format = "%-"+(maxNumberSize+2)+"d";
        for (int i = 0; i<result.length;i++){
            for (int j=0;j<result[i].length;j++){
                formatter.format(format,result[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
