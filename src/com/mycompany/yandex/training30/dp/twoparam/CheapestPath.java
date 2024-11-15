package com.mycompany.yandex.training30.dp.twoparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class CheapestPath {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CheapestPath().run();
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

        // dp[i][j] - минимальный вес еды в килограммах, отдав которую игрок может попасть в клетку [i][j].
        int[][] dp = new int[rowsCnt+1][colsCnt+1];
        // Заполнение нулевой строки и нулевого столбца
        for(int i = 2; i <= rowsCnt; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for(int j = 2; j <= colsCnt; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        for(int i = 1; i <= rowsCnt; i++) {
            for(int j = 1; j <= colsCnt; j++) {
                // в текущую клетку можно попасть только сверху либо слева
                dp[i][j] = matrix[i][j] + Math.min(dp[i][j-1], dp[i-1][j]);
            }
        }

        int res = dp[rowsCnt][colsCnt];
        out.println(res);
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
