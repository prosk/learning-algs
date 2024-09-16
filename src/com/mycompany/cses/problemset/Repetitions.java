// package com.mycompany.cses.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Repetitions {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Repetitions().simpleRun();
        out.close();
    }

    void simpleRun() {
        // s.length() > = 1 !!!!
        String s = readString();
        int ans = 1; // one symbol is always exist
        int cnt = 0;
        char cur = 'A'; // a string consisting of characters A, C, G, and T.
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == cur) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cur = ch;
                cnt = 1;
            }
        }
        out.println(ans);
    }

    void run() {
        String s = readString();
        int ans = 0;
        int pos = 0;
        while(pos < s.length()) {
            char cur = s.charAt(pos);
            int startPos = pos;
            while(pos + 1 < s.length() && s.charAt(pos + 1) == cur) {
                pos++;
            }
            int cnt = pos - startPos + 1;
            ans = Math.max(ans, cnt);
            pos++;
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
