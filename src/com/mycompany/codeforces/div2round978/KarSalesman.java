// package com.mycompany.codeforces.div2round978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KarSalesman {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new KarSalesman().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        // WRONG ANSWER - верное решение это KarSalesmanOpt
        int n = readInt();
        int x = readInt();
        long[] arr = new long[n];
        long sum = 0, arrMax = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            arrMax = Math.max(arrMax, arr[i]);
            sum += arr[i];
        }
        // edge cases
        if (x == 1) {
            out.println(sum);
            out.flush();
            return;
        }
        if (x >= n) {
            out.println(arrMax);
            out.flush();
            return;
        }
        // x > 1, x < n
        Arrays.sort(arr);
        long ans = 0;
        int leftInd = n - x;
        while(true) {
            long cMin = 2_000_000_001L, cMax = 0;
            for(int i = leftInd; i < n; i++) {
                if (arr[i] > 0) {
                    cMin = Math.min(cMin, arr[i]);
                    cMax = Math.max(cMax, arr[i]);
                }
            }
            if (leftInd == 0) {
                ans += cMax;
                break;
            }
            ans += cMin;
            int zeroCnt = 0;
            for(int i = leftInd; i < n; i++) {
                if (arr[i] > 0) {
                    arr[i] -= cMin;
                    if (arr[i] == 0) {
                        zeroCnt++;
                    }
                }
            }
            leftInd -= zeroCnt;
            if (leftInd < 0) {
                leftInd = 0;
            }
        }
        out.println(ans);
        out.flush();
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