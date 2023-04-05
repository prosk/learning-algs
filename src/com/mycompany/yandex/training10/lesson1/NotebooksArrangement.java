package com.mycompany.yandex.training10.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class NotebooksArrangement {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new NotebooksArrangement().run();
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
        int[] a = new int[2];
        a[0] = readInt();
        a[1] = readInt();

        int[] b = new int[2];
        b[0] = readInt();
        b[1] = readInt();

        int t1, t2, area, minArea = Integer.MAX_VALUE;
        Map<Integer, int[]> areas = new HashMap<>();
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                // a[i] и b[j] = общие стороны
                t1 = Math.max(a[i], b[j]);
                t2 = a[1-i] + b[1-j];
                area = t1*t2;
                areas.put(area, new int[]{t1, t2});
                minArea = Math.min(area, minArea);
             }
        }
        int[] res = areas.get(minArea);
        out.println(res[0] + " " + res[1]);
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

