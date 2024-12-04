package com.mycompany.coderun.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://coderun.yandex.ru/selections/eserajim/problems/sweets-wanted/description
public class CarlsonCandies {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new CarlsonCandies().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long[] arr = new long[n];
        long sum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            sum += arr[i];
        }
        if (n <= 2) {
            out.println("0");
            return;
        }
        Arrays.sort(arr);
        long leftSum = arr[0], rightVal = arr[n-1];
        long ans = Long.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            long leftVal = arr[i-1];
            long rightSum = sum - leftSum;
            long cnt = (leftVal*i - leftSum) + (rightVal*(n - i) - rightSum);
            ans = Math.min(ans, cnt);
            leftSum += arr[i];
        }
        out.println(ans);
    }

    int readInt() {
        return Integer.parseInt(readString());
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