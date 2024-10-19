// package com.mycompany.codeforces.problemset.comp900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Twins {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Twins().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            sum += arr[i];
        }
        Arrays.sort(arr);
        int currSum = 0, pos = n;
        while(currSum <= (sum - currSum) && pos > 0) {
            pos--;
            currSum += arr[pos];
        }
        out.println(n-pos);
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