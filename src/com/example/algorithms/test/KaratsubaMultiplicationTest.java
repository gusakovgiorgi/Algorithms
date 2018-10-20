package com.example.algorithms.test;

import com.example.algorithms.karatsuba.KaratsubaMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class KaratsubaMultiplicationTest {

    @Test
    public void multiplySmallNumbers() {
        long first;
        long second;
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            first = random.nextInt() % 1073741000;
            second = random.nextInt() % 1073741000;
            long result = first * second;
            String expected = String.valueOf(result);
            Assert.assertTrue("for numbers " + first + " and " + second,
                    expected.equals(KaratsubaMultiplication.multiply(String.valueOf(first), String.valueOf(second))));
        }
    }

    @Test
    public void multiplyBigNumbers() {
        BigInteger first;
        BigInteger second;
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            StringBuilder firstSb = new StringBuilder(120);
            StringBuilder secondSb = new StringBuilder(120);
            for (int j = 0; j < 10; j++) {
                firstSb.append(Math.abs(random.nextInt()));
                secondSb.append(Math.abs(random.nextInt()));
            }
            if (random.nextBoolean()) firstSb.insert(0, '-');
            if (random.nextBoolean()) secondSb.insert(0, '-');
            first = new BigInteger(firstSb.toString());
            second = new BigInteger(secondSb.toString());
            BigInteger expected = first.multiply(second);
            String tested = KaratsubaMultiplication.multiply(firstSb.toString(), secondSb.toString());
            Assert.assertTrue("multiply " + first.toString() + " and " + second.toString(),
                    expected.toString().equals(tested));
        }
    }
}