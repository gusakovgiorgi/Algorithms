package com.example.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GradingStudents {

    private static final Scanner scan = new Scanner(System.in);

    /*
     * Complete the gradingStudents function below.
     */
    static int[] gradingStudents(int[] grades) {
        int[] avgGrades = new int[grades.length];
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] < 38) {
                avgGrades[i] = grades[i];
            } else if (nextMultipleOfFive(grades[i]) - grades[i] < 3) {
                avgGrades[i] = nextMultipleOfFive(grades[i]);
            } else {
                avgGrades[i] = grades[i];
            }
        }
        return avgGrades;
    }

    private static int nextMultipleOfFive(int grade) {
        while (grade % 5 != 0) {
            grade++;
        }
        return grade;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] grades = new int[n];

        for (int gradesItr = 0; gradesItr < n; gradesItr++) {
            int gradesItem = Integer.parseInt(scan.nextLine().trim());
            grades[gradesItr] = gradesItem;
        }

        int[] result = gradingStudents(grades);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}
