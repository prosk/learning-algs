package com.mycompany.codeforces.streams.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://codeforces.com/group/yeVhAfeK6s/contest/571840/problem/B
public class BestTeam {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BestTeam().run();
        out.close();
    }

    void run() {
        int q = readInt();
        StringBuilder sb = new StringBuilder();
        while(q-- > 0) {
            int c = readInt(); // coders
            int m = readInt(); // math
            int x = readInt(); // without specs
            int ans = getMaxBestTeams(c, m, x);
            sb.append(ans);
            sb.append('\n');
        }
        out.print(sb);
    }

    int getMaxBestTeams(int c, int m, int x) {
        int ans = 0;
        int studentsCnt = c + m + x;
        int l = 0, r = studentsCnt / 3;
        while(l <= r) {
            int mid = l + (r - l)/2;
            boolean isOk = isOk(c, m, x, mid);
            if (isOk) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    boolean isOk(int c, int m, int x, int teamsCnt) {
        boolean codersAndMathOk = (teamsCnt <= c && teamsCnt <= m);
        if (!codersAndMathOk) return false;
        int restPeople = (c - teamsCnt) + (m - teamsCnt) + x;
        return restPeople >= teamsCnt;
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