package com.example.algorithms.greedy.wighted_completion_time;

public class Job {
    int weight;
    int length;

    public Job(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Job{" +
                "weight=" + weight +
                ", length=" + length +
                '}';
    }
}
