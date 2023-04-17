package com.mycompany.postupashki.algs2023.twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class StringCompressor {

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new StringCompressor().run();
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
        String inputStr = readString();
        StringBuilder res = new StringBuilder("");
        int len = inputStr.length();

        int ind = 0;
        while(ind < len) {
            char currChar = inputStr.charAt(ind);
            int cnt = 1, next = ind + 1;
            while(next < len && inputStr.charAt(next) == currChar) {
                cnt++;
                next++;
            }
            res.append(currChar);
            res.append(cnt);
            ind = next;
        }
        out.println(res);
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


