package com.mycompany.codeforces.courses.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AddingConstOnSegment {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new AddingConstOnSegment().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
        // difference array
        long[] diff = new long[n];
        for(int i = 1; i <= n; i++) {
            diff[i-1] = arr[i] - arr[i-1];
        }
        // queries
        int q = readInt();
        while(q-- > 0) {
            int l = readInt() - 1;
            int r = readInt() - 1;
            long d = readInt();
            diff[l] += d;
            if (r + 1 < n) {
                diff[r + 1] -= d;
            }
        }
        long[] ans = new long[n+1];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            ans[i] = ans[i-1] + diff[i-1];
            if (i > 1) sb.append(' ');
            sb.append(ans[i]);
        }
        out.println(sb);
    }

    //
    // 1, 5, 7, 15, 19
    // 0, 1, 5, 7, 15, 19
    // 1, 4, 2, 8, 4

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