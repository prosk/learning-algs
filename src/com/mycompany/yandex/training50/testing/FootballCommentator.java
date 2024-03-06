package com.mycompany.yandex.training50.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FootballCommentator {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new FootballCommentator().run();
        out.close();
    }

    void run() {
        String game1 = readString();
        String game2 = readString();
        int homeFlag = readInt();

        int[] g1 = Arrays.stream(game1.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] g2 = Arrays.stream(game2.split(":")).mapToInt(Integer::parseInt).toArray();
        boolean isFirstHome = homeFlag == 1;

        int goals1 = g1[0] + g2[0];
        int goals2 = g1[1] + g2[1];
        int ans = 0;
        if (goals1 <= goals2) {
            ans += (goals2 - goals1);
            int goals11 = g1[0], goals12 = g2[0] + ans;
            if (isFirstHome) {
                ans += (goals12 > g1[1]) ? 0 : 1;
            } else {
                ans += (goals11 > g2[1]) ? 0 : 1;
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
