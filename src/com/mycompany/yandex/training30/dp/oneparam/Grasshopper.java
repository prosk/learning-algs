package com.mycompany.yandex.training30.dp.oneparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Grasshopper {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Grasshopper().run();
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
        int allCellsCnt = readInt();
        int maxJumpCellsCnt = readInt();

        if (allCellsCnt == 1) {
            out.println("1");
            return;
        }

        int dp[] = new int[allCellsCnt+1];

        dp[1] = 1;
        dp[2] = 1; // maxJumpCellsCnt >= 1

        for(int i = 3; i<= allCellsCnt; i++) {
            int lastMaxJumpSum = 0;
            for(int j = i-1; j >= (i - maxJumpCellsCnt) && j >= 1; j--) {
                lastMaxJumpSum += dp[j];
            }
            dp[i] = lastMaxJumpSum;
        }
        out.println(dp[allCellsCnt]);
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
