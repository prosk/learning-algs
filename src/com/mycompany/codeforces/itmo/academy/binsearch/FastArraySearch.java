package com.mycompany.codeforces.itmo.academy.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

// https://codeforces.com/edu/course/2/lesson/6/1/practice/contest/283911/problem/D
public class FastArraySearch {

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new FastArraySearch().run();
    }

    private void run() {
        try {
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int n = readInt();
        int[] a = readIntArray(n);
        int k = readInt();

        Arrays.sort(a);

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < k; i++) {
            int l = readInt();
            int r = readInt();
            int leftInd = leftBound(a, l);
            int rightInd = rightBound(a, r);
            int res = (leftInd < 0 || rightInd < 0) ? 0
                    : rightInd - leftInd + 1;
            if (i == 0) {
                sb.append(res);
            } else {
                sb.append(' ');
                sb.append(res);
            }
        }
        out.println(sb);
    }

    // минимальный индекс i, для которого a[i] >= left
    // если все a[i] < left, возвращает -1
    int leftBound(int[] a, int left) {
        int res = -1;
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a[mid] >= left) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    // максимальный индекс i, для которого a[i] <= right
    // если все a[i] больше right, вернет -1
    int rightBound(int[] a, int right) {
        int res = -1;
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a[mid] <= right) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
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

