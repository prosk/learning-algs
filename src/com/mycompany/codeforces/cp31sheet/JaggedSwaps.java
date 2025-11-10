package com.mycompany.codeforces.cp31sheet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JaggedSwaps {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new JaggedSwaps().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = readInt();
            out.println(arr[0] == 1 ? "YES" : "NO");
        }
    }

    boolean isArraySorted(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while(!tok.hasMoreTokens()) {
            String line = readLine();
            if (line == null) return null;
            tok = new StringTokenizer(line);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
