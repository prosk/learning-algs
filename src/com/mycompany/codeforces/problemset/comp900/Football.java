// package com.mycompany.codeforces.problemset.comp900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Football {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Football().run();
        out.close();
    }

    void run() {
        String s = readString();
        char cur = s.charAt(0);
        int cnt = 1;
        boolean ans = false;
        for(int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == cur) {
                cnt++;
                if (cnt == 7) {
                    ans = true; break;
                }
            } else {
                cur = ch;
                cnt = 1;
            }
        }
        out.println(ans ? "YES" : "NO");
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