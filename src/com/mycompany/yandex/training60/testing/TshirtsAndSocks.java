//package com.mycompany.yandex.training60.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TshirtsAndSocks {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TshirtsAndSocks().run();
        out.close();
    }

    void run() {
        int a = readInt();
        int b = readInt();
        int c = readInt();
        int d = readInt();

        int m = 0, n = 0;
        Color outputColor = null;

        if (a > 0 && b > 0 && c > 0 && d > 0) {
            if (b + d < a + c) {
                m = b + 1;
                n = d + 1;
                outputColor = Color.RED;
            } else {
                m = a + 1;
                n = c + 1;
                outputColor = Color.BLUE;
            }
        } else {
            if (a == 0 || c == 0) {
                m = a + 1;
                n = c + 1;
                outputColor = Color.BLUE;
            } else if (b == 0 || d == 0) {
                m = b + 1;
                n = d + 1;
                outputColor = Color.RED;
            }
        }

        int m1 = (int) (1e9 + 1), n1 = (int) (1e9 + 1);
        int m2 = (int) (1e9 + 1), n2 = (int) (1e9 + 1);

        if (a > 0 && b > 0 && m > a && m > b) {
            m1 = m;
            n1 = 1;
        }
        if (c > 0 && d > 0 && n > c && n > d) {
            m2 = 1;
            n2 = n;
        }
        int sum1 = m1 + n1, sum2 = m2 + n2, sum3 = m + n;

        int minSum = Integer.min(Integer.min(sum1, sum2), sum3);
        if (minSum == sum1) {
            out.println(m1 + " " + n1);
        } else if (minSum == sum2) {
            out.println(m2 + " " + n2);
        } else {
            out.println(m + " " + n);
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

    public enum Color {
        BLUE, RED
    }
}