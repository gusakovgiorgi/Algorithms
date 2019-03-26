package com.example.codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class GoodString {

    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int tests = sc.nextInt();
        for (int i = 0; i < tests; i++) {
            sc.nextLine();
            out.println(numberOfCuts(sc.nextLine().toCharArray()));
        }

        out.close();
    }

    private static int numberOfCuts(char[] symbols) {
//        if (symbols[0] == '>') return 0;
//        return numberOfCutsRecursive(0, symbols);
        if (symbols.length == 1) return 0;
        if (symbols[0] == '<' && symbols[symbols.length - 1] == '>') return 1;
        return 0;
    }

//    private static int numberOfCutsRecursive(int startSymbol, char[] symbols) {
//        if (startSymbol >= symbols.length) return 0;
//        for (int i = startSymbol; i < symbols.length; i++) {
//            if (symbols[i] == '<') {
//                return numberOfCutsRecursive(i + 1, symbols);
//            }
//        }
//        return 1;
//    }

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
