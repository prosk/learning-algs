package com.mycompany.yandex.training40.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class GroupProject {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new GroupProject().run();
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
        for(int i = 1; i <= t; i++) {
            int n = readInt();
            int a = readInt();
            int b = readInt();
            String ans = getAns(n, a, b);
            // out.println("For :(" + n + ", " + a + ", " + b + "): ans = " + ans);
            out.println(ans);
        }
    }

    private String getAns(int n, int a, int b) {
        String ans = "NO";
        int rA = n % a;
        if (a + rA <= b) {
            ans = "YES";
        } else {
            int qA = n / a;
            int maxAddedToAGroups = qA*(b-a);
            if (rA <= maxAddedToAGroups)
                ans = "YES";
        }
        return ans;
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
