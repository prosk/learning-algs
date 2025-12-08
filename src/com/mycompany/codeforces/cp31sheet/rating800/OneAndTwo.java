package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OneAndTwo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new OneAndTwo().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    /*
    beautiful solution in Python

    for t in range(int(input())):
        n = int(input())
        a = input().split()
        s = [ i+1 for i in range(n) if a[i] == '2' ]
        if not s:
            print(1)
        elif len(s) % 2:
            print(-1)
        else:
            print(s[len(s)//2-1])

     */

    void solve() {
        int n = readInt();
        int[] arr = new int[n+1];
        int allTwoCnt = 0;
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            allTwoCnt += (arr[i] == 2) ? 1 : 0;
        }
        int leftTwoCnt = 0, ans = -1;
        for(int j = 1; j < n; j++) {
            leftTwoCnt += (arr[j] == 2) ? 1 : 0;
            if (leftTwoCnt == allTwoCnt - leftTwoCnt) {
                ans = j;
                break;
            }
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