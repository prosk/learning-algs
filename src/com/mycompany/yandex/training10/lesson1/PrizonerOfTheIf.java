package com.mycompany.yandex.training10.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class PrizonerOfTheIf {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PrizonerOfTheIf().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int a = readInt();
        int b = readInt();
        int c = readInt();
        int d = readInt();
        int e = readInt();
        // d * e - отверстие в стене
        // a * b * c - размеры кирпича
        boolean resCanThrow = isCanThrow(d, e, a, b) ||
               isCanThrow(d, e, a, c) ||
               isCanThrow(d, e, b, c);
        out.println(resCanThrow ? "YES" : "NO");
    }

    private boolean isCanThrow(int w1, int w2, int k1, int k2) {
        return (k1 <= w1 && k2 <= w2) ||
                (k2 <= w1 && k1 <= w2);
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
