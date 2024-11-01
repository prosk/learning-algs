//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RoughtString {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new RoughtString().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long maxRought = readLong();
        String s = readString();

        int ans = 0;
        int right = 0;
        long rought = 0;
        long aCnt = s.charAt(0) == 'a' ? 1 : 0;
        long bCnt = s.charAt(0) == 'b' ? 1 : 0;
        // инвариант - значения rought, aCnt, bCnt поддерживаются корректными для отрезка [left, right]
        for(int left = 0; left < n; left++) {
            while(right < n && rought <= maxRought) {
                right++;
                if (right == n) break;
                char ch = s.charAt(right);
                if (ch == 'b') {
                    rought += aCnt;
                    bCnt++;
                } else if (ch == 'a') {
                    aCnt++;
                }
            }
            ans = Math.max(ans, right - left);
            if (s.charAt(left) == 'a') {
                rought -= bCnt;
                aCnt--;
            } else if (s.charAt(left) == 'b') {
                bCnt--;
            }
        }
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