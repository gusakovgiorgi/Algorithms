package com.example.algorithms.matrix;

public class MatrixMultiplicationStrassens {

    public int[][] multiply(int[][] a, int[][] b) {
        if (!isMatricesSameDimensionsAndQuadratic(a, b)) {
            throw new IllegalArgumentException("Matrices is not quadratic or same dimensions");
        }
        return multiplyRecursive(a, b);
    }

    private int[][] multiplyRecursive(int[][] a, int[][] b) {
        int n = a.length;
        if (n == 1) {
            return new int[][]{{a[0][0] * b[0][0]}};
        }

        int quadrant = n / 2;
        int[][] A = getUpperLeftQuadrant(a, quadrant);
        int[][] B = getUpperRightQuadrant(a, quadrant);
        int[][] C = getBottomLeftQuadrant(a, quadrant);
        int[][] D = getBottomRightQuadrant(a, quadrant);
        int[][] E = getUpperLeftQuadrant(b, quadrant);
        int[][] F = getUpperRightQuadrant(b, quadrant);
        int[][] G = getBottomLeftQuadrant(b, quadrant);
        int[][] H = getBottomRightQuadrant(b, quadrant);

        int[][] P1 = multiplyRecursive(A, subtract(F, H));
        int[][] P2 = multiplyRecursive(add(A, B), H);
        int[][] P3 = multiplyRecursive(add(C, D), E);
        int[][] P4 = multiplyRecursive(D, subtract(G, E));
        int[][] P5 = multiplyRecursive(add(A, D), add(E, H));
        int[][] P6 = multiplyRecursive(subtract(B, D), add(G, H));
        int[][] P7 = multiplyRecursive(subtract(A, C), add(E, F));

        return combine(n, P1, P2, P3, P4, P5, P6, P7);
    }

    private int[][] combine(int length, int[][] p1, int[][] p2, int[][] p3, int[][] p4, int[][] p5, int[][] p6, int[][] p7) {
        int[][] result = new int[length][length];
        int[][] upperLeft = add(subtract(add(p5, p4), p2),p6);
        int[][] upperRight = add(p1, p2);
        int[][] bottomLeft = add(p3, p4);
        int[][] bottomRight = subtract(subtract(add(p1, p5), p3), p7);

        int quadrant = length / 2;

        for (int i = 0; i < quadrant; i++) {
            for (int j = 0; j < quadrant; j++) {
                result[i][j] = upperLeft[i][j];
            }
        }
        for (int i = 0; i < quadrant; i++) {
            for (int j = quadrant; j < length; j++) {
                result[i][j] = upperRight[i][j-quadrant];
            }
        }
        for (int i = quadrant; i < length; i++) {
            for (int j = 0; j < quadrant; j++) {
                result[i][j] = bottomLeft[i-quadrant][j];
            }
        }
        for (int i = quadrant; i < length; i++) {
            for (int j = quadrant; j < length; j++) {
                result[i][j] = bottomRight[i - quadrant][j - quadrant];
            }
        }

        return result;
    }

    private int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private int[][] subtract(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    private int[][] getUpperLeftQuadrant(int[][] a, int quadrant) {
        return getQuadrant(a, quadrant, 0, 0);
    }

    private int[][] getUpperRightQuadrant(int[][] a, int quadrant) {
        return getQuadrant(a,quadrant,0,quadrant);
    }

    private int[][] getBottomLeftQuadrant(int[][] a, int quadrant) {
        return getQuadrant(a, quadrant,quadrant,0);
    }

    private int[][] getBottomRightQuadrant(int[][] a, int quadrant) {
        return getQuadrant(a, quadrant,quadrant,quadrant);
    }

    private int[][] getQuadrant(int[][] a, int length,int rowStart, int columnStart) {
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i][j] = a[i+rowStart][j+columnStart];
            }
        }
        return result;
    }

    private boolean isMatricesSameDimensionsAndQuadratic(int[][] a, int[][] b) {
        int n = a.length;
        if (n < 0 || (n & (n - 1)) != 0 || a[0].length != n || b.length != n || b[0].length != n) return false;
        return true;
    }
}
