package com.example.algorithms.test;

import com.example.algorithms.greedy.wighted_completion_time.Job;
import com.example.algorithms.greedy.wighted_completion_time.WeightedCompletionTimeNotOptimal;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class WeightedCompletionTimeNotOptimalTest {

    @Test
    public void calculateWeightSum() {
        String fileName = "/home/qvark/Desktop/jobs.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int n = Integer.valueOf(reader.readLine());
            Job[] jobs = new Job[n];
            int i = 0;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,");
                int weight = Integer.valueOf(tokenizer.nextToken());
                int length = Integer.valueOf(tokenizer.nextToken());
                jobs[i++] = new Job(weight, length);
            }
            System.out.println(WeightedCompletionTimeNotOptimal.calculateWeightSum(jobs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}