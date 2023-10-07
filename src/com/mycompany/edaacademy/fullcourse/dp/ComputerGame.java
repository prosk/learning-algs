package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class ComputerGame {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ComputerGame().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int platformsCnt = readInt();
        int[] platformHeights = new int[platformsCnt+1];

        for(int i = 1; i <= platformsCnt; i++)
            platformHeights[i] = readInt();

        // dp[i] = минимальное количество энергии, достаточное, чтобы добраться с 1-й платформы до i-й
        int dp[] = new int[platformsCnt+1];

        dp[1] = 0;
        dp[2] = Math.abs(platformHeights[2] - platformHeights[1]);

        int prev[] = new int[platformsCnt+1];
        prev[2] = 1;
        prev[1] = 0;
        for(int i = 3; i<= platformsCnt; i++) {
            int minusTwo = dp[i-2] + 3*Math.abs(platformHeights[i] - platformHeights[i-2]);
            int minusOne = dp[i-1] + Math.abs(platformHeights[i] - platformHeights[i-1]);

            if (minusTwo < minusOne) {
                dp[i] = minusTwo;
                prev[i] = i-2;
            } else {
                dp[i] = minusOne;
                prev[i] = i-1;
            }
        }

        List<Integer> pathPlatforms = new ArrayList<>();
        int cur = platformsCnt;
        pathPlatforms.add(cur);
        while(prev[cur] > 0) {
            cur = prev[cur];
            pathPlatforms.add(cur);
        }

        out.println(dp[platformsCnt]);
        out.println(pathPlatforms.size());
        StringBuilder sb = new StringBuilder();
        for(int i = pathPlatforms.size()-1; i >= 0; i--) {
            sb.append(pathPlatforms.get(i));
            if (i > 0) sb.append(' ');
        }
        out.println(sb);
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
