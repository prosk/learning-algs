package com.mycompany.yandex.training40.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class MoscowTrip {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MoscowTrip().run();
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
        double x1 = (double) readInt();
        double y1 = (double) readInt();
        double x2 = (double) readInt();
        double y2 = (double) readInt();

        double angleA = Math.atan2(y1, x1);
        double angleB = Math.atan2(y2, x2);

        double angleAB = angleB - angleA;
        while (angleAB > Math.PI)
            angleAB -= 2*Math.PI;
        while (angleAB < -Math.PI)
            angleAB += 2*Math.PI;

        double lenA = Math.sqrt(x1*x1 + y1*y1);
        double lenB = Math.sqrt(x2*x2 + y2*y2);

        double maxLen = Math.max(lenA, lenB);
        double minLen = Math.min(lenA, lenB);

        double ansForLine = lenA + lenB;
        double ansForArc = (maxLen - minLen) + Math.abs(minLen*angleAB);
        double ans = Math.min(ansForLine, ansForArc);

        out.printf("%.9f\n", ans);
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
