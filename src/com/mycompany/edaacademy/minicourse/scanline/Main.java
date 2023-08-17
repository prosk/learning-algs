package com.mycompany.edaacademy.minicourse.scanline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Main().solve();
        out.close();
    }

    private void solve() {
        // data input
        int segCnt = readInt();
        int pointCnt = readInt();

        // define events
        // 0 - point value, 1 - type (0 - segment start, 1 - point, 2 - segment end)
        // 2 - point index
        int[][] events = new int[segCnt*2 + pointCnt][];
        int j = 0;

        for(int i = 0; i < segCnt; i++) {
            events[j] = new int[2];
            events[j][0] = readInt();
            events[j][1] = 0;
            j++;
            events[j] = new int[2];
            events[j][0] = readInt();
            events[j][1] = 2;
            j++;
        }

        for(int i = 0; i < pointCnt; i++) {
            events[j] = new int[3];
            events[j][0] = readInt();
            events[j][1] = 1;
            events[j][2] = i;
            j++;
        }

        Arrays.sort(events, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return Integer.compare(e1[0], e2[0]);
            } else {
                return Integer.compare(e1[1], e2[1]);
            }
        });
        // get final results
        int[] pointSegCnts = new int[pointCnt];
        int currSegCnt = 0, currType;
        for(int i = 0; i < j; i++) {
            currType = events[i][1];
            if (currType == 0) {
                currSegCnt++;
            } else if (currType == 2) {
                currSegCnt--;
            } else {
                pointSegCnts[events[i][2]] = currSegCnt;
            }
        }
        // print results
        for(int i = 0; i < pointCnt; i++) {
            out.print(pointSegCnts[i]);
            if (i < pointCnt-1) out.print(" ");
        }
        out.println();
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
