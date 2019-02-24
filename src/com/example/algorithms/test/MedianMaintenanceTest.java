package com.example.algorithms.test;

import com.example.algorithms.MedianMaintenance;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MedianMaintenanceTest {

    @Test
    public void testMedians() {
        MedianMaintenance medianMaintenance = new MedianMaintenance();
        medianMaintenance.addElement(1);
        medianMaintenance.addElement(2);
        medianMaintenance.addElement(5);
        medianMaintenance.addElement(8);
        medianMaintenance.addElement(4);
        medianMaintenance.addElement(1);
        medianMaintenance.addElement(3);
        medianMaintenance.addElement(0);
        medianMaintenance.addElement(50);

        Assert.assertEquals(20, medianMaintenance.getSumOfMedians());
    }

    @Test
    public void ExmaTest() {
        String fileName = "/home/qvark/Desktop/median.txt";
        MedianMaintenance medianMaintenance = new MedianMaintenance();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                int newKey = Integer.valueOf(line);
                medianMaintenance.addElement(newKey);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long medianLas4 = medianMaintenance.getSumOfMedians() % 10000;
        System.out.println(medianLas4);
    }

}