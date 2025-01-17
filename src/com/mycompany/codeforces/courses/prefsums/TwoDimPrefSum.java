package com.mycompany.codeforces.courses.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoDimPrefSum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TwoDimPrefSum().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int m = readInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = readInt();
            }
        }
        long[][] pref = new long[n+1][m+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                pref[i+1][j+1] = pref[i][j+1] + pref[i+1][j] - pref[i][j] + arr[i][j];
            }
        }

        // queries
        int q = readInt();
        while(q-- > 0) {
            int lx = readInt() - 1;
            int ly = readInt() - 1;
            int rx = readInt();
            int ry = readInt();
            long sum = pref[rx][ry] - pref[lx][ry] - pref[rx][ly] + pref[lx][ly];
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