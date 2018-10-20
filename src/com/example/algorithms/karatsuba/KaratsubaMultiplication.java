package com.example.algorithms.karatsuba;

public class KaratsubaMultiplication {

    public static String multiply(String x, String y) {
        ArithmeticString first = new ArithmeticString(x);
        ArithmeticString second = new ArithmeticString(y);
        String sing = first.isNegative() != second.isNegative() ? "-" : "";

        return sing + multiply(first, second);
    }

    public static String multiply(int x, int y) {
        return multiply(String.valueOf(x), String.valueOf(y));
    }

    private static ArithmeticString multiply(ArithmeticString x, ArithmeticString y) {
        if (x.isSingle() && y.isSingle()) return x.multiplySingleArithmeticStrings(y);
        int xLength = x.length();
        int yLength = y.length();
        ArithmeticString a, b, c, d;

        int n = Math.max(xLength, yLength);

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
        ArithmeticString a_plus_b_c_plus_d = multiply(a.add(b), c.add(d));
        ArithmeticString ad_plus_bc = a_plus_b_c_plus_d.subtract(ac).subtract(bd);

        // 10^n * ac + 10^(n/2) * (ad + bc) + bd
        return ac.tenPowerOf(n).add(ad_plus_bc.tenPowerOf(n / 2)).add(bd);
    }
}
