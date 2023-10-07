package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Turtle {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Turtle().run();
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

        int[][] matrix = new int[rowsCnt+1][colsCnt+1];
        for(int i = 1; i <= rowsCnt; i++) {
            for(int j = 1; j <= colsCnt; j++) {
                matrix[i][j] = readInt();
            }
        }

        // dp[i][j] - минимально возможный штраф черепашки, чтобы попасть в клетку [i][j].
        long[][] dp = new long[rowsCnt+1][colsCnt+1];
        // Заполнение нулевой строки и нулевого столбца
        for(int i = 2; i <= rowsCnt; i++) {
            dp[i][0] = Long.MAX_VALUE;
        }
        for(int j = 2; j <= colsCnt; j++) {
            dp[0][j] = Long.MAX_VALUE;
        }

        for(int i = 1; i <= rowsCnt; i++) {
            for(int j = 1; j <= colsCnt; j++) {
                // в текущую клетку можно попасть только сверху либо слева
                dp[i][j] = (long)matrix[i][j] + Math.min(dp[i][j-1], dp[i-1][j]);
            }
        }

        // получаем маршрут
        List<int[]> pathCoords = new ArrayList<>();
        int i = rowsCnt, j = colsCnt;
        while (i > 1 || j > 1) {
            pathCoords.add(new int[] {i, j});
            if (dp[i][j-1] < dp[i-1][j]) j--; else i--;
        }
        pathCoords.add(new int[] {1, 1});

        long res = dp[rowsCnt][colsCnt];
        out.println(res);

        for(int k = pathCoords.size()-1; k >= 0; k--) {
            out.println(String.format("%d %d", pathCoords.get(k)[0], pathCoords.get(k)[1]));
        }
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
