package com.mycompany.codeforces.div3round1071;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BlackslexAndPenguinCivilization {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BlackslexAndPenguinCivilization().run();
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
        // solution
        int size = 1 << n;
        List<Integer> ansList = new ArrayList<>();
        ansList.add(size - 1);
        int upper = 2; // upper = 2 ^ k
        int lowerOnes = size / 2; // lowerOnes = 2 ^ (n-k)
        for(int k = 1; k <= n; k++) {
            for(int elem = 0; elem < upper; elem +=2) {
                ansList.add(lowerOnes - 1 + (elem << (n-k)));
            }
            lowerOnes /= 2;
            upper *= 2;
        }

        // output
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < ansList.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(ansList.get(i));
        }
        out.println(sb);
        out.flush();
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