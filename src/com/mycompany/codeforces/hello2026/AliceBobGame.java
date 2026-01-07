package com.mycompany.codeforces.hello2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AliceBobGame {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new AliceBobGame().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        int oneCnt = 0, zeroCnt = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            if (arr[i] == 1) oneCnt++; else zeroCnt++;
        }
        String winner = "";
        if (oneCnt == n) {
            winner = "Alice";
        } else if (zeroCnt == n) {
            winner = "Bob";
        } else {
            winner = (arr[0] == 1 || arr[n-1] == 1) ? "Alice" : "Bob";
        }
        out.println(winner);
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