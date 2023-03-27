package com.mycompany.yandex.training10.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Ambulance {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Ambulance().run();
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
        long k1 = readLong();
        long m = readLong();
        long k2 = readLong();
        long p2 = readLong();
        long n2 = readLong();

        if (n2 > m) {
            out.println("-1 -1");
            return;
        }

        // x - количество квартир на площадке
        long xUpperDenom = m * (p2 - 1) + n2 - 1;
        long xLowerDenom = xUpperDenom + 1;

        if (xUpperDenom == 0) {
            printSpecialCase(m, k1, k2);
            return;
        }

        long xUpperBound = ceil(k2, xUpperDenom) - 1;
        long xLowerBound = ceil(k2, xLowerDenom);

        if (xUpperBound < xLowerBound) {
            out.println("-1 -1");
        } else {
            long minAreaCnt = ceil(k1, xUpperBound);
            long maxAreaCnt = ceil(k1, xLowerBound);

            long minP1 = ceil(minAreaCnt, m);
            long maxP1 = ceil(maxAreaCnt, m);
            long resP1 = (minP1 == maxP1) ? minP1 : 0;

            long minN1 = (minAreaCnt - 1) % m + 1;
            long maxN1 = (maxAreaCnt - 1) % m + 1;
            long resN1 = (minN1 == maxN1) ? minN1 : 0;

            out.println(resP1 + " " + resN1);
        }

    }

    private long ceil(long a, long b) {
        return ((a % b) == 0) ? a / b : (a / b + 1);
    }

    private void printSpecialCase(long m, long k1, long k2) {
        // p2 == 1, n2 == 1
        if (k1 <= k2) {
            out.println("1 1");
        } else {
            long resN1 = m == 1 ? 1 : 0;
            long minAreaCnt = k2;
            long resP1 = (k1 <= minAreaCnt*m) ? 1 : 0;
            out.println(resP1 + " " + resN1);
        }
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private long readLong() {
        return Long.parseLong(readString());
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


