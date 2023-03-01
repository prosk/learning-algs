package com.mycompany.yandex.training.dp.oneparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class ThreeOnes {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ThreeOnes().run();
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
        int inputNum = readInt();

        int dp[] = new int[36];

        dp[0] = 0;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        dp[4] = 13;

        // dp[i] - число последовательностей 0 и 1 длины i,
        // в которых никакие три единицы не стоят рядом.

        // dp[i] = dp[i-1] (добавляем 0 слева) + (dp[i-1] - dp[i-4])
        // (добавляем 1 слева ко всем, кроме тех, которые начинаются на 110)

        if (inputNum <= 4) {
            out.println(dp[inputNum]);
        } else {
            for(int i = 5; i <= inputNum; i++) {
                dp[i] = dp[i-1]*2 - dp[i-4];
            }
            out.println(dp[inputNum]);
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


