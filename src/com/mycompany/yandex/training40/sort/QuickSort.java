package com.mycompany.yandex.training40.sort;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class QuickSort {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PrintWriter out = new PrintWriter(System.out);
    private StringTokenizer tok = new StringTokenizer("");

    private final Random rand = new Random();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new QuickSort().run();
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

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private Point partition(int[] a, int startInd, int endInd) {
        int lt = startInd, i = startInd+1, gt = endInd;
        int pivotInd = startInd + rand.nextInt(endInd-startInd+1);
        int pivot = a[pivotInd];
        swap(a, startInd, pivotInd);
        while(i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
        // a[startInd..lt-1] < pivot = a[lt..gt] < a[gt+1..hi]
        return new Point(lt, gt);
    }

    private void sort(int[] a, int startInd, int endInd) {
        if (startInd < endInd) {
            Point pivot = partition(a, startInd, endInd);
            sort(a, startInd, pivot.x-1);
            sort(a, pivot.y+1, endInd);
        }
    }

    private void solve() {
        int n = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        sort(arr, 0, arr.length-1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(' ');
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