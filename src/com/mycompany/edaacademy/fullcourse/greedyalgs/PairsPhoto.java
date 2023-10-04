package com.mycompany.edaacademy.fullcourse.greedyalgs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class PairsPhoto {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PairsPhoto().solve();
        out.close();
    }

    private void solve() {
        int pairsCnt = readInt();
        int peopleCnt = 2*pairsCnt;
        int[] arr = new int[peopleCnt];
        boolean[] erased = new boolean[peopleCnt];
        for(int i = 0; i < peopleCnt; i++) {
            arr[i] = readInt();
            erased[i] = false;
        }

        int ans = 0;
        for(int i = 0; i < peopleCnt; i++) {
            if (!erased[i]) {
                int dist = getDist(i, arr[i], arr, erased);
                ans += dist;
            }
        }
        out.println(ans);
    }

    private int getDist(int ind, int value, int[] arr, boolean[] erased) {
        int dist = 0;
        erased[ind] = true;
        for(int i = ind+1; i < arr.length; i++) {
            if (arr[i] == value) {
                erased[i] = true;
                return dist;
            }
            dist += (erased[i] ? 0 : 1);
        }
        return dist;
    }

    private int readInt() {
        return Integer.parseInt(readString());
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
