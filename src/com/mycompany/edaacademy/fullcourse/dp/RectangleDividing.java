package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class RectangleDividing {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new RectangleDividing().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            optSolve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    int[][] predAnswers = new int[501][501];

    // Переборная стратегия - рекурсия с мемоизацией
    private int findAns(int a, int b) {
        if (a == b) {
            return 0;
        }
        int minAB = Math.min(a, b);
        int maxAB = Math.max(a, b);
        if (predAnswers[minAB][maxAB] > 0) {
            return predAnswers[minAB][maxAB];
        }
        int minStepCnt = Integer.MAX_VALUE;
        for(int i = 1; i <= minAB/2; i++) {
            int cnt1 = findAns(i, maxAB);
            int cnt2 = findAns(minAB-i, maxAB);
            int allCnt = 1 + cnt1 + cnt2;
            minStepCnt = Math.min(minStepCnt, allCnt);
        }
        for(int i = 1; i <= maxAB/2; i++) {
            int cnt1 = findAns(i, minAB);
            int cnt2 = findAns(maxAB-i, minAB);
            int allCnt = 1 + cnt1 + cnt2;
            minStepCnt = Math.min(minStepCnt, allCnt);
        }
        predAnswers[minAB][maxAB] = minStepCnt;
        return minStepCnt;
    }

    private void optSolve() {
        int a = readInt();
        int b = readInt();
        int ans = findAns(a, b);
        out.println(ans);
    }

    // Жадная стратегия - брать бОльшую сторону всегда - не работает
    // Легко найти контрпример на прямоугольнике 5*6
    // Жадная стратегия дает 5 ходов, оптимальная 4 (два квадрата 3*3 и 3 квадрата 2*2)
    private int getAns(int a, int b, int stepCnt) {
        if (a == b) {
            return stepCnt;
        }
        if (a < b) {
            return getAns(b - a, a, stepCnt+1);
        } else {
            return getAns(a - b, b, stepCnt+1);
        }
    }

    private void solve() {
        int a = readInt();
        int b = readInt();

        /*int ans = getAns(a, b, 0);
        out.println(ans);*/

        int n = a*b;
        int maxD = Math.min(a, b);

        // dp[i] = минимальное кол-во квадратов целых чисел,
        // таких что каждое число, возводимое в квадрат, не больше maxD,
        // сумма которых равна i
        int dp[] = new int[n+3];

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i<= n; i++) {
            int minPrev = Integer.MAX_VALUE;
            int j = 1;
            int jSquare = 1;
            int diff = i - jSquare;
            while(diff >= 0 && j <= maxD) {
                if (dp[i - jSquare] < minPrev) {
                    minPrev = dp[i - jSquare];
                }
                if (diff == 0) break;
                j++;
                jSquare = j*j;
                diff = i - jSquare;
            }
            dp[i] = minPrev+1;
            out.println("dp[" + i + "] = " + dp[i]);
        }
        out.println(dp[n] - 1); // ходов на 1 меньше, чем слагаемых в сумме (=квадратов)
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
