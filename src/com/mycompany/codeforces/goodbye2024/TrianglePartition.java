package com.mycompany.codeforces.goodbye2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TrianglePartition {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TrianglePartition().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        boolean ans = false;
        for(int i = 0; i < n-1; i++) {
            // check if pair arr[i], arr[i+1] is stable
            if (isStable(arr[i], arr[i+1])) {
                ans = true; break;
            }
        }
        out.println(ans ? "YES" : "NO");
    }

    private boolean isStable(int a, int b) {
        // a, a, b
        // b, b, a
        return isTrianle(a, a, b) && isTrianle(b, b, a);
    }

    private boolean isTrianle(int a, int b, int c) {
        return (a + b > c) && (b + c > a) && (a + c > b);
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