//package com.mycompany.codeforces.div3round981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskCC {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TaskCC().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        for(int i = 0, j = n-1; i < j; i++, j--) {
            if(arr[i] == arr[j]) continue;
            if (i + 1 < j - 1 && (arr[i] == arr[i+1] || arr[j-1] == arr[j])) {
                if (i == 0 && j == n-1) {
                    swap(arr, i, j);
                } else {
                    if (arr[i-1] != arr[j] && arr[j+1] != arr[i]) {
                        swap(arr, i, j);
                    }
                }
            } else {
                if (i-1 > 0 && j+1 < n && (arr[i-1] == arr[i] || arr[j+1] == arr[j])) {
                    swap(arr, i, j);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n-1; i++) {
            if (arr[i] == arr[i+1]) ans++;
        }
        out.println(ans);
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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