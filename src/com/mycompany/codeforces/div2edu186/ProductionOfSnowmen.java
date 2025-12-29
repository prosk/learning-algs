package com.mycompany.codeforces.div2edu186;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProductionOfSnowmen {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ProductionOfSnowmen().run();
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
        int MAX = 3*n;

        int[] a = new int[n];
        int minA = MAX + 1, maxA = 0;
        for(int i = 0; i < n; i++) {
            a[i] = readInt();
            minA = Math.min(minA, a[i]);
            maxA = Math.max(maxA, a[i]);
        }

        int[] b = new int[n];
        int minB = MAX + 1, maxB = 0;
        for(int i = 0; i < n; i++) {
            b[i] = readInt();
            minB = Math.min(minB, b[i]);
            maxB = Math.max(maxB, b[i]);
        }

        int[] c = new int[n];
        int minC = MAX + 1, maxC = 0;
        for(int i = 0; i < n; i++) {
            c[i] = readInt();
            minC = Math.min(minC, c[i]);
            maxC = Math.max(maxC, c[i]);
        }

        if (maxA < minB && maxB < minC) {
            out.println(n*n*n);
            return;
        }
        int bShiftedCnt = 0;
        for(int startPosInB = 0; startPosInB < n; startPosInB++) {
            if (checkForShiftedB(a, b, c, startPosInB)) {
                bShiftedCnt++;
            }
        }
        int mult = (maxA < minB || maxB < minC) ? n*n : n;
        int ans = bShiftedCnt * mult;
        out.println(ans);
    }

    boolean checkForShiftedB(int[] a, int[] b, int[] c, int startPosInB) {
        int indB = startPosInB;
        int n = a.length, cnt = 0;
        for(int i = 0; i < n; i++) {
            if (a[i] < b[indB] && b[indB] < c[i]) {
                cnt++;
            } else {
                return false;
            }
            indB = (indB + 1) % n;
        }
        return true;
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