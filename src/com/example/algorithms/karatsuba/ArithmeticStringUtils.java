package com.example.algorithms.karatsuba;

public class ArithmeticStringUtils {
    /**
     * return first half of lenght
     *
     * @param evenLength should be even
     * @return
     */
    public static ArithmeticString getFirstHalfOf(ArithmeticString arStr, int evenLength) {
        if (evenLength % 2 != 0) throw new IllegalArgumentException("length " + evenLength + " not even");
        int middle = evenLength / 2;
        if (arStr.length() <= middle) {
            return ArithmeticString.ZERO;
        } else {
            int start = 0;
            if (arStr.isNegative()) start = 1;
            return new ArithmeticString(arStr.toString().substring(start, arStr.length() + start - middle));
        }
    }

    /**
     * return second half of length
     *
     * @param arStr
     * @param evenLength should be even
     * @return
     */
    public static ArithmeticString getSecondHalfOf(ArithmeticString arStr, int evenLength) {
        if (evenLength % 2 != 0) throw new IllegalArgumentException("length " + evenLength + " not even");
        int middle = evenLength / 2;
        if (arStr.length() <= middle) {
            return arStr;
        } else {
            int start = 0;
            if (arStr.isNegative()) start = 1;
            return new ArithmeticString(arStr.toString().substring(arStr.length() + start - middle));
        }
    }
}
