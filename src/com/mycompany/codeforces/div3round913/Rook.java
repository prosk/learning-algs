package com.mycompany.codeforces.div3round913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Rook {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Rook().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int t = readInt();
        String[] inp = new String[t];
        for(int i = 0; i < t; i++)
            inp[i] = readString();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            int currCol = chrToNum(inp[i].charAt(0));
            int currRow = (int)inp[i].charAt(1) - 48;

            for(int j = 1; j <= 8; j++) {
                // j - row
                if (j != currRow) {
                    sb.append(inp[i].charAt(0));
                    sb.append(j);
                    sb.append('\n');
                }
                // j - col
                if (j != currCol) {
                    sb.append(numToChr(j));
                    sb.append(inp[i].charAt(1));
                    sb.append('\n');
                }
            }
        }
        out.print(sb);
    }

    private int chrToNum(char chr) {
        return (int)chr - 96;
    }
    private char numToChr(int num) {
        return (char)(num + 96);
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
