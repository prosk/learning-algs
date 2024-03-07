package com.mycompany.yandex.training50.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ChessCalculator {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ChessCalculator().run();
        out.close();
    }

    void run() {
        String[] input = new String[8];
        char[][] board = new char[8][8];
        for(int i = 0; i < 8; i++) {
            input[i] = readString();
            for(int j = 0; j < 8; j++) {
                board[i][j] = input[i].charAt(j);
            }
        }
        int ans = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (board[i][j] == '*') {

                }
            }
        }
        out.println(" ");
    }

    boolean rExists(char[][] board, int row, int col) {
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] direction: directions) {
            int i = row, j = col;
            while(true) {
                i += direction[0];
                j += direction[1];
            }
        }
        return true;
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

