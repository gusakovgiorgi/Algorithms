package com.example.datastructure;

import java.util.*;

public class MinHeapForDijkstra<T> {
    private List<T> items = new ArrayList<>();
    private Map<T, Integer> itemsMapping = new HashMap<>();
    private Comparator<T> comparator;


    public MinHeapForDijkstra(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < items.size();
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < items.size();
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private T getLeftChild(int index) {
        return items.get(getLeftChildIndex(index));
    }

    private T getRightChild(int index) {
        return items.get(getRightChildIndex(index));
    }

    private T parent(int index) {
        return items.get(getParentIndex(index));
    }

    private void swap(int indexOne, int indexTwo) {
        T temp = items.get(indexOne);
        items.set(indexOne, items.get(indexTwo));
        itemsMapping.put(items.get(indexTwo), indexOne);
        items.set(indexTwo, temp);
        itemsMapping.put(temp, indexTwo);
    }

    public int size() {
        return items.size();
    }

    public T peek() {
        if (items.isEmpty()) throw new IllegalArgumentException();
        return items.get(0);
    }

    public T poll() {
        if (items.isEmpty()) throw new IllegalArgumentException();
        T item = items.get(0);
        if (items.size() == 1) {
            items.remove(0);
            itemsMapping.remove(item);
        } else {
            T lastItem = items.remove(items.size() - 1);
            items.set(0, lastItem);
            itemsMapping.put(lastItem, 0);
            itemsMapping.remove(item);
            heapifyDown();
        }
        return item;
    }

    public void add(T item) {
        items.add(item);
        itemsMapping.put(item, items.size() - 1);
        heapifyUp();
    }

    public T contains(T item) {
        Integer index = itemsMapping.get(item);
        if (index != null) {
            return items.get(index);
        }
        return null;
    }

    public void remove(T item) {
        Integer index = itemsMapping.get(item);
        if (items.size() == 1 || index == items.size() - 1) {
            items.remove(index.intValue());
            itemsMapping.remove(item);
        } else {
            T lastItem = items.remove(items.size() - 1);
            T deletedItem = items.get(index);
            items.set(index, lastItem);
            itemsMapping.put(lastItem, index);
            itemsMapping.remove(deletedItem);
            decideWhichHeapify(index);
        }

    }

    private void decideWhichHeapify(int index) {
        if (!hasParent(index)) {
            heapifyDown();
        } else if (comparator.compare(parent(index), items.get(index)) > 0) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
    }

    private void heapifyUp() {
        heapifyUp(items.size() - 1);
    }

    private void heapifyUp(int index) {
        while (hasParent(index) && comparator.compare(parent(index), items.get(index)) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        heapifyDown(0);
    }

    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(getRightChild(index), getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (comparator.compare(items.get(index), items.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            }
        }
    }
}
