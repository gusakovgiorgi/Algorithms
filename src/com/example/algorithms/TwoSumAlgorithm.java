package com.example.algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TwoSumAlgorithm {
    private Set<Long> hashSet;

    public TwoSumAlgorithm(int numberOfElements, float loadFactor) {
        hashSet = new HashSet<>(numberOfElements, loadFactor);
    }

    public TwoSumAlgorithm() {
        hashSet = new HashSet<>();
    }

    public void put(long number) {
        hashSet.add(number);
    }

    public boolean isTargetSum(long sum) {
        Iterator<Long> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            long current = iterator.next();
            long find = sum - current;
            if (hashSet.contains(find)) {
                return true;
            }
        }
        return false;
    }

}
