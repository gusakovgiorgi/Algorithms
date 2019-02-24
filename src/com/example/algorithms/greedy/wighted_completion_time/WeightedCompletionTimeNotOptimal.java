package com.example.algorithms.greedy.wighted_completion_time;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedCompletionTimeNotOptimal {

    public static long calculateWeightSum(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                int dif1 = o1.weight - o1.length;
                int dif2 = o2.weight - o2.length;
                if (dif1 - dif2 == 0) {
                    return o2.weight - o1.weight;
                }
                return dif2 - dif1;
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
