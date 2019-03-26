package com.example.codeforces;

import java.io.*;
import java.util.*;

public class PointsInSegments {
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        int m = sc.nextInt();

        Segment[] segments = new Segment[n];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                if (o1.left - o2.left == 0) {
                    return o1.right - o2.right;
                }
                return o1.left - o2.left;
            }
        });

        List<Integer> missedPoints = new LinkedList<>();
        int lastPoint = 0;
        for (Segment segment : segments) {
            if (lastPoint == 0) {
                if (segment.left > 1) {
                    int missedPoint = 1;
                    do {
                        missedPoints.add(missedPoint);
                        missedPoint++;
                    } while (missedPoint != segment.left);
                }
                lastPoint = segment.right;
            } else {
                if (segment.left > lastPoint + 1) {
                    int missedPoint = lastPoint + 1;
                    do {
                        missedPoints.add(missedPoint);
                        missedPoint++;
                    } while (missedPoint < segment.left);
                }
                if (segment.right > lastPoint) {
                    lastPoint = segment.right;
                }
            }
        }

        if (lastPoint < m) {
            do {
                missedPoints.add(++lastPoint);
            } while (lastPoint != m);
        }

        out.println(missedPoints.size());
        for (Integer point : missedPoints) {
            out.print(point + " ");
        }

        out.flush();
        out.close();
    }

    static class Segment {
        int left;
        int right;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
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
//--------------------------------------------------------}
}
