package com.mycompany.codeforces.div2round1108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task1_500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Task1_500().run();
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
        int[] res = new int[n];
        int j = 0;
        for(int i = 2; i <= n; i += 2) {
            res[j] = i;
            j += 2;
        }
        j = 1;
        for(int i = 1; i <= n; i += 2) {
            res[j] = i;
            j += 2;
        }
        String s = "";
        for(int i = 0; i < n; i++) {
            if (i > 0) {
                s += " ";
            }
            s += String.valueOf(res[i]);
        }
        out.println(s);
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