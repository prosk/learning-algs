package com.mycompany.codeforces.courses.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SumOnSegment {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new SumOnSegment().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        long[] pref = new long[n+1];
        for(int i = 0; i < n; i++) {
            pref[i+1] = pref[i] + arr[i];
        }
        // queries
        int q = readInt();
        while(q-- > 0) {
            int l = readInt() - 1;
            int r = readInt() - 1;
            long sum = pref[r+1] - pref[l];
            out.println(sum);
        }
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