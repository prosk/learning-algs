package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DontTryToCount {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new DontTryToCount().run();
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
        int m = readInt();
        String x = readString();
        String s = readString();

        // Solution
        int maxRepeatsCnt = m / n + 2, opsCnt = 0, ans = -1, repeatsCnt = 1;
        String curr = x;

        while(true) {
            if (curr.contains(s)) {
                ans = opsCnt;
                break;
            }
            if (repeatsCnt >= maxRepeatsCnt) break;
            curr = String.format("%s%s", curr, curr);
            repeatsCnt = 2*repeatsCnt;
            opsCnt++;
        }
        out.println(ans);
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
