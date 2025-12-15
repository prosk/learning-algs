package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MakeItBeautiful {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MakeItBeautiful().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    /*
        Solution in Python
t = int(input())
for i in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    if a[0] == a[n - 1]:
        print('NO')
    else:
        print('YES')
        print(a[n - 1], end = ' ')
        print(*(a[0:n-1]))
     */

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();
        if (arr[0] == arr[n-1]) {
            out.println("NO");
        } else {
            out.println("YES");
            swap(arr, 0, n-2);
            StringBuffer sb = new StringBuffer();
            for(int j = n-1; j >= 0; j--) {
                sb.append(arr[j]);
                if (j > 0) sb.append(' ');
            }
            out.println(sb);
        }
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