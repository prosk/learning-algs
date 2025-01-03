package com.mycompany.codeforces.div2eduround173;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class DigitFactorialTimesNumber {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new DigitFactorialTimesNumber().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    int fact(int n) {
        int res = 1;
        for(int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    void solve() {
        int n = readInt();
        int d = readInt();
        boolean[] ans = new boolean[10];
        ans[1] = true;
        ans[5] = (d == 5);
        List<Integer> calcNums = Arrays.asList(3, 7, 9);
        if (n >= 7) {
            for(int i: calcNums) ans[i] = true;
        } else {
            String numStr = String.valueOf(d).repeat(fact(n));
            BigInteger num = new BigInteger(numStr);
            for(int i: calcNums) {
                BigInteger[] qr = num.divideAndRemainder(BigInteger.valueOf(i));
                ans[i] = qr[1].equals(BigInteger.ZERO);
            }
        }
        // print answer
        for(int i = 1; i <= 9; i += 2) {
            if (ans[i]) out.print(i + " ");
        }
        out.println();
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