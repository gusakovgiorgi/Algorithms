package com.example.codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class GoodArray {


    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        if (n == 2) {
            out.println(0);
            out.close();
            return;
        }
        List<Number> arr = new ArrayList<>(n + 1);
        for (int i = 0; i < n; i++) {
            long num = sc.nextInt();
            arr.add(new Number(i, num));
        }

        arr.sort(Comparator.comparingLong(o -> o.number));
        int k = 0;
        int[] indexes = new int[n];

        int removedIndex = 0;
        long max;
        long sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i).number;
        }
        long curSum;
        do {
            max = arr.get(arr.size() - 1).number;
            curSum = sum - max - arr.get(removedIndex).number;
            if (curSum == max) {
                indexes[k++] = arr.get(removedIndex).index + 1;
            }
            removedIndex++;
        } while (curSum >= max && removedIndex != arr.size() - 1);

        max = arr.get(arr.size() - 2).number;
        curSum = sum - max - arr.get(arr.size() - 1).number;
        if (curSum == max) {
            indexes[k++] = arr.get(arr.size() - 1).index + 1;
        }

        out.println(k);
        for (int i = 0; i < k; i++) {
            out.print(indexes[i]);
            out.print(" ");
        }

        out.close();
    }

    private static class Number {
        int index;
        long number;

        public Number(int index, long number) {
            this.index = index;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "index=" + index +
                    ", number=" + number +
                    '}';
        }
    }

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    //--------------------------------------------------------
}
