package com.mycompany.yandex.training40.stringhash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SubstringEquality {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SubstringEquality().run();
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
        String inpString = readString();

        long[][] hashParams = { {257, 1_000_000_007}, {31, 433_494_437} };

        HashPrefix hp = new HashPrefix(inpString, hashParams[0][0], hashParams[0][1]);

        /*Во второй строке записано число Q (1 ≤ Q ≤ 2 ⋅ 105) — количество запросов.
        В следющих Q строках записаны запросы: целые числа L, A и B (1 ≤ L ≤ |S|, 0 ≤ A, B ≤ (|S| - L))
        — длина подстрок и позиции, с которых они начинаются.*/

        int Q = readInt();
        for(int i = 1; i <= Q; i++) {
            int L = readInt();
            int A = readInt();
            int B = readInt();
            boolean res = isSubstringEquals(hp, A, B, L);
            out.println(res ? "yes" : "no");
        }

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

    public boolean isSubstringEquals(HashPrefix hp, int from1, int from2, int slen) {
        from1++;
        from2++;
        long v1 = (hp.h[from1 + slen - 1] + hp.h[from2-1]*hp.x[slen]) % hp.modulo;
        long v2 = (hp.h[from2 + slen - 1] + hp.h[from1-1]*hp.x[slen]) % hp.modulo;
        return v1 == v2;
    }

    public static class HashPrefix {
        public long modulo;
        public long[] x;
        public long[] h;

        public HashPrefix(String s, long point, long modulo) {
            this.modulo = modulo;
            int len = s.length();
            x = new long[len+1];
            h = new long[len+1];
            x[0] = 1;
            for(int i = 1; i <= len; i++) {
                h[i] = (h[i-1]*point + (long)s.charAt(i-1)) % modulo;
                x[i] = (x[i-1]*point) % modulo;
            }
        }
    }

}
