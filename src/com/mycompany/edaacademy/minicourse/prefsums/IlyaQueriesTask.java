package com.mycompany.edaacademy.minicourse.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class IlyaQueriesTask {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new IlyaQueriesTask().solve();
        out.close();
    }

    private void solve() {
        // data input
        String str = readString();
        int len = str.length();
        int queriesCnt = readInt();
        int[][] queries = new int[queriesCnt][2];
        for(int i = 0; i < queriesCnt; i++) {
            queries[i][0] = readInt()-1;
            queries[i][1] = readInt()-1;
        }
        // prefix sum calculation
        int[] pref = new int[len+1];
        pref[0] = 0;
        for(int i = 1; i <= len; i++) {
            int currCharCnt = (i < len && str.charAt(i-1) == str.charAt(i)) ? 1 : 0;
            pref[i] = pref[i-1] + currCharCnt;
        }
        // result calculation
        int currAns;
        for(int i = 0; i < queriesCnt; i++) {
            currAns = pref[queries[i][1]] - pref[queries[i][0]];
            out.println(currAns);
        }
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
