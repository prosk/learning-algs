package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SequenceGame {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new SequenceGame().run();
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
        int[] b = new int[n];
        for(int i = 0; i < n; i++) b[i] = readInt();

        List<Integer> a = new ArrayList<>();
        a.add(b[0]);
        // Solution
        for(int i = 1; i < n; i++) {
            if (b[i - 1] > 1) {
                int newElem = Math.min(b[i], b[i - 1]) - 1;
                a.add(Math.max(newElem, 1));
            }
            a.add(b[i]);
        }
        out.println(a.size());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(a.get(i));
        }
        out.println(sb);
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

