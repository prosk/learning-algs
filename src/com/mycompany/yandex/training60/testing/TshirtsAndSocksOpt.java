// package com.mycompany.yandex.training60.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TshirtsAndSocksOpt {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TshirtsAndSocksOpt().run();
        out.close();
    }

    void run() {
        int a = readInt();
        int b = readInt();
        int c = readInt();
        int d = readInt();

        List<Pair> pairs = new ArrayList<>();

        if (a > 0 && b > 0 && c > 0 && d > 0) {
            pairs.add(new Pair(a+1, c+1));
            pairs.add(new Pair(b+1, d+1));
        }
        if (a == 0 || c == 0) {
            pairs.add(new Pair(a+1, c+1));
        }
        if (b == 0 || d == 0) {
            pairs.add(new Pair(b+1, d+1));
        }
        if (a > 0 && b > 0) {
            pairs.add(new Pair(Math.max(a, b) + 1, 1));
        }
        if (c > 0 && d > 0) {
            pairs.add(new Pair( 1, Math.max(c, d) + 1));
        }
        Collections.sort(pairs);
        out.println(pairs.get(0).m + " " + pairs.get(0).n);
    }

    public static class Pair implements Comparable<Pair> {
        int m;
        int n;

        Pair(int m, int n) {
            this.m = m;
            this.n = n;
        }


        @Override
        public int compareTo(Pair o) {
            return Integer.compare(m + n, o.m + o.n);
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