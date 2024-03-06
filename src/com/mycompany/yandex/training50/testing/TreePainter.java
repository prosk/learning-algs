package com.mycompany.yandex.training50.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TreePainter {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TreePainter().run();
        out.close();
    }

    void run() {
        int treeNum1 = readInt();
        int treeDist1 = readInt();

        int treeNum2 = readInt();
        int treeDist2 = readInt();

        int start1 = treeNum1 - treeDist1, end1 = treeNum1 + treeDist1;
        int start2 = treeNum2 - treeDist2, end2 = treeNum2 + treeDist2;

        int startAns = (end1 - start1 + 1) + (end2 - start2 + 1);
        int ans;
        if (end1 < start2 || end2 < start1) {
            ans = startAns;
        } else {
            int sMax = Math.max(start1, start2);
            int eMin = Math.min(end1, end2);
            ans  = startAns - (eMin - sMax + 1);
        }

        out.println(ans);
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
