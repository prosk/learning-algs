package com.mycompany.codeforces.div2round1108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
  equal Python solution

for _ in range(int(input())):
  n = int(input())
  if n == 1:
    print(1)
  elif n == 2:
    print(-1)
  else:
    print(1)
    print(2)
    for exp in range(n - 2):
      print(3 * 2 ** exp)
 */
public class Task2_EzraftAndArray {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Task2_EzraftAndArray().run();
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
        if (n == 1) {
            out.println("1");
        } else if (n == 2) {
            out.println("-1");
        } else {
            // n >= 3 && n <= 50
            out.print("1 2 3");
            long curr = 6;
            for (int i = 4; i <= n; i++) {
                out.print(" " + curr);
                curr *= 2;
            }
            out.println();
        }
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