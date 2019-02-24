package com.example.algorithms;

import com.example.datastructure.MaxHeap;
import com.example.datastructure.MinHeap;

import java.util.Comparator;

public class MedianMaintenance {
    private long sumOfMedians;
    /**
     * supports extract max
     */
    private MaxHeap<Integer> hLow;
    /**
     * supports extract min
     */
    private MinHeap<Integer> hHigh;

    public MedianMaintenance() {
        sumOfMedians = 0;
        hLow = new MaxHeap<>(Comparator.comparingInt(o -> o));
        hHigh = new MinHeap<>(Comparator.comparingInt(o -> o));
    }

    public void addElement(int x) {
        Integer maxInLows = hLow.peek();
        Integer minInHigths = hHigh.peek();
        if (maxInLows == null) {
            hLow.add(x);
        } else if (minInHigths == null) {
            if (x < maxInLows) {
                hHigh.add(hLow.poll());
                hLow.add(x);
            } else {
                hHigh.add(x);
            }
        } else if (x < maxInLows) {
            if (hLow.size() - hHigh.size() > 0) {
                hHigh.add(hLow.poll());
            }
            hLow.add(x);
        } else if (x > minInHigths) {
            if (hHigh.size() - hLow.size() > 0) {
                hLow.add(hHigh.poll());
            }
            hHigh.add(x);
        } else {
            if (hLow.size() < hHigh.size()) {
                hLow.add(x);
            } else {
                hHigh.add(x);
            }
        }

        // Even number of elements
        if (hHigh.size() == hLow.size()) {
            sumOfMedians += hLow.peek();
        } else {
            if (hLow.size() > hHigh.size()) {
                sumOfMedians += hLow.peek();
            } else {
                sumOfMedians += hHigh.peek();
            }
        }
    }

    public long getSumOfMedians() {
        return sumOfMedians;
    }
}
