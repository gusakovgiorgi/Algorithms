package com.example.algorithms.test;

import com.example.datastructure.MinHeap;
import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {

    @Test
    public void heapTest() {
        MinHeap<Integer> integerMinHeap = new MinHeap<>(Integer::compareTo);
        integerMinHeap.add(10);
        integerMinHeap.add(20);
        integerMinHeap.add(30);
        integerMinHeap.add(5);

        Assert.assertEquals(integerMinHeap.peek().intValue(), 5);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 5);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 10);

        integerMinHeap.add(100);
        integerMinHeap.add(10);

        Assert.assertTrue(integerMinHeap.contains(20) != null);
        Assert.assertTrue(integerMinHeap.contains(15) == null);

        integerMinHeap.add(45);
        integerMinHeap.add(55);

        integerMinHeap.remove(100);
        Assert.assertTrue(integerMinHeap.contains(100) == null);

        Assert.assertEquals(integerMinHeap.poll().intValue(), 10);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 20);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 30);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 45);
        Assert.assertEquals(integerMinHeap.poll().intValue(), 55);

    }

}