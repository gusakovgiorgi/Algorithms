package com.example.datastructure;

import java.util.LinkedList;
import java.util.List;

public class UnionFind {
    int numberOfElements;
    UnionFindNode[] nodes;

    public UnionFind(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        initNodes();
    }

    public int calculateClusters() {
        int numOfClusters = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].parent == i) {
                numOfClusters++;
            }
        }
        return numOfClusters;
    }

    /**
     * @param nodeIndex index of current node
     * @return nodeIndex leader node's rank
     */
    public int find(int nodeIndex) {
        checkArgument(nodeIndex);

        UnionFindNode currentNode = nodes[nodeIndex];
        int currentIndex = nodeIndex;
        List<UnionFindNode> nodesForPathCompression = new LinkedList<>();

        while (currentNode.parent != currentIndex) {
            nodesForPathCompression.add(currentNode);

            currentIndex = currentNode.parent;
            currentNode = nodes[currentIndex];
        }
        compressPathes(nodesForPathCompression, currentIndex);
        return currentIndex;
    }

    private void compressPathes(List<UnionFindNode> nodesForPathCompression, int leaderIndex) {
        nodesForPathCompression.forEach(unionFindNode -> unionFindNode.setParent(leaderIndex));
    }

    /**
     * Union of two nodes if they weren't unioned before
     *
     * @param firstNodeIndex  index of first node
     * @param secondNodeIndex index of second node
     * @return true if nodes have unionde, false otherwise
     */
    public boolean unionIfNeed(int firstNodeIndex, int secondNodeIndex) {
        checkArgument(firstNodeIndex);
        checkArgument(secondNodeIndex);

        int firstLeadersIndex = find(firstNodeIndex);
        int secondLeadersIndex = find(secondNodeIndex);

        if (firstLeadersIndex == secondLeadersIndex) {
            return false;
        } else {
            union(firstNodeIndex, secondNodeIndex);
            return true;
        }
    }

    /**
     * Union of two nodes. If this nodes was unioned before, than throws {@link RuntimeException}
     * /n.To avoid exception run {@link #unionIfNeed(int, int)}
     *
     * @param firstNodeIndex  index of first node
     * @param secondNodeIndex index of second node
     */
    public void union(int firstNodeIndex, int secondNodeIndex) {
        checkArgument(firstNodeIndex);
        checkArgument(secondNodeIndex);

        int firstLeadersIndex = find(firstNodeIndex);
        int secondLeadersIndex = find(secondNodeIndex);

        if (firstLeadersIndex == secondLeadersIndex) {
            throw new RuntimeException("firstNodeIndex and secondNodeIndex have been already" +
                    " unioned");
        }

        UnionFindNode firsLeader = nodes[firstLeadersIndex];
        UnionFindNode secondLeader = nodes[secondLeadersIndex];

        if (firsLeader.rank > secondLeader.rank) {
            secondLeader.parent = firstLeadersIndex;
        } else if (firsLeader.rank < secondLeader.rank) {
            firsLeader.parent = secondLeadersIndex;
        } else {
            firsLeader.rank++;
            secondLeader.parent = firstLeadersIndex;
        }
    }

    private void checkArgument(int nodeIndex) {
        if (nodeIndex < 0 || nodeIndex >= numberOfElements) {
            throw new IllegalArgumentException("incorrect nodeIndex: " + nodeIndex);
        }
    }

    private void initNodes() {
        nodes = new UnionFindNode[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            nodes[i] = new UnionFindNode(i, 0);
        }
    }

    static class UnionFindNode {
        int parent;
        int rank;

        public UnionFindNode(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}

