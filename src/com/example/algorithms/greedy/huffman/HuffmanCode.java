package com.example.algorithms.greedy.huffman;

import java.util.*;

public class HuffmanCode {
    public static long maxLengthOfCodeword(int[] frequencies) {
        SuperNode node = getHuffmanTree(frequencies);
        return node.getDepth();
    }

    private static SuperNode getHuffmanTree(int[] frequencies) {
        if (frequencies.length == 2) return new SuperNode(new Node("0", frequencies[0]),
                new Node("1", frequencies[1]));

        List<Node> nodes = new ArrayList<>(frequencies.length + 10);
        for (int i = 0; i < frequencies.length; i++) {
            nodes.add(new Node(String.valueOf(i), frequencies[i]));
        }
        Collections.sort(nodes, Comparator.comparingLong(Node::getWeight));
        Queue<SuperNode> queue = new LinkedList<>();

        for (int i = 0; i < nodes.size(); i++) {
            Node nodeFromQueue = queue.peek();
            Node currentNode = nodes.get(i);
            if (nodeFromQueue != null) {
                if (currentNode.getWeight() >= nodeFromQueue.getWeight()) {
                    queue.poll();
                    queue.add(new SuperNode(nodeFromQueue, currentNode));
                } else if (i != nodes.size() - 1) {
                    Node nextNode = nodes.get(i + 1);
                    if (nextNode.getWeight() > nodeFromQueue.getWeight()) {
                        queue.poll();
                        queue.add(new SuperNode(nodeFromQueue, currentNode));
                    } else {
                        queue.add(new SuperNode(currentNode, nextNode));
                        i++;
                    }
                } else {
                    queue.poll();
                    queue.add(new SuperNode(nodeFromQueue, currentNode));
                }
            } else {
                Node nexNode = nodes.get(++i);
                queue.add(new SuperNode(currentNode, nexNode));
            }
        }

        while (queue.size() > 1) {
            queue.add(new SuperNode(queue.poll(), queue.poll()));
        }
        return queue.poll();
    }

    public static int minLengthOfCodeword(int[] frequencies) {
        SuperNode root = getHuffmanTree(frequencies);
        System.out.println("Start BFS from " + root.getSymbol());
        int minDepth = Integer.MAX_VALUE;
        Stack<DepthNode> stack = new Stack<>();
        stack.add(new DepthNode(root.getNode1(), root.getNode2(), 0));
        while (!stack.isEmpty()) {
            DepthNode currNode = stack.pop();
            Node leftNode = currNode.getNode1();
            Node rightNode = currNode.getNode2();
            if (leftNode instanceof SuperNode) {
                SuperNode leftSuperNode = (SuperNode) leftNode;
                stack.add(new DepthNode(leftSuperNode.getNode1(), leftSuperNode.getNode2(), currNode.depth + 1));
            } else {
                int currDepth = currNode.depth + 1;
                if (currDepth < minDepth) {
                    minDepth = currDepth;
                }
            }

            if (rightNode instanceof SuperNode) {
                SuperNode rightSUperNode = (SuperNode) rightNode;
                stack.add(new DepthNode(rightSUperNode.getNode1(), ((SuperNode) rightNode).getNode2(), currNode.depth + 1));
            } else {
                int currDepth = currNode.depth + 1;
                if (currDepth < minDepth) {
                    minDepth = currDepth;
                }
            }
        }

        return minDepth;

    }

    static class DepthNode extends SuperNode {
        private int depth;

        public DepthNode(Node node1, Node node2, int depth) {
            super(node1, node2);
            this.depth = depth;
        }

        @Override
        public int getDepth() {
            return depth;
        }

        @Override
        public void setDepth(int depth) {
            this.depth = depth;
        }
    }
}
