// package com.mycompany.cses.problemset.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DiceCombinations {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new DiceCombinations().runWithDiffBase();
        out.close();
    }

    void run() {
        int n = readInt();
        int modulo = 1_000_000_000 + 7;
        int[] dp = new int[n+2];
        // dp[i] = number of ways to construct sum i by throwing a dice one or more times
        // base solutions
        dp[1] = 1;
        dp[2] = 2; // 1 + 1, 2
        // dp[3] = 4; // 1 + 1 + 1, 1 + 2, 2 + 1, 3

        for(int i = 3; i <= n; i++) {
            int j = 1;
            while((i - j) > 0 && j <= 6) {
                dp[i] = (dp[i] + dp[i-j]) % modulo;
                j++;
            }
            if (i <= 6) {
                dp[i]++;
            }
        }
        out.println(dp[n]);
    }

    void runWithDiffBase() {
        int n = readInt();
        int modulo = 1_000_000_000 + 7;
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= 6; j++) {
                if (i >= j) dp[i] = (dp[i] + dp[i-j]) % modulo;
            }
        }
        out.println(dp[n]);
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
