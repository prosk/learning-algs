package com.mycompany.codeforces.itmo.academy.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
5 10
2 4 4 6 8
1 3 5 7 9

3 5 5 5 7 7 7 7 9 9 9 9 9

ans = 9
kAns = 4 + 3 + 3 + 2 + 1 = 13 элементов которые <= 9
kAns >= k => запоминаем значение ans как итоговый ответ

ans = 8
kAns = 8 элементов которые <= 8
kAns < k => берем ans больше

итоговый ответ равен минимальному значению ans при котором kAns >= k
 */

public class KSum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new KSum().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long k = readLong();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0; i < n; i++) a[i] = readInt();
        for(int i = 0; i < n; i++) b[i] = readInt();

        Arrays.sort(a);
        Arrays.sort(b);
        int l = a[0] + b[0], r = a[n-1] + b[n-1], ans = 0;
        while(l <= r) {
            int mid = l + (r - l)/2;
            long kCnt = getLessOrEqualCnt(a, b, n, mid);
            if (kCnt >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        out.println(ans);
    }

    long getLessOrEqualCnt(int[] a, int[] b, int n, int target) {
        int i = 0, j = n - 1;
        long cnt = 0;
        for(int step = 1; step <= 2*n; step++) {
            int sum = a[i] + b[j];
            if (sum > target) {
                if (j > 0) j--; else break;
            } else {
                cnt += j + 1;
                if (i < n-1) i++; else break;
            }
        }
        return cnt;
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    long readLong() {
        return Long.parseLong(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}