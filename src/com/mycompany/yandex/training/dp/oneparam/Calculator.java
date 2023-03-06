package com.mycompany.yandex.training.dp.oneparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Calculator {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Calculator().run();
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

        int dp[] = new int[inputNum+3];
        int operationIndex[] = new int[inputNum+3]; // 1 - прибавление 1; 2 - умножение на 2; 3 - умножение на 3

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        operationIndex[2] = 2;
        operationIndex[3] = 3;

        if (inputNum > 3) {
            for(int i = 4; i <= inputNum; i++) {
                int multiplyBy3 = (i % 3) == 0 ? dp[i/3] + 1 : Integer.MAX_VALUE;
                int multiplyBy2 = (i % 2) == 0 ? dp[i/2] + 1 : Integer.MAX_VALUE;
                int plus1 = dp[i-1] + 1;

                int res = Math.min(Math.min(multiplyBy3, multiplyBy2), plus1);

                dp[i] = res;
                if (res == multiplyBy3) {
                    operationIndex[i] = 3;
                } else if (res == multiplyBy2) {
                    operationIndex[i] = 2;
                } else {
                    operationIndex[i] = 1;
                }
            }
        }

        if (inputNum == 1) {
            out.println("0");
            out.println("1");
            return;
        }

        // собираем вывод
        int[] resNums = new int[dp[inputNum]+1];
        int j = inputNum;
        int i = 0;
        resNums[i] = inputNum;
        while (j > 1) {
            if (operationIndex[j] == 3) {
                j = j / 3;
            } else if (operationIndex[j] == 2) {
                j = j / 2;
            } else {
                j = j - 1;
            }
            resNums[++i] = j;
        }

        StringBuilder sb = new StringBuilder("");
        sb.append(resNums[dp[inputNum]]);
        for(int k = dp[inputNum]-1; k >= 0; k--) {
            sb.append(" ");
            sb.append(resNums[k]);
        }

        out.println(dp[inputNum]);
        out.println(sb);
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
