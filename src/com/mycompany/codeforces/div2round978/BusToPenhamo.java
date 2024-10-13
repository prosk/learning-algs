// package com.mycompany.codeforces.div2round978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BusToPenhamo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BusToPenhamo().run();
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
        int r = readInt();
        int[] arr = new int[n];
        int ans = 0;
        int freeRest = 2*r;
        int toSeatCnt = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            if ((arr[i] % 2) == 0) {
                ans += arr[i];
                freeRest -= arr[i];
            } else {
                int seated = arr[i] - 1;
                ans += seated;
                freeRest -= seated;
                toSeatCnt++;
            }
        }
        int rCnt = freeRest/2;
        int restAfterByOne = toSeatCnt - rCnt;
        if (restAfterByOne <= 0) {
            ans += toSeatCnt;
        } else {
            ans += (rCnt - restAfterByOne);
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