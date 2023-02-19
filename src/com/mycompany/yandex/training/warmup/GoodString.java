package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class GoodString {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new GoodString().run();
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
        int n = readInt();
        int[] symbolsCnt = readIntArray(n);

        long maxGoodness = 0;
        while(true) {
            // 1 проход, берем отрезки с ненулевой суммой и длиной > 1
            // внтури отрезка можем уменьшать сразу на минимум из всех эдементов отрезка
            boolean begin = false;
            long prevGoodnes = maxGoodness;
            int beginInd = 0, endInd = 0;
            int minCnt = Integer.MAX_VALUE;

            for(int i = 0; i < n; i++) {
                if (symbolsCnt[i] > 0) {
                    if (!begin && i < (n-1) && symbolsCnt[i+1] > 0) {
                        begin = true;
                        beginInd = i;
                        minCnt = Math.min(minCnt, symbolsCnt[i]);
                    } else {
                        if (begin) {
                            minCnt = Math.min(minCnt, symbolsCnt[i]);
                        }
                    }
                } else {
                    if (begin) {
                        begin = false;
                        endInd = i - 1;
                        maxGoodness += goodnessDiff(symbolsCnt, beginInd, endInd, minCnt);
                        minCnt = Integer.MAX_VALUE;
                    }
                }
            }
            if (begin) {
                endInd = n - 1;
                maxGoodness += goodnessDiff(symbolsCnt, beginInd, endInd, minCnt);
            }

            if (maxGoodness == prevGoodnes) {
                break;
            }
        }
        out.println(maxGoodness);
    }

    private long goodnessDiff(int[] symbolsCnt, int beginIndex, int endIndex, int minCnt) {
        for (int i = beginIndex; i <= endIndex; i++) {
            symbolsCnt[i] -= minCnt;
        }
        return (endIndex - beginIndex) * (long) minCnt;
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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
