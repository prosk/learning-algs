package com.mycompany.codeforces.div2round977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MexMaximumOpt {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MexMaximumOpt().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int x = readInt();
        int[] freq = new int[n];

        for(int i = 0; i < n; i++) {
            int elem = readInt();
            if (elem < n) {
                freq[elem]++;
            }
        }
        int ans = n;
        for(int i = 0; i < n; i++) {
            if (freq[i] == 0) {
                ans = i; break;
            } else if (freq[i] > 1 && (i + x) < n) {
                freq[i + x] += freq[i] - 1;
            }
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
