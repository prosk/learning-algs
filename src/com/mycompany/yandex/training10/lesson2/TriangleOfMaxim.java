package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class TriangleOfMaxim {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TriangleOfMaxim().run();
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
        int noteCnt = readInt();
        double[] freq = new double[noteCnt];
        boolean[] isCloser = new boolean[noteCnt];

        for(int i = 0; i < noteCnt; i++) {
            freq[i] = readDouble();
            if (i > 0) {
                String diff = readString();
                isCloser[i] = "closer".equals(diff);
            }
        }

        double lowerBound = 30.0;
        double upperBound = 4000.0;

        double prev, curr, mid;
        for(int i = 1; i < noteCnt; i++) {
            prev = freq[i-1];
            curr = freq[i];
            mid = (prev + curr)/2;
            Double newLowerBound = lowerBound, newUpperBound = upperBound;
            if (prev < curr) {
                // -----prev ---------mid ----------curr
                if (isCloser[i]) {
                    newLowerBound = mid; // >= mid
                } else {
                    newUpperBound = mid; // <= mid
                }
            } else {
                // -----curr ---------mid ----------prev
                if (isCloser[i]) {
                    newUpperBound = mid; // >= mid
                } else {
                    newLowerBound = mid; // <= mid
                }
            }
            if (newLowerBound > lowerBound) {
                lowerBound = newLowerBound;
            }
            if (newUpperBound < upperBound) {
                upperBound = newUpperBound;
            }
        }
        out.println(lowerBound + " " + upperBound);
    }

    private double readDouble() {
        return Double.parseDouble(readString());
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
