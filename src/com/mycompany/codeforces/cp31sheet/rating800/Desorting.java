package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Desorting {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Desorting().run();
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
        for(int i = 0; i < n; i++) arr[i] = readInt();

        // solution
        int minDiff = Integer.MAX_VALUE;
        boolean isNotSorted = false;
        for(int i = 1; i < n; i++) {
            if (arr[i-1] > arr[i]) {
                isNotSorted = true;
                break;
            }
            minDiff = Math.min(minDiff, arr[i] - arr[i-1]);
        }
        out.println(isNotSorted ? 0 : minDiff/2 + 1);
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
