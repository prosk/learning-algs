package com.mycompany.codeforces.goodbye2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BestImpressionist {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BestImpressionist().run();
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
        int[][] ranges = new int[n][2];
        TreeMap<Integer, Integer> points = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            int l = readInt();
            int r = readInt();
            ranges[i][0] = l;
            ranges[i][1] = r;
            if (l == r) {
                points.merge(l, 1, Integer::sum);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            boolean uniq = false;
            int l = ranges[i][0];
            int r = ranges[i][1];
            if (l == r) {
                int cnt = points.get(l);
                if (cnt == 1) uniq = true;
            } else {
                // l < r
                Integer minPoint = points.higherKey(l - 1);
                Integer maxPoint = points.lowerKey(r + 1);
                if (minPoint != null && maxPoint != null && minPoint.equals(l) && maxPoint.equals(r)) {
                    int rangeCnt = r - l + 1;
                    int pointsCnt = points.subMap(minPoint, maxPoint+1).keySet().size();
                    uniq = rangeCnt > pointsCnt;
                } else {
                    uniq = true;
                }
            }
            sb.append(uniq ? '1' : '0');
        }
        out.println(sb);
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