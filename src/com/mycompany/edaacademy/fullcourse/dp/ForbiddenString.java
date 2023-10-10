package com.mycompany.edaacademy.fullcourse.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ForbiddenString {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ForbiddenString().run();
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

    long getAns(int inputNum) {
        long[] a = new long[inputNum+5];
        long[] b = new long[inputNum+5];

        int[] smallAns = {0, 3, 9, 21};

        a[2] = 1;
        a[3] = 3;

        b[2] = 1;
        b[3] = 2;

        if (inputNum <= 3) {
            return smallAns[inputNum];
        } else {
            for(int i = 4; i <= inputNum; i++) {
                a[i] = 3*a[i-2] + 4*b[i-2];
                b[i] = 2*a[i-2] + 3*b[i-2];
            }
            long ans = 3 * a[inputNum] + 6 * b[inputNum];
            return ans;
        }
    }

    private void solve() {
        while(true) {
            String inp = readString();
            if (inp == null) break;
            long ans = getAns(Integer.parseInt(inp));
            out.println(ans);
        }

        /*Set<String> forbidden = new HashSet<>(Arrays.asList("123", "132", "213", "231", "312", "321"));
        char[] symbols = new char[]{' ', 'A', 'B', 'C'};
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= 3; j++) {
                for(int k = 1; k <= 3; k++) {
                    String s = String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
                    if (!forbidden.contains(s)) {
                        String res = String.valueOf(symbols[i]) + String.valueOf(symbols[j]) +
                                String.valueOf(symbols[k]);
                        out.println(res);
                    }
                }
            }
        }*/
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
