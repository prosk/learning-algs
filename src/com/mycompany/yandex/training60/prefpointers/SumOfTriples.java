//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SumOfTriples {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new SumOfTriples().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long[] arr = new long[n+1];
        long[] pref = new long[n+2];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            pref[i+1] = pref[i] + arr[i];
        }
        long ans = 0;
        long MODULO = 1_000_000_007L;
        for(int ind = 2; ind <= n-1; ind++) {
            long left = getSum(pref, 1, ind-1) % MODULO;
            long curr = arr[ind] % MODULO;
            long right = getSum(pref, ind+1, n) % MODULO;
            long mult1 = (left * curr) % MODULO;
            long mult2 = (mult1 * right) % MODULO;
            ans = (ans + mult2) % MODULO;
        }
        out.println(ans);
    }

    // sum on the segment [i, j]
    long getSum(long pref[], int i, int j) {
        return pref[j+1] - pref[i];
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