// package com.mycompany.cses.problemset.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinimizingCoins {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MinimizingCoins().run();
        out.close();
    }

    // https://www.youtube.com/watch?v=lJAYDAhPOpI
    void run() {
        int n = readInt();
        int x = readInt();
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = readInt();
        }

        // dp[i] = min count of coins to get sum = i
        //
        int[] dp = new int[x+1];
        dp[0] = 0;
        final int INF = 1_000_000_000;
        for(int i = 1; i <= x; i++) {
            dp[i] = INF;
        }

        for(int i = 1; i <= x; i++) {
            for(int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        out.println(dp[x] == INF ? -1 : dp[x]);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
