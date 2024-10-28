// package com.mycompany.codeforces.itmo.academy.twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberOfSmaller {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new NumberOfSmaller().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int m = readInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = readInt();

        int[] b = new int[m];
        for(int j = 0; j < m; j++) b[j] = readInt();

        int[] res = new int[m];

        int i = 0;
        for(int j = 0; j < m; j++) {
            while (i < n && a[i] < b[j]) i++;
            res[j] = i;
        }

        for(int j = 0; j < m; j++) {
            out.print(res[j] + " ");
        }
        out.println();
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
