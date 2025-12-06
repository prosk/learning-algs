package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UnitArray {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new UnitArray().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int onesCnt = 0, minusOnesCnt = 0;
            for(int i = 0; i < n; i++) {
                int elem = readInt();
                onesCnt += (elem == 1) ? 1 : 0;
                minusOnesCnt += (elem == -1) ? 1 : 0;
            }
            if (minusOnesCnt == 0) {
                out.println("0");
            } else {
                int ans = 0;
                if (minusOnesCnt % 2 == 1) {
                    ans++;
                    minusOnesCnt--;
                    onesCnt++;
                }
                if (minusOnesCnt > onesCnt) {
                    int ops = (minusOnesCnt - onesCnt + 1) / 2;
                    ans += ops;
                    minusOnesCnt -= ops;
                    if (minusOnesCnt % 2 == 1) {
                        ans++;
                    }
                }
                out.println(ans);
            }
        }
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while(!tok.hasMoreTokens()) {
            String line = readLine();
            if (line == null) return null;
            tok = new StringTokenizer(line);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}