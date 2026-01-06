package com.mycompany.codeforces.cp31sheet.rating900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VasilijeInCacak {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new VasilijeInCacak().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        long n = readLong();
        long k = readLong();
        long x = readLong();
        // solution
        long minSum = k*(k+1)/2;
        long maxSum;
        if (k == n) {
            maxSum = minSum;
        } else {
            long allSum = n*(n+1)/2;
            long m = n - k;
            long mSum = m*(m+1)/2;
            maxSum = allSum - mSum;
        }
        String ans = (x >= minSum && x <= maxSum) ? "YES" : "NO";
        out.println(ans);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    long readLong() {
        return Long.parseLong(readString());
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