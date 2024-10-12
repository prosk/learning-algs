//package com.mycompany.codeforces.div2round977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class AdjustThePresentation {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new AdjustThePresentation().run();
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
        int m = readInt();
        int q = readInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i = 0; i < n; i++) a[i] = readInt();
        for(int i = 0; i < m; i++) b[i] = readInt();

        // main
        boolean isGood = true;
        int nextInLineInd = 0;
        int nextInLine = a[nextInLineInd];
        Set<Integer> free = new HashSet<>();
        for(int i = 0; i < m; i++) {
            int curMember = b[i];
            if (curMember == nextInLine) {
                free.add(nextInLine);
                nextInLineInd++;
                nextInLine = (nextInLineInd < n) ? a[nextInLineInd] : -1;
            } else if (!free.contains(curMember)) {
                isGood = false;
                break;
            }
        }

        out.println(isGood ? "YA" : "TIDAK");
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