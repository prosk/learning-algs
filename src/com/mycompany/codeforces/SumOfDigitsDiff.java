package com.mycompany.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

// https://codeforces.com/contest/1788/problem/B
public class SumOfDigitsDiff {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    final int[] powersOfTen = {1, 10, 100, 1000, 10_000, 100_000, 1000_000, 10_000_000, 100_000_000, 1_000_000_000};

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SumOfDigitsDiff().run();
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
        int inpSetCnt = readInt();
        for(int i = 0; i < inpSetCnt; i++) {
            int n = readInt();
            printResult(n);
        }
    }

    private void printResult(int n) {
        int digits[] = new int[10];
        int addedDigits[] = new int[10];
        int i = 0, currN = n, digitSum = 0;
        while(currN > 0) {
            digits[i] = currN % 10;
            digitSum += digits[i];
            currN = currN / 10;
            i++;
        }
        int digitCount = i;

        int currDelta = digitSum; // дельта по сумме цифр с числом 0

        if (currDelta <= 1) {
            out.printf("%d %d\n", n, 0);
            return;
        }

        while (currDelta > 1) {
            int diff = currDelta / 2;
            // Ищем первый разряд, который больше нуля
            int nonZeroDigitIndex = 0;
            while (digits[nonZeroDigitIndex] == 0) {
                nonZeroDigitIndex++;
            }
            int realDiff = digits[nonZeroDigitIndex] >= diff ? diff : digits[nonZeroDigitIndex];
            digits[nonZeroDigitIndex] -= realDiff;
            addedDigits[nonZeroDigitIndex] += realDiff;
            currDelta = currDelta - realDiff*2;
        }
        int s1 = 0, s2 = 0;
        for(int j = 0; j < digitCount; j++) {
            s1 = s1 + powersOfTen[j] * digits[j];
            s2 = s2 + powersOfTen[j] * addedDigits[j];
        }
        out.printf("%d %d\n", s1, s2);
    }

    private int[] readIntArray(int arrayLength) {
        int res[] = new int[arrayLength];
        for(int j = 0; j < arrayLength; j++) {
            res[j] = readInt();
        }
        return res;
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

