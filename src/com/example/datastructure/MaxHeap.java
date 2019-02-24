package com.example.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxHeap<T> {
    private List<T> items = new ArrayList<>();
    private Comparator<T> comparator;


    public MaxHeap(Comparator<T> comparator) {
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
        items.set(indexTwo, temp);
    }

    public int size() {
        return items.size();
    }

    public T peek() {
        if (items.isEmpty()) return null;
        return items.get(0);
    }

    public T poll() {
        if (items.isEmpty()) return null;
        T item = items.get(0);
        if (items.size() == 1) {
            items.remove(0);
        } else {
            T lastItem = items.remove(items.size() - 1);
            items.set(0, lastItem);
            heapifyDown();
        }
        return item;
    }

    public void add(T item) {
        items.add(item);
        heapifyUp();
    }

    private void heapifyUp() {
        int index = items.size() - 1;
        while (hasParent(index) && comparator.compare(parent(index), items.get(index)) < 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(getRightChild(index), getLeftChild(index)) > 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (comparator.compare(items.get(index), items.get(smallerChildIndex)) > 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            }
        }
    }
}
