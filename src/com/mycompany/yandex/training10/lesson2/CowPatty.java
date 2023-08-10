package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class CowPatty {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CowPatty().run();
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

        int maxLength = 0;
        int maxLengthInd = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > maxLength) {
                maxLength = arr[i];
                maxLengthInd = i;
            }
        }

        int maxLengthForVasya = 0;
        for(int i = maxLengthInd+1; i < arr.length-1; i++) {
            if ( (arr[i] % 10) == 5 && arr[i] > arr[i+1]) {
                maxLengthForVasya = Math.max(maxLengthForVasya, arr[i]);
            }
        }
        if (maxLengthForVasya == 0) {
            out.println("0");
            return;
        }

        int betterPartCnt = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] > maxLengthForVasya) {
                betterPartCnt++;
            }
        }
        out.println(betterPartCnt+1);
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
