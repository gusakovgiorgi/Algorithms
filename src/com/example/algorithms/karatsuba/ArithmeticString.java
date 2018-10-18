package com.example.algorithms.karatsuba;

import java.util.Objects;

public final class ArithmeticString {
    private final String strNumber;

    public static final ArithmeticString ZERO = new ArithmeticString("0");

    public ArithmeticString(String strNum) {
            strNumber = strNum;
    }

    @Override
    public String toString() {
        return strNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArithmeticString that = (ArithmeticString) o;
        return Objects.equals(strNumber, that.strNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strNumber);
    }

    public int length() {
        return strNumber.length();
    }

    public boolean isSingle() {
        return strNumber.length() == 1;
    }


    public ArithmeticString reverseDigits() {
        return new ArithmeticString(new StringBuilder(strNumber).reverse().toString());
    }

    public ArithmeticString multiplySingleArithmeticStrings(ArithmeticString y) {
        int first = Integer.valueOf(this.toString());
        int second = Integer.valueOf(y.toString());
        return new ArithmeticString(String.valueOf(first * second));
    }

    public ArithmeticString tenPowerOf(int power){
        if (this.equals(ZERO)) return ZERO;
        StringBuilder sb = new StringBuilder(strNumber);
        for (int i = 0; i < power; i++) {
            sb.append('0');
        }
        return new ArithmeticString(sb.toString());
    }

    public ArithmeticString add(ArithmeticString y) {
        char[] firstDigits = this.reverseDigits().toString().toCharArray();
        char[] secondDigits = y.reverseDigits().toString().toCharArray();

        // Add numbers to start and then reverseDigits it, because we should add from end
        StringBuilder sb = new StringBuilder(firstDigits.length + secondDigits.length);

        char[] biggest;
        char[] smallest;
        boolean isBiggestNegative, isSmallestNegative;
        if (firstDigits.length >= secondDigits.length) {
            biggest = firstDigits;
            smallest = secondDigits;
        } else {
            biggest = secondDigits;
            smallest = firstDigits;
        }

        int remember = 0;
        for (int i = 0; i < biggest.length; i++) {
            int firstDigit = Character.getNumericValue(biggest[i]);
            int secondDigit = 0;

            if (i >= smallest.length) {
                if (remember == 0) {
                    writeDigitsWithoutArithmeticOperations(biggest, i, sb);
                    break;
                }
            } else {
                secondDigit = Character.getNumericValue(smallest[i]);
            }

            int sumResult = firstDigit + secondDigit + remember;
            if (sumResult < 10) {
                sb.append(sumResult);
                remember = 0;
            } else {
                sb.append(sumResult % 10);
                remember = 1;
            }
        }
        if (remember == 1) sb.append(1);
        return new ArithmeticString(sb.reverse().toString());
    }

    private void writeDigitsWithoutArithmeticOperations(char[] biggest, int start, StringBuilder sb) {
        for (int i = start; i < biggest.length; i++) {
            sb.append(biggest[i]);
        }
    }
}
