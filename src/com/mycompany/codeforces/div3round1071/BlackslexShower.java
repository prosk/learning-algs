package com.mycompany.codeforces.div3round1071;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BlackslexShower {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BlackslexShower().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
        // solution
        int ans = Math.max(Math.abs(arr[2] - arr[1]), Math.abs(arr[n] - arr[n-1]));
        int sum = Math.abs(arr[2] - arr[1]);
        for(int i = 2; i <= n-1; i++) {
            int diff = Math.abs(arr[i] - arr[i - 1]) +
                    Math.abs(arr[i + 1] - arr[i]) - Math.abs(arr[i + 1] - arr[i - 1]);
            ans = Math.max(ans, diff);
            sum += Math.abs(arr[i+1] - arr[i]);
        }
        ans = sum - ans;
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