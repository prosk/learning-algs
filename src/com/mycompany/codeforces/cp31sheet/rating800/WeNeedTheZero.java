package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WeNeedTheZero {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new WeNeedTheZero().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            optSolve();
        }
    }

    void optSolve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();
        // solution
        int xorSum = 0;
        for(int j = 0; j < n; j++) {
            xorSum ^= arr[j];
        }
        if (xorSum == 0) {
            out.println("0");
        } else {
            int x = (n % 2 == 1) ? xorSum : -1;
            out.println(x);
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();
        int x = 0;
        // цикл по битам от 0 до 7
        for(int bit = 0; bit < 8; bit++) {
            // считаем кол-во единиц в данном бите
            int mask = 1 << bit, onesCnt = 0;
            for(int j = 0; j < n; j++) {
                if ((arr[j] & mask) > 0) onesCnt++;
            }
            int zeroCnt = n - onesCnt;
            if (onesCnt % 2 == 1 && zeroCnt % 2 == 1) {
                x = -1; break;
            }
            x += (zeroCnt % 2 == 0 && onesCnt > 0) ? mask : 0;
        }
        out.println(x);
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