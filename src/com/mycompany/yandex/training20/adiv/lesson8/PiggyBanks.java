package com.mycompany.yandex.training20.adiv.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PiggyBanks {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new PiggyBanks().run();
        out.close();
    }

    void run() {
        int N = readInt();
        int[] where = new int[N+1];
        for (int i = 1; i <= N; i++) {
            where[i] = readInt();
        }
        int ans = 0;
        int[] mark = new int[N+1];
        for(int i = 1; i <= N; i++) {
            if (mark[i] == 0) {
                int cur = i;
                while(mark[cur] == 0) {
                    mark[cur] = i;
                    cur = where[cur];
                }
                if (mark[cur] == i) {
                    ans++;
                }
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