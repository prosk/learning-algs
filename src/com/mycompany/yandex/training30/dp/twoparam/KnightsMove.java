package com.mycompany.yandex.training30.dp.twoparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class KnightsMove {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new KnightsMove().run();
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

        // dp[i][j] - кол-во способов добраться до клетки [i][j]
        int[][] dp = new int[rowsCnt+2][colsCnt+2];
        dp[2-1][2-2] = 1; // сначала конь в верхнем левом углу, это чтобы в dp[2][2] было 1

        for(int i = 2; i < (rowsCnt+2); i++) {
            for(int j = 2; j < (colsCnt+2); j++) {
                // в текущую клетку можно попасть только из 2-х других клеток
                dp[i][j] = dp[i-1][j-2] + dp[i-2][j-1];
            }
        }

        int res = dp[rowsCnt+1][colsCnt+1];
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

