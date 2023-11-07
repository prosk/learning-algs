package com.mycompany.yandex.training40.sort;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class RadixSort {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PrintWriter out = new PrintWriter(System.out);
    private StringTokenizer tok = new StringTokenizer("");

    private final Random rand = new Random();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new RadixSort().run();
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

    private void sortByDigit(String a[], String[] sorted, int digitPos) {
        int[] count = new int[10];
        int[] pos = new int[10];
        /*for(int i = 0; i < a.length; i++)
            count[a[i].charAt(digitPos)]*/
    }

    private void sort(String[] a) {
        int len = a[0].length();
        String[] b = new String[a.length];
        boolean changeArrays = false;
        for(int i = len-1; i >= 0; i--)
            sortByDigit(changeArrays ? b : a, changeArrays ? a : b, i);
            changeArrays = !changeArrays;
    }

    private void solve() {
        int n = readInt();

        String[] arr = new String[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readString();
        }

        sort(arr);
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
