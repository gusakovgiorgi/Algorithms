package com.example.algorithms;

import com.example.algorithms.selectiction.DeterministicSelection;

public class Main {


    public static void main(String[] args) {

//        int[] arr = new int[]{3, 2, 5, 7, 6, 1, 8};
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("RSelect of " + i + " = " + RandomizedSelection.getIthOrderElementInArray(arr, i));
//            System.out.println("DSelect of " + i + " = " + DeterministicSelection.getIthOrderElementInArray(arr, i));
//        }
        int[] arr = new int[]{0, 11, 0, 0, 0, 0, 0, 0, 0, 16};
        System.out.println("DSelect of " + 8 + " = " + DeterministicSelection.getIthOrderElementInArray(arr, 8));


    }
}
