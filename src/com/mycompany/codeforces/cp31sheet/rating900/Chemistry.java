package com.mycompany.codeforces.cp31sheet.rating900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Chemistry {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Chemistry().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int n = readInt();
        int k = readInt();
        String s = readString();
        // solution
        int[] freq = new int[26];
        for(char ch: s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int oddCnt = 0;
        for(int i = 0; i < 26; i++) {
            oddCnt += (freq[i] % 2 == 0) ? 0 : 1;
        }
        String ans = (k < oddCnt - 1) ? "NO" : "YES";
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