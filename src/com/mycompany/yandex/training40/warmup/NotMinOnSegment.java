package com.mycompany.yandex.training40.warmup;

import com.mycompany.edaacademy.fullcourse.graphs.Connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class NotMinOnSegment {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new NotMinOnSegment().run();
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
        int m = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        for(int i = 1; i <= m; i++) {
            int l = readInt();
            int r = readInt();
            String ans = getNotMin(l, r, arr);
            out.println(ans);
        }
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
