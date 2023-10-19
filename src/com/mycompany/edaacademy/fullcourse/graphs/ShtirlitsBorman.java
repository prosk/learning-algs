package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class ShtirlitsBorman {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ShtirlitsBorman().run();
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
        int n = readInt();
        int[][] adj = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                adj[i][j] = readInt();
            }
        }

        int minLength = 5000, currLength;
        int[] ans = new int[3];
        for(int i = 1; i <= n; i++) {
            for(int j = i+1; j <= n; j++) {
                for(int k = j+1; k <= n; k++) {
                    currLength = adj[i][j] + adj[j][k] + adj[k][i];
                    if (currLength < minLength) {
                        minLength = currLength;
                        ans[0] = i; ans[1] = j; ans[2] = k;
                    }
                }
            }
        }
        out.println(ans[0] + " " + ans[1] + " " + ans[2]);
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
