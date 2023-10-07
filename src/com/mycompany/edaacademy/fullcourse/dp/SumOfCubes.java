package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SumOfCubes {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SumOfCubes().run();
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

        // dp[i] = минимальное кол-во кубов целых чисел, сумма которых равна i
        long dp[] = new long[n+3];

        dp[0] = 0L;
        dp[1] = 1L;
        dp[2] = 2L;

        for(int i = 3; i<= n; i++) {
            long minPrev = Long.MAX_VALUE;
            long j = 1L;
            long jCube = 1L;
            long diff = Long.valueOf(i) - jCube;
            while(diff >= 0L) {
                if (dp[i - (int)jCube] < minPrev) {
                    minPrev = dp[i - (int)jCube];
                }
                if (diff == 0L) break;
                j++;
                try {
                    jCube = Math.multiplyExact(j * j, j);
                } catch (ArithmeticException e) {
                    break;
                }
                diff = Long.valueOf(i) - jCube;
            }
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
