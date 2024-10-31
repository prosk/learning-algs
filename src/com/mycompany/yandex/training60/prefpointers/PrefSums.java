//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PrefSums {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new PrefSums().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        int[] pref = new int[n+1];
        for(int i = 1; i <= n; i++) {
            pref[i] = pref[i-1] + arr[i-1];
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 1; i <= n; i++) {
            sb.append(pref[i]);
            if (i < n) sb.append(' ');
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