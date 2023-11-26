package com.mycompany.yandex.training40.contest;

import com.mycompany.yandex.training40.warmup.GroupProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class TaskA {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TaskA().run();
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
        int x = readInt();
        int aInd = 1, bInd = 1;
        int targetInd = 0;
        boolean found = false;
        long curr = 0;
        while(!found) {
            targetInd++;
            long aElem = (long)aInd*aInd;
            long bElem = (long)bInd*bInd*bInd;
            if (aElem < bElem) {
                curr = aElem;
                aInd++;
            } else if (aElem > bElem) {
                curr = bElem;
                bInd++;
            } else {
                curr = aElem;
                aInd++;
                bInd++;
            }
            if (targetInd == x) {
                found = true;
            }
        }
        out.println(curr);
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
