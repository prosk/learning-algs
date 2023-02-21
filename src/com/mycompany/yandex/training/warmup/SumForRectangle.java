package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SumForRectangle {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SumForRectangle().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        // Ввод данных
        int rowsCnt = readInt();
        int colsCnt = readInt();
        int queriesCnt = readInt();

        int[][] matrix = new int[rowsCnt+1][colsCnt+1];

        for(int i = 1; i <= rowsCnt; i++) {
            int currRowSum = 0;
            for(int j = 1; j <= colsCnt; j++) {
                currRowSum += readInt();
                 matrix[i][j] = currRowSum + matrix[i-1][j];
            }
        }

        for(int i = 0; i < queriesCnt; i++) {
            int[] currQuery = readIntArray(4);
            int sum = getSubMatrixSum(matrix, currQuery);
            out.println(sum);
        }

    }

    private int getSubMatrixSum(int[][] matrix, int[] coords) {
        int x1 = coords[0], y1 = coords[1];
        int x2 = coords[2], y2 = coords[3];
        int s1 = matrix[x2][y2] - matrix[x1-1][y2];
        int s2 = matrix[x2][y1-1] - matrix[x1-1][y1-1];
        return s1 - s2;
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
