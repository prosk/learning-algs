// package com.mycompany.codeforces.problemset.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnluckyTicket {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new UnluckyTicket().run();
        out.close();
    }

    void run() {
        int n = readInt();
        String str = readString();

        int a[] = new int[n];
        int b[] = new int[n];
        for(int i = 0; i < n; i++) a[i] =  (int)str.charAt(i) - 48;
        for(int i = 0; i < n; i++) b[i] =  (int)str.charAt(n+i) - 48;

        Arrays.sort(a);
        Arrays.sort(b);

        int prevSign = Integer.compare(a[0], b[0]);
        String ans = "YES";
        for(int i = 0; i < n; i++) {
            int currSign = Integer.compare(a[i], b[i]);
            if (currSign == 0 || currSign != prevSign) {
                ans = "NO";
                break;
            }
            prevSign = currSign;
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