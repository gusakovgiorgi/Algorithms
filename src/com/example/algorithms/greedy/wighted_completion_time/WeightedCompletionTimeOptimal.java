package com.example.algorithms.greedy.wighted_completion_time;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedCompletionTimeOptimal {

    public static long calculateWeightSum(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                double result = Double.valueOf((double) o2.weight / o2.length - (double) o1.weight / o1.length);
                if (result > 0) return 1;
                if (result < 0) return -1;
                return 0;
            }
        });

        long sum = 0;
        long complTime = 0;
        for (Job job : jobs) {
            complTime += job.length;
            sum += job.weight * complTime;
        }
        return sum;
    }

}
