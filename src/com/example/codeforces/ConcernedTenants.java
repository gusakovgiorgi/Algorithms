package com.example.codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class ConcernedTenants {

    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        byte n = (byte) sc.nextInt();
        boolean[] a = new boolean[n];
        for (byte i = 0; i < n; i++) {
            a[i] = sc.nextInt() == 1;
        }

        byte k = 0;
        for (byte i = 1; i < n; i++) {
            if (i == n - 1) break;
            if (a[i - 1] && !a[i] && a[i + 1]) {
                a[i + 1] = false;
                k++;
            }
        }
        out.println(k);
        out.close();
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
