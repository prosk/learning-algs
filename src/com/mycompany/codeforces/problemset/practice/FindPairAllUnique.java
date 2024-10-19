// package com.mycompany.codeforces.problemset.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindPairAllUnique {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new FindPairAllUnique().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long k = readLong();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr);

        int firstInd = (int) ((k-1) / n);
        int secondInd = (int) ((k-1) % n);


        out.printf("%d %d\n", arr[firstInd], arr[secondInd]);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    long readLong() {
        return Long.parseLong(readString());
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