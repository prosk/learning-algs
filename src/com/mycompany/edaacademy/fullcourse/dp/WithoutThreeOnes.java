package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class WithoutThreeOnes {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new WithoutThreeOnes().run();
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

        int dp[] = new int[inputNum+5];

        dp[0] = 0;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        dp[4] = 13;

       /*
         Пусть ответ на задачу для числа n равен dp[n]
         Можно выделить три случая:
           1) последовательности, кончающиеся на 0, их dp[n-1]
           2) последовательности, кончающиеся на 01, их dp[n-2]
           3) последовательности, кончающиеся на 011, их dp[n-3]
           Отсюда получаем формулу dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        */

        if (inputNum <= 4) {
            out.println(dp[inputNum]);
        } else {
            int modulo = 12345;
            for(int i = 5; i <= inputNum; i++) {
                dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % modulo;
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
