// package com.mycompany.codeforces.div2eduround170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoScreens {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TwoScreens().run();
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
        String s = readString();
        String t = readString();
        StringBuilder pref = new StringBuilder();
        int sLen = s.length();
        int tLen = t.length();
        int minLen = Math.min(sLen, tLen);
        for(int i = 0; i < minLen; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                pref.append(s.charAt(i));
            } else {
                break;
            }
        }
        int prefLen = pref.length();
        int ans;
        if (prefLen == 0) {
            ans = sLen+tLen;
        } else {
            ans = prefLen + 1 + (sLen-prefLen) + (tLen-prefLen);
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