package com.example.algorithms.karatsuba;

import java.util.Objects;

public final class ArithmeticString {
    private final String strNumber;
    /**
     * -1 for negative, and +1 for positive
     */
    private final int sign;

    public static final ArithmeticString ZERO = new ArithmeticString("0");

    public ArithmeticString(String strNum) {
        if (strNum.charAt(0) == '-') {
            sign = -1;
            strNumber = strNum.substring(1);
        } else if (strNum.charAt(0) == '0' && strNum.length() == 1) {
            strNumber = strNum;
            sign = 0;
        } else {
            sign = 1;
            strNumber = strNum;
        }
    }

    @Override
    public String toString() {
        return isNegative() ? "-" + strNumber : strNumber;
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

    public ArithmeticString inverse() {
        String number = strNumber;
        if (!isNegative()) number = '-' + number;
        return new ArithmeticString(number);
    }

    public boolean isNegative() {
        return sign < 0;
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

    public ArithmeticString tenPowerOf(int power) {
        if (this.equals(ZERO)) return ZERO;
        StringBuilder sb = new StringBuilder(strNumber);
        for (int i = 0; i < power; i++) {
            sb.append('0');
        }
        return new ArithmeticString(sb.toString());
    }

    public ArithmeticString subtract(ArithmeticString y) {
        return add(y.inverse());
    }

    public ArithmeticString add(ArithmeticString y) {
        char[] firstDigits = this.reverseDigits().toString().toCharArray();
        char[] secondDigits = y.reverseDigits().toString().toCharArray();

        ArithmeticString result;
        if (firstDigits.length > secondDigits.length) {
            result = calculate(firstDigits, secondDigits, this.isNegative(), y.isNegative());
        } else if (firstDigits.length < secondDigits.length) {
            result = calculate(secondDigits, firstDigits, y.isNegative(), this.isNegative());
        } else {
            int compareResult = this.strNumber.compareTo(y.strNumber);
            if (compareResult == 0) {
                if (this.isNegative() != y.isNegative()) {
                    result = ZERO;
                } else {
                    result = calculate(firstDigits, secondDigits, this.isNegative(), y.isNegative());
                }
            } else if (compareResult > 0) {
                result = calculate(firstDigits, secondDigits, this.isNegative(), y.isNegative());
            } else {
                result = calculate(secondDigits, firstDigits, y.isNegative(), this.isNegative());
            }
        }

        return result;
    }

    private ArithmeticString calculate(char[] biggest, char[] smallest, boolean biggestIsNegative, boolean smallestIsNegative) {
        ArithmeticString result;
        if (biggestIsNegative) {
            if (smallestIsNegative) {
                result = new ArithmeticString("-" + invokeOpetarion(biggest, smallest, Operation.ADD));
            } else {
                result = new ArithmeticString("-" + invokeOpetarion(biggest, smallest, Operation.SUBTRACT));
            }
        } else {
            if (smallestIsNegative) {
                result = new ArithmeticString(invokeOpetarion(biggest, smallest, Operation.SUBTRACT).toString());
            } else {
                result = new ArithmeticString(invokeOpetarion(biggest, smallest, Operation.ADD).toString());
            }
        }
        return result;
    }

    private StringBuilder invokeOpetarion(char[] biggest, char[] smallest, Operation operation) {
        // Add numbers to start and then reverseDigits it, because we should add from end
        StringBuilder sb = new StringBuilder(biggest.length + smallest.length);

        int addRemember = 0;
        int minusFromPreviousZero = 0;
        for (int i = 0; i < biggest.length; i++) {

            int firstDigit = Character.getNumericValue(biggest[i]) + minusFromPreviousZero + addRemember;
            // firstDigit was zero and we subtract 1;
            if (firstDigit == -1) {
                {
                    firstDigit = 9;
                    minusFromPreviousZero = -1;
                }
            } else {
                minusFromPreviousZero = 0;
            }

            int secondDigit = 0;

            if (i < smallest.length) {
                secondDigit = Character.getNumericValue(smallest[i]);
            }
//                if (addRemember == 0) {
//                    writeDigitsWithoutArithmeticOperations(biggest, i, sb);
//                    break;
//                }
//            } else {
//                secondDigit = Character.getNumericValue(smallest[i]);
            if (operation == Operation.SUBTRACT) {
                secondDigit = -1 * secondDigit;
            }
//            }

            int sumResult = firstDigit + secondDigit;

            if (sumResult < 0) {
                // 10 + sumresult = 10 - |sumresult|
                sb.append(10 + sumResult);
                addRemember = -1;
            } else if (sumResult < 10) {
                sb.append(sumResult);
                addRemember = 0;
            } else {
                sb.append(sumResult % 10);
                addRemember = 1;
            }
        }
        if (addRemember == 1) sb.append(1);

        int lastCharIndex = sb.length() - 1;
        while (lastCharIndex > 0 && sb.charAt(lastCharIndex) == '0') {
            sb.deleteCharAt(lastCharIndex);
            lastCharIndex--;
        }

        return sb.reverse();
    }

    private int getResultSign(boolean biggestIsNegative, boolean smallestIsNegative) {
        if (biggestIsNegative) {
            return smallestIsNegative ? 1 : -1;
        } else {
            return 1;
        }
    }

    private void writeDigitsWithoutArithmeticOperations(char[] biggest, int start, StringBuilder sb) {
        for (int i = start; i < biggest.length; i++) {
            sb.append(biggest[i]);
        }
    }

    private enum Operation {
        ADD, SUBTRACT
    }
}
