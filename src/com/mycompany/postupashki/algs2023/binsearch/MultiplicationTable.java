package com.mycompany.postupashki.algs2023.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

// https://codeforces.com/edu/course/2/lesson/6/5/practice/contest/285084/problem/B
public class MultiplicationTable {

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MultiplicationTable().run();
    }

    private void run() {
        try {
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        long n = readLong();
        long k = readLong();

        long l = 1;
        long r = n*n;
        long ans = 0;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (pairsCnt(mid, n) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        out.println(ans);
    }

    private long pairsCnt(long mult, long n) {
        long res = 0;
        long j = n;

        for(long i = 1; i <= n; i++) {
            while(j >= 1 && mult < i*j) {
                j--;
            }
            res += j;
        }
        return res;
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
