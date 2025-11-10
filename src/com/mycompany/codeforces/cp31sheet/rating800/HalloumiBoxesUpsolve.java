package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HalloumiBoxesUpsolve {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new HalloumiBoxesUpsolve().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int k = readInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = readInt();
            if (k > 1) {
                out.println("YES");
            } else {
                out.println(isArraySorted(arr) ? "YES" : "NO");
            }
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
