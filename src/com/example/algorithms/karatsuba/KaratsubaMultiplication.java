package com.example.algorithms.karatsuba;

public class KaratsubaMultiplication {

    public static String multiply(String x, String y){
        return multiply(new ArithmeticString(x),new ArithmeticString(y)).toString();
    }

    public static String multiply(int x, int y){
        return multiply(String.valueOf(x),String.valueOf(y));
    }
    private static ArithmeticString multiply(ArithmeticString x, ArithmeticString y) {
        if (x.isSingle() && y.isSingle()) return x.multiplySingleArithmeticStrings(y);
        int xLength = x.length();
        int yLength = y.length();
        ArithmeticString a, b, c, d;

        int n = xLength > yLength ? xLength : yLength;

        // n should be even
        if (n % 2 != 0) {
            n++;
        }

        int middle = n / 2;

        a = ArithmeticStringUtils.getFirstHalfOf(x, n);
        b = ArithmeticStringUtils.getSecondHalfOf(x, n);
        c = ArithmeticStringUtils.getFirstHalfOf(y, n);
        d = ArithmeticStringUtils.getSecondHalfOf(y, n);


        ArithmeticString ac = multiply(a, c);
        ArithmeticString bd = multiply(b, d);
        ArithmeticString ad = multiply(a, d);
        ArithmeticString bc = multiply(b, c);


        return ac.tenPowerOf(n).add(ad.add(bc).tenPowerOf(n / 2)).add(bd);
    }
}
