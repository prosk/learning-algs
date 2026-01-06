package com.mycompany.codeforces.cp31sheet.rating900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ChessForkWithRecord {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ChessForkWithRecord().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    public record Pair(int x, int y) {}

    void solve() {
        int a = readInt();
        int b = readInt();
        int xKing = readInt();
        int yKing = readInt();
        int xQueen = readInt();
        int yQueen = readInt();
        // solution
        int[] xPos = new int[] {  1, -1, -1, 1 };
        int[] yPos = new int[] { -1, -1,  1, 1 };
        Set<Pair> kingPos = new HashSet<>(), queenPos = new HashSet<>();
        for(int i = 0; i < 4; i++) {
            // a, b
            kingPos.add(new Pair(xKing + xPos[i]*a, yKing + yPos[i]*b));
            queenPos.add(new Pair(xQueen + xPos[i]*a, yQueen + yPos[i]*b));
            // b, a
            kingPos.add(new Pair(xKing + xPos[i]*b, yKing + yPos[i]*a));
            queenPos.add(new Pair(xQueen + xPos[i]*b, yQueen + yPos[i]*a));
        }
        kingPos.retainAll(queenPos);
        out.println(kingPos.size());
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