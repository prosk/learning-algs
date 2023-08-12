package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SymmetricSequence {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SymmetricSequence().run();
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
        int n = readInt();
        int[] arr = readIntArray(n);
        int len = arr.length;

        int addedCnt = len-1;
        boolean isPolindrome = false;
        for(int startPos = 0; startPos < len-1; startPos++) {
            isPolindrome = checkPalindrome(arr, len, startPos);
            if (isPolindrome) {
                addedCnt = startPos;
                break;
            }
        }

        out.println(addedCnt);
        for(int i = addedCnt-1; i >= 0; i--) {
            out.print(arr[i]);
            if (i > 0) { out.print(" "); } else { out.println(); }
        }
    }

    private boolean checkPalindrome(int[] arr, int len, int startPos) {
        boolean res = true;
        for(int i = startPos, j = len-1; i < j; i++, j--) {
            if (arr[i]  != arr[j]) return false;
        }
        return res;
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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

