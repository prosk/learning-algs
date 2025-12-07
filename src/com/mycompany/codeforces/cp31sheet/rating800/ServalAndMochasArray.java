package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ServalAndMochasArray {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ServalAndMochasArray().run();
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
        for(int i = 0; i < n; i++) arr[i] = readInt();
        // solution
        boolean ans = false;
        for(int i = 0; i < n-1; i++) {
            for(int j = i+1; j < n; j++) {
                if (gcd(arr[i], arr[j]) <= 2) {
                    ans = true;
                    break;
                }
            }
        }
        out.println(ans ? "YES" : "NO");
    }

    private static int gcd(int a, int b) {
        return (a == 0 ? b : gcd(b % a, a));
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