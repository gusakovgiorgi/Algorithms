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
            return new ArithmeticString(arStr.toString().substring(0, arStr.length() - middle));
        }
    }

    /**
     * return second half of length
     * @param arStr
     * @param evenLength should be even
     * @return
     */
    public static ArithmeticString getSecondHalfOf(ArithmeticString arStr, int evenLength){
        if (evenLength % 2 != 0) throw new IllegalArgumentException("length " + evenLength + " not even");
        int middle = evenLength / 2;
        if (arStr.length()<=middle){
            return arStr;
        }else {
            return new ArithmeticString(arStr.toString().substring(arStr.length()-middle));
        }
    }
}
