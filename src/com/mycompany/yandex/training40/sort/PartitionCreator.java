package com.mycompany.yandex.training40.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class PartitionCreator {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PartitionCreator().run();
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

    private int partition(int[] a, int startInd, int endInd, int pivotElem) {
        int swapInd = startInd;
        for(int i = startInd; i <= endInd; i++) {
            if (a[i] < pivotElem) {
                swap(a, i, swapInd);
                swapInd++;
            }
        }
        return swapInd;
    }

    private void solve() {
        int n = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        int pivotElem = readInt();

        int lessCnt = partition(arr, 0, arr.length-1, pivotElem);

        out.println(lessCnt);
        out.println(arr.length - lessCnt);
    }

    private String getNotMin(int l, int r, int[] arr) {
        int min = arr[l], max = min;
        for(int i = l; i <= r; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        return (min == max) ? "NOT FOUND" : String.valueOf(max);
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
