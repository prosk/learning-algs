package com.mycompany.yandex.training30.dp.twoparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class LcsWithAnswer {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new LcsWithAnswer().run();
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
        // Ввод данных
        int firstLen = readInt();
        int[] firstSeqNums = new int[firstLen+1];
        for(int i = 1; i <= firstLen; i++) {
            firstSeqNums[i] = readInt();
        }

        int secondLen = readInt();
        int[] secondSeqNums = new int[secondLen+1];
        for(int i = 1; i <= secondLen; i++) {
            secondSeqNums[i] = readInt();
        }

        int[][] dp = new int[firstLen+1][secondLen+1];

        for(int i = 1; i <= firstLen; i++) {
            for(int j = 1; j <= secondLen; j++) {
                // переход по динамике
                if (firstSeqNums[i] == secondSeqNums[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        int lcdNums[] = new int[Math.min(firstLen, secondLen)];
        int lcdLen = 0;
        int currI = firstLen, currJ = secondLen;
        while(dp[currI][currJ] > 0) {
            if (dp[currI][currJ] == dp[currI][currJ-1]) {
                currJ--;
            } else if (dp[currI][currJ] == dp[currI-1][currJ]) {
                currI--;
            } else {
                lcdNums[lcdLen] = firstSeqNums[currI];
                lcdLen++;
                currI--;
                currJ--;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(int i = (lcdLen-1); i >= 0; i--) {
            if (i == lcdLen-1) {
                sb.append(lcdNums[i]);
            } else {
                sb.append(' ');
                sb.append(lcdNums[i]);
            }
        }
        if (lcdLen > 0) {
            out.println(sb.toString());
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
