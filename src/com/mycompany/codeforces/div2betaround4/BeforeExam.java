package com.mycompany.codeforces.div2betaround4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BeforeExam {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BeforeExam().run();
        out.close();
    }

    void run() {
        int d = readInt();
        int sumTime = readInt();
        int[] minTime = new int[d], maxTime = new int[d];
        int minSum = 0, maxSum = 0;
        for(int i = 0; i < d; i++) {
            minTime[i] = readInt();
            maxTime[i] = readInt();
            minSum += minTime[i];
            maxSum += maxTime[i];
        }
        if (sumTime < minSum || sumTime > maxSum) {
            out.println("NO");
            return;
        }
        out.println("YES");
        int diff = sumTime - minSum;
        for(int i = 0; i < d; i++) {
            if (diff == 0) {
                out.print(minTime[i]);
                out.print(" ");
                continue;
            }
            int dayDiff = maxTime[i] - minTime[i];
            if (diff <= dayDiff) {
                out.print(minTime[i] + diff);
                diff = 0;
            } else {
                out.print(maxTime[i]);
                diff -= dayDiff;
            }
            out.print(" ");
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
