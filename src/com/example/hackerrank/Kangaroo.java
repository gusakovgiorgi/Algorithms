package com.example.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class Kangaroo {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the kangaroo function below.
    static String kangaroo(int x1, int v1, int x2, int v2) {
        if (v1 <= v2) return "NO";
        long pos1 = x1 + v1;
        long pos2 = x2 + v2;
        do {
            pos1 += v1;
            pos2 += v2;
        } while (pos1 >= pos2);

        return pos1 == pos2 ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String[] x1V1X2V2 = scanner.nextLine().split(" ");
//
//        int x1 = Integer.parseInt(x1V1X2V2[0]);
//
//        int v1 = Integer.parseInt(x1V1X2V2[1]);
//
//        int x2 = Integer.parseInt(x1V1X2V2[2]);
//
//        int v2 = Integer.parseInt(x1V1X2V2[3]);

        String result = kangaroo(0, 3, 4, 2);
        System.out.println(result);

//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
//
//        scanner.close();
    }
}
