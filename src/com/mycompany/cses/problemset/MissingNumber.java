// package com.mycompany.cses.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MissingNumber {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MissingNumber().runOpt();
        out.close();
    }

    void runOpt() {
        long n = readInt();
        long sum = 0;
        for(int i = 1; i < n; i++) {
            int cur = readInt();
            sum += cur;
        }
        long res = n * (n+1) / 2 - sum;
        out.println(res);
    }

    void run() {
        int n = readInt();
        Set<Integer> nums = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            nums.add(i);
        }
        for(int i = 1; i < n; i++) {
            int cur = readInt();
            nums.remove(cur);
        }
        out.println(nums.iterator().next());
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
