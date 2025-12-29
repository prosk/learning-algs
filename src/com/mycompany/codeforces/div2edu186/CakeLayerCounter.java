package com.mycompany.codeforces.div2edu186;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CakeLayerCounter {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new CakeLayerCounter().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int a = readInt();
        int b = readInt();
        int firstSum = 0, secondSum = 0, ans = 0;
        int ind = 0, val = 1;
        while(true) {
            if (ind % 2 == 0) {
                firstSum += val;
            } else {
                secondSum += val;
            }
            if ((a >= firstSum && b >= secondSum) || (b >= firstSum && a >= secondSum)) {
                ans = ind + 1;
            } else {
                break;
            }
            ind++;
            val *= 2;
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