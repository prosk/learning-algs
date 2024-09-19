// package com.mycompany.cses.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberSpiral {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new NumberSpiral().run();
        out.close();
    }

    void run() {
        int t = readInt();
        for (int i = 0; i < t; i++) {
            long row = readInt();
            long col = readInt();
            long ans = getVal(row, col);
            out.println(ans);
        }
    }

    private long getVal(long row, long col) {
        if (col >= row) {
            // upper right
            if ((col % 2) == 1) {
                // odd column number
                long colStartVal = (col - 1)*(col - 1) + (col*2 - 1);
                return colStartVal - (row-1);
            } else {
                // even column number
                col--;
                long colStartVal = (col - 1)*(col - 1) + (col*2 - 1) + 1;
                return colStartVal + (row-1);
            }
        } else {
            // lower left (row > col, row > 1)
            if ((row % 2) == 0) {
                // even row num
                long rowStartVal = (row - 1)*(row - 1) + (row*2 - 1);
                return rowStartVal - (col-1);
            } else {
                // odd row num
                row--;
                long rowStartVal = (row - 1)*(row - 1) + (row*2 - 1) + 1;
                return rowStartVal + (col-1);
            }

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
