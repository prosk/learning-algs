package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class VasyaAndVertexes {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new VasyaAndVertexes().run();
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
        long n = readLong();
        long m = readLong();

        long minAns, maxAns;
        if (2*m >= n)
            minAns = 0;
        else
            minAns = n - 2*m;

        if (m == 0)
            maxAns = n;
        else {
            maxAns = 0;
            for(long i = 2; i < n; i++) {
                long fullM = i * (i-1) / 2;
                if (m <= fullM) {
                    maxAns = n - i;
                    break;
                }
            }
        }

        out.println(minAns + " " + maxAns);
    }

    private long readLong() {
        return Long.parseLong(readString());
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
