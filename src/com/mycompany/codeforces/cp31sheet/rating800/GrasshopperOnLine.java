package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GrasshopperOnLine {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new GrasshopperOnLine().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    /*
    More simple solution:
    1) When x is not divisible by k, the grasshopper can reach x in just one jump.

    2) Otherwise, you can show that two jumps are always enough. For example, jumps 1 and x−1.
    1 is not divisible by any k>1. Also, x and x−1 can't be divisible by any k>1 at the same time.

    for _ in range(int(input())):
	x, k = map(int, input().split())
	if x % k != 0:
		print(1)
		print(x)
	else:
		print(2)
		print(1, x - 1)
     */

    void solve() {
        int x = readInt();
        int k = readInt();
        // solution
        if (x % k != 0) {
            out.println("1");
            out.println(x);
            return;
        }
        out.println("2");
        int first, second;
        if (x == k) {
            first = k + 1;
            second = -1;
        } else {
            // x > k
            first = (x/k - 1)*k + 1;
            second = x - first;
        }
        out.println(first + " " + second);
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