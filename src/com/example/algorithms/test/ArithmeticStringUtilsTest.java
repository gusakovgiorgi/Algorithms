package com.example.algorithms.test;

import com.example.algorithms.karatsuba.ArithmeticString;
import com.example.algorithms.karatsuba.ArithmeticStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;


public class ArithmeticStringUtilsTest {

    @Test
    public void getHalves() {
        ArithmeticString arStr = new ArithmeticString("78345");
        ArithmeticString expectedFirstHalf = new ArithmeticString("7");
        ArithmeticString expectedSecondHalf = new ArithmeticString("8345");

        Assert.assertEquals(expectedFirstHalf, ArithmeticStringUtils.getFirstHalfOf(arStr, 8));
        Assert.assertEquals(expectedSecondHalf, ArithmeticStringUtils.getSecondHalfOf(arStr, 8));
    }

    @Test
    public void multiply() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int expected = i * j;
                ArithmeticString resutl = new ArithmeticString(String.valueOf(i))
                        .multiplySingleArithmeticStrings(new ArithmeticString(String.valueOf(j)));
                int tested = Integer.valueOf(resutl.toString());
                Assert.assertEquals(expected,tested);
            }
        }
    }

    @Test
    public void power(){
        ArithmeticString arStr = new ArithmeticString("2343");
        ArithmeticString expected1 = new ArithmeticString("2343000");
        ArithmeticString expected2 = new ArithmeticString("23430");


        Assert.assertEquals(expected1, arStr.tenPowerOf(3));
        Assert.assertEquals(expected2, arStr.tenPowerOf(1));
        Assert.assertEquals(ArithmeticString.ZERO, ArithmeticString.ZERO.tenPowerOf(3));

    }

    @Test
    public void subtract(){
        Assert.assertEquals(ArithmeticString.ZERO, ArithmeticString.ZERO.subtract(ArithmeticString.ZERO));
        Random random;
        for (int seed = 0; seed<10; seed++) {
            random=new Random(seed);
            for (int j = 0; j < 1000; j++) {
                long first = random.nextInt();
                ArithmeticString firstArStr=new ArithmeticString(String.valueOf(first));
                long second = random.nextInt();
                ArithmeticString secondArStr = new ArithmeticString(String.valueOf(second));
                long result = first - second;
                ArithmeticString expected = new ArithmeticString(String.valueOf(result));
                ArithmeticString tested = firstArStr.subtract(secondArStr);

                Assert.assertEquals("for numbers " + first + " and " + second, expected, tested);
            }
        }
    }

    @Test
    public void add(){
        Assert.assertEquals(ArithmeticString.ZERO, ArithmeticString.ZERO.add(ArithmeticString.ZERO));
        Random random;
        for (int seed = 0; seed<10; seed++) {
            random=new Random(seed);
            for (int j = 0; j < 1000; j++) {
                long first = random.nextInt();
                ArithmeticString firstArStr=new ArithmeticString(String.valueOf(first));
                long second = random.nextInt();
                ArithmeticString secondArStr = new ArithmeticString(String.valueOf(second));
                long result = first + second;
                ArithmeticString expected = new ArithmeticString(String.valueOf(result));
                ArithmeticString tested = firstArStr.add(secondArStr);

                Assert.assertEquals("for numbers " + first + " and " + second, expected, tested);
            }
        }
//        ArithmeticString first = new ArithmeticString("100");
//        ArithmeticString second = new ArithmeticString("-55");
//        ArithmeticString expected = new ArithmeticString("45");
//        Assert.assertTrue(expected.equals(first.add(second)));
    }
}