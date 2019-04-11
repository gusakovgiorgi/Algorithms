package com.example.algorithms.test;

import com.example.algorithms.dynamic_programming.TravelingSalesmanProblem;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class TravelingSalesmanProblemTest {
/* test1.txt
13
        20833.3333 17100.0000
        20900.0000 17066.6667
        21600.0000 16500.0000
        23700.0000 15933.3333
        23616.6667 15866.6667
        21600.0000 14966.6667
        22583.3333 14300.0000
        21600.0000 14150.0000
        21300.0000 13016.6667
        22183.3333 13133.3333
        22683.3333 12716.6667
        23883.3333 14533.3333
        24166.6667 13250.0000*/

    //test2.txt
// 12
//        26150.0000 10550.0000
//        26283.3333 12766.6667
//        26433.3333 13433.3333
//        26550.0000 13850.0000
//        26733.3333 11683.3333
//        27026.1111 13051.9444
//        27096.1111 13415.8333
//        27153.6111 13203.3333
//        27166.6667 9833.3333
//        27233.3333 10450.0000
//        26133.3333 14500.0000
//        25149.1667 12365.8333
    @Test
    public void allChoicesOfS() {
        //https://www.desmos.com/calculator/tm7gsmtmpv
        String fileName1 = "/home/qvark/Desktop/test1.txt";
        String fileName2 = "/home/qvark/Desktop/test2.txt";
        double[][] adjMatr1 = null;
        double[][] adjMatr2 = null;
        Point[] points1 = null;
        Point[] points2 = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName1));
            String line;
            int size = Integer.valueOf(reader.readLine());
            points1 = new Point[size];
            int index = 0;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
                float x = Float.parseFloat(tokenizer.nextToken());
                float y = Float.parseFloat(tokenizer.nextToken());
                points1[index] = new Point(index, x, y);
                index++;
            }
            reader.close();

            index = 0;
            reader = new BufferedReader(new FileReader(fileName2));
            size = Integer.valueOf(reader.readLine());
            points2 = new Point[size];
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
                float x = Float.parseFloat(tokenizer.nextToken());
                float y = Float.parseFloat(tokenizer.nextToken());
                points2[index] = new Point(index, x, y);
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adjMatr1 = new double[points1.length][points1.length];
        for (int i = 0; i < points1.length; i++) {
            for (int j = i + 1; j < points1.length; j++) {
                Point first = points1[i];
                Point second = points1[j];
                double weight = Math.sqrt(Math.pow((first.x - second.x), 2) + Math.pow((first.y - second.y), 2));
                adjMatr1[i][j] = weight;
                adjMatr1[j][i] = weight;
            }
        }
        adjMatr2 = new double[points2.length][points2.length];
        for (int i = 0; i < points2.length; i++) {
            for (int j = i + 1; j < points2.length; j++) {
                Point first = points2[i];
                Point second = points2[j];
                double weight = Math.sqrt(Math.pow((first.x - second.x), 2) + Math.pow((first.y - second.y), 2));
                adjMatr2[i][j] = weight;
                adjMatr2[j][i] = weight;
            }
        }

        Point fromFirst = points1[points1.length - 1];
        Point fromSecond = points2[points2.length - 1];
        double distanceBetweenTwoGroups = Math.sqrt(Math.pow((fromFirst.x - fromSecond.x), 2) +
                Math.pow((fromFirst.y - fromSecond.y), 2));
        System.out.println("distance between two groups is " + distanceBetweenTwoGroups);
        fromFirst = points2[points2.length - 2];
        fromSecond = points1[points1.length - 2];
        double distanceBetweenTwoGroups2 = Math.sqrt(Math.pow((fromFirst.x - fromSecond.x), 2) +
                Math.pow((fromFirst.y - fromSecond.y), 2));
        System.out.println("distance between two groups is " + distanceBetweenTwoGroups2);

        AtomicReference<Double> firstGroupShortestDistance = new AtomicReference<>(0D);
        AtomicReference<Double> secondGroupShortestDistance = new AtomicReference<>(0D);
        double[][] finalAdjMatr = adjMatr1;
        double[][] finalAdjMatr2 = adjMatr2;
        Thread first = new Thread(() -> {
            TravelingSalesmanProblem problem = new TravelingSalesmanProblem();
            double shortest = problem.solve(finalAdjMatr);
            System.out.println("soleve first part: " + shortest);
            firstGroupShortestDistance.set(shortest);
        });
        Thread second = new Thread(() -> {
            TravelingSalesmanProblem problem = new TravelingSalesmanProblem();
            double shortest = problem.solve(finalAdjMatr2);
            System.out.println("soleve second part: " + shortest);
            secondGroupShortestDistance.set(shortest);
        });

        first.start();
        second.start();
        try {
            first.join();
            second.join();
            System.out.println("shortest distance is " + (firstGroupShortestDistance.get() + secondGroupShortestDistance.get() +
                    distanceBetweenTwoGroups + distanceBetweenTwoGroups2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Point {
        int index;
        float x;
        float y;

        public Point(int index, float x, float y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }
}