package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Sapper {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    private final int IS_MINE = 9;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Sapper().run();
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
        int rowCnt = readInt();
        int colCnt = readInt();
        int mineCnt = readInt();
        int[][] field = new int[rowCnt+2][colCnt+2];
        int row, col;
        for(int i = 0; i < mineCnt; i++) {
            row = readInt();
            col = readInt();
            field[row][col] = IS_MINE;
        }

        // field calculation
        for(int i = 1; i <= rowCnt; i++) {
            for(int j = 1; j <= colCnt; j++) {
                if (field[i][j] != IS_MINE) {
                    calcCell(field, i, j);
                }
            }
        }

        // print result
        StringBuilder sb = new StringBuilder("");
        for(int i = 1; i <= rowCnt; i++) {
            for(int j = 1; j <= colCnt; j++) {
                String val = (field[i][j] == IS_MINE) ? "*" : String.valueOf(field[i][j]);
                sb.append(val);
                if (j < colCnt) sb.append(" ");
            }
            sb.append('\n');
        }
        out.print(sb);
    }

    private void calcCell(int[][] field, int row, int col) {
        int mineCnt = 0;
        for(int i = row-1; i <= row+1; i++) {
            for(int j = col-1; j <= col+1; j++) {
                if (field[i][j] == IS_MINE) mineCnt++;
            }
        }
        field[row][col] = mineCnt;
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
