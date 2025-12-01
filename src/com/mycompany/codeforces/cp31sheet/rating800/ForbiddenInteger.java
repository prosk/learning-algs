package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ForbiddenInteger {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ForbiddenInteger().run();
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
        int n = readInt();
        int k = readInt();
        int x = readInt();
        // solution
        String ans = "YES", sumStr = "";
        int m = 0;
        if (k == 1) {
            ans = "NO";
        } else if (k > 1 && x != 1) {
            m = n;
            sumStr = getRepeatingSum(1, n);
        } else {
            // k > 1 && x == 1
            if (k == 2) {
                ans = (n % 2 == 0) ? "YES" : "NO";
                if ("YES".equals(ans)) {
                    m = n / 2;
                    sumStr = getRepeatingSum(2, m);
                }
            } else {
                // k >= 3
                if ((n % 2) == 0) {
                    m = n / 2;
                    sumStr = getRepeatingSum(2, m);
                } else {
                    m = (n - 3) / 2 + 1;
                    sumStr = "3 " + getRepeatingSum(2, (n - 3)/2);
                }
            }
        }
        out.println(ans);
        if ("YES".equals(ans)) {
            out.println(m);
            out.println(sumStr);
        }
    }

    String getRepeatingSum(int num, int repeatCnt) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < repeatCnt; i++) {
            if (i > 0) sb.append(' ');
            sb.append(num);
        }
        return sb.toString();
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