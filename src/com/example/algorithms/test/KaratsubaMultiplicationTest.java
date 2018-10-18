package com.example.algorithms.test;

import com.example.algorithms.karatsuba.ArithmeticString;
import com.example.algorithms.karatsuba.KaratsubaMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.*;

public class KaratsubaMultiplicationTest {

    @Test
    public void multiplySmallNumbers(){
        long first;
        long second;
        Random random=new Random();

        for (int i=0;i<10000; i++){
            first=Math.abs(random.nextInt(1073741824));
            second = Math.abs(random.nextInt(1073741824));
            long result = first * second;
            String expected = String.valueOf(result);
            Assert.assertTrue("for numbers "+first+" and "+second,
                    expected.equals(KaratsubaMultiplication.multiply(String.valueOf(first),String.valueOf(second))));
        }
    }

    @Test
    public void multiplyBigNumbers() {
        BigInteger first;
        BigInteger second;
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            StringBuilder firstSb=new StringBuilder(120);
            StringBuilder secondSb= new StringBuilder(120);
            for (int j = 0; j<10; j++){
                firstSb.append(Math.abs(random.nextInt()));
                secondSb.append(Math.abs(random.nextInt()));
            }
            first=new BigInteger(firstSb.toString());
            second=new BigInteger(secondSb.toString());
            BigInteger expected = first.multiply(second);
            String tested = KaratsubaMultiplication.multiply(firstSb.toString(),secondSb.toString());
            Assert.assertTrue("multiply "+first.toString()+" and "+second.toString(),
                    expected.toString().equals(tested));
        }
    }
}