package com.mycompany.yandex.training60.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PointAndRaft {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new PointAndRaft().run();
        out.close();
    }

    void run() {
        int x1 = readInt();
        int y1 = readInt();
        int x2 = readInt();
        int y2 = readInt();
        int x = readInt();
        int y = readInt();

        String ans = "";
        if (x <= x1 && y >= y2) {
            ans = "NW";
        } else if (x >= x2 && y >= y2) {
            ans = "NE";
        } else if (x <= x1 && y <= y1) {
            ans = "SW";
        } else if (x >= x2 && y <= y1) {
            ans = "SE";
        } else {
            if (y > y2) ans = "N";
            else if (x < x1) ans = "W";
            else if (x > x2) ans = "E";
            else if (y < y1) ans = "S";
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