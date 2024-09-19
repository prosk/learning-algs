// package com.mycompany.cses.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Permutations {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Permutations().run();
        out.close();
    }

    void run() {
        int n = readInt();
        if (n > 1 && n < 4) {
            out.println("NO SOLUTION");
            return;
        }
        // 4: 2 4 1 3
        for(int i = 2; i <= n; i+= 2) {
            if (i > 2) out.print(' ');
            out.print(i);
        }
        for(int i = 1; i <= n; i+= 2) {
            if (n > 1) out.print(' ');
            out.print(i);
        }
        out.print('\n');
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
