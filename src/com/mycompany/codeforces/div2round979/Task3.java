// package com.mycompany.codeforces.div2round979;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Task3().run();
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
        String s = readString();
        String ans = (s.charAt(0) == '1' || s.charAt(n-1) == '1' || s.contains("11")) ? "YES" : "NO";
        out.println(ans);

        /*if (s.charAt(0) == '1' || s.charAt(n-1) == '1') {
            out.println("YES");
            return;
        }*/
        /*int pos = 0;
        int substr010cnt = 0;
        while (pos < n) {
            int res = s.indexOf("010", pos);
            if (res < 0) break;
            substr010cnt++;
            pos = res + 1;
        }

        int steps = n-1;
        int bobStepsCnt = 0;
        while (steps > 0) {
            steps--;
            if (steps > 0) {
                steps--;
                bobStepsCnt++;
            }
        }
        String ans = (bobStepsCnt >= substr010cnt) ? "NO" : "YES";
        out.println(ans);*/
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