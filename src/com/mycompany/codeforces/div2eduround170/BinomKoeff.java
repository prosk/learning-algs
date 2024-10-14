// package com.mycompany.codeforces.div2eduround170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BinomKoeff {

    private final static int MODULO = 1000 * 1000 * 1000 + 7;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BinomKoeff().run();
        out.close();
    }

    void run() {
        int t = readInt();
        int[] nArr = new int[t];
        int[] kArr = new int[t];
        for(int i = 0; i < t; i++) {
            nArr[i] = readInt();
        }
        for(int i = 0; i < t; i++) {
            kArr[i] = readInt();
        }

        for(int i = 0; i < t; i++) {
            out.println(binpow(2, kArr[i]));
        }
        out.flush();
    }

    private static int binpow(long base, long power) {
        if (power == 0) return 1;
        if ((power & 1) == 0) {
            long half = binpow(base, power >> 1);
            return mult(half, half);
        } else {
            long prev = binpow(base, power - 1);
            return mult(prev, base);
        }
    }

    private static int mult(long a, long b) { return (int)((a * b) % MODULO); }



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