package com.mycompany.yandex.training40.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SortedListMerging {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SortedListMerging().run();
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

    // merge sub-arrays a[startInd..midInd] and a[midInd+1, endInd]
    // startInd <= midInd < endInd
    private void merge(int[] a, int startInd, int midInd, int endInd) {
        int n1 = midInd - startInd + 1;
        int n2 = endInd - midInd;
        int[] left = new int[n1+1];
        int[] right = new int[n2+1];

        System.arraycopy(a, startInd, left, 0, n1);
        System.arraycopy(a, midInd+1, right, 0, n2);
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for(int k = startInd; k <= endInd; k++) {
            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
        }
    }

    private void solve() {
        int[] arr = new int[2_000_000];
        int n = readInt();
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        int m = readInt();
        for(int i = n; i < n+m; i++) {
            arr[i] = readInt();
        }

        merge(arr, 0, n-1, n+m-1);

        int len = n + m;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.append(arr[i]);
            if (i < len - 1) sb.append(' ');
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
