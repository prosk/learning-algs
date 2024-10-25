// package com.mycompany.codeforces.div2round980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskD {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TaskD().run();
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
        long[] aScores = new long[n+1];
        long[] bParams = new long[n+1];
        long[] aPref = new long[n+2];
        for(int i = 1; i <= n; i++) {
            aScores[i] = readInt();
            aPref[i+1] = aScores[i] + aPref[i];
        }
        for(int i = 1; i <= n; i++) {
            bParams[i] = readInt();
        }


        long ans = getMaxScores(1, 0, 0L,
            aScores, bParams, aPref);
        out.println(ans);
    }

    long getMaxScores(int curNum, int prevNum, long skippedSum,
                      long[] aScores, long[] bParams, long[] aPref) {
        // вариант сдать задачу  curNum и все предыдущие слева
        long submitScores = aPref[curNum+1] - skippedSum;

        long skipScores = 0;
        for(int num = curNum; num > prevNum; num--) {
            long bParam = bParams[num];
            if (bParam > curNum) {
                // есть вариант связанный с прыжком вперед с помощью пропуска задачи
                long numSkipScores = getMaxScores((int)bParam, curNum, skippedSum + aScores[num],
                    aScores, bParams, aPref);
                skipScores = Math.max(skipScores, numSkipScores);
            }
        }
        return Math.max(submitScores, skipScores);
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