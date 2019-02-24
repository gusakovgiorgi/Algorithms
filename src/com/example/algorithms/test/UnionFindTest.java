package com.example.algorithms.test;

import com.example.datastructure.UnionFind;
import org.junit.Assert;
import org.junit.Test;

public class UnionFindTest {

    @Test
    public void run() {
        int numOfElemts = 11;
        UnionFind unionFind = new UnionFind(numOfElemts);
        for (int i = 0; i < numOfElemts; i++) {
            Assert.assertEquals(i, unionFind.find(i));
        }

        unionFind.union(0, 6);
        unionFind.union(3, 9);
        unionFind.union(4, 7);
        unionFind.union(8, 7);
        unionFind.union(6, 8);

        Assert.assertEquals(0, unionFind.find(7));
        Assert.assertEquals(0, unionFind.find(8));
        Assert.assertEquals(0, unionFind.find(4));
        Assert.assertEquals(0, unionFind.find(6));
        Assert.assertEquals(0, unionFind.find(0));

        Assert.assertEquals(3, unionFind.find(9));
        Assert.assertEquals(3, unionFind.find(3));

        Assert.assertEquals(1, unionFind.find(1));
        Assert.assertEquals(5, unionFind.find(5));
        Assert.assertEquals(2, unionFind.find(2));

        unionFind.union(5, 1);
        unionFind.union(5, 4);

        Assert.assertEquals(0, unionFind.find(7));
        Assert.assertEquals(0, unionFind.find(8));
        Assert.assertEquals(0, unionFind.find(4));
        Assert.assertEquals(0, unionFind.find(6));
        Assert.assertEquals(0, unionFind.find(0));

        Assert.assertEquals(3, unionFind.find(9));
        Assert.assertEquals(3, unionFind.find(3));

        Assert.assertEquals(0, unionFind.find(1));
        Assert.assertEquals(0, unionFind.find(5));
        Assert.assertEquals(2, unionFind.find(2));
    }
}