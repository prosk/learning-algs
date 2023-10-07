package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class OnesExpression {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new OnesExpression().run();
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
        int n = readInt();

        // dp[i] = наименьшее количество единиц нужно использовать, чтобы получить число i
        int dp[] = new int[n+3];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i = 4; i<= n; i++) {
            int minVal = Integer.MAX_VALUE;
            // сложение и умножение
            for(int j = 1; j <= i/2; j++) {
                // i = j + (i - j)
                minVal = Math.min(minVal, dp[j] + dp[i - j]);
                if (j > 1 && j < i/2 && (i % j) == 0) {
                    // i = j * (i/j)
                    minVal = Math.min(minVal, dp[j] + dp[i/j]);
                }
            }
            dp[i] = minVal;
        }
        out.println(dp[n]);
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

