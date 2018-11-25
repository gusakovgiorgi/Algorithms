package com.example.codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class JumpedFrogs {
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        short t = (short) sc.nextInt();

        for (int i = 0; i < t; i++) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            long k = sc.nextInt();

            boolean even = k % 2 == 0;
            long answer;
            if (a != b) {
                if (even) {
                    answer = (a - b) * k / 2;
                } else {
                    answer = (a - b) * (k - 1) / 2 + a;
                }
            } else {
                if (even) {
                    answer = 0;
                } else {
                    answer = a;
                }
            }
            out.println(answer);
        }
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
