package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class DigitRemoving {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new DigitRemoving().run();
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

        // dp[i] = минимальное кол-во шагов чтобы вычитанием одной из цифр сделать число i равным нулю
        long dp[] = new long[n+10];

        for(int i = 1; i <= 9; i++)
            dp[i] = 1;

        for(int i = 10; i<= n; i++) {
            long minPrev = Long.MAX_VALUE;

            int currVal = i, digit;
            do {
                digit = currVal % 10;
                currVal = currVal / 10;
                if (digit > 0) {
                    minPrev = Math.min(minPrev, dp[i - digit]);
                }
            } while (currVal > 0);

            dp[i] = minPrev+1L;
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
