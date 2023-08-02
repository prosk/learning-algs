package com.mycompany.yandex.training10.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class EquationSystem {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new EquationSystem().run();
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
        double a = readDouble();
        double b = readDouble();
        double c = readDouble();
        double d = readDouble();
        double e = readDouble();
        double f = readDouble();

        String abcd = getSymbol(a) + getSymbol(b) + getSymbol(c) + getSymbol(d);

        String ans = "";
        double y1, y2, x1, x2, ansX, ansY;
        boolean efIsZero = (e == 0d && f == 0d);
        switch (abcd) {
            case "0000":
                ans = (e == 0d && f == 0d) ? "5" : "0";
                break;
            case "0001":
                ans = (e == 0d) ? ("4 " + f / d) : "0";
                break;
            case "0010":
                ans = (e == 0d) ? ("3 " + f / c) : "0";
                break;
            case "0011":
                ans = (e == 0d) ? ("1 " + (-c / d) + " " + f / d) : "0";
                break;
            case "0100":
                ans = (f == 0d) ? ("4 " + e / b) : "0";
                break;
            case "0101":
                y1 = e / b;
                y2 = f / d;
                ans = (Double.compare(y1, y2) == 0 || efIsZero) ? ("4 " + y1) : "0";
                break;
            case "0110":
                ansY = e / b;
                ansX = f / c;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "0111":
                ansY = e / b;
                ansX = (f - d*ansY) / c;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "1000":
                ans = (f == 0d) ? ("3 " + e / a) : "0";
                break;
            case "1001":
                ansY = f / d;
                ansX = e / a;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "1010":
                x1 = e / a;
                x2 = f / c;
                ans = (Double.compare(x1, x2) == 0 || efIsZero) ? ("3 " + x1) : "0";
                break;
            case "1011":
                ansX = e / a;
                ansY = (f - c*ansX) / d;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "1100":
                ans = (f == 0d) ? ("1 " + (-a / b) + " " + e / b) : "0";
                break;
            case "1101":
                ansY = f / d;
                ansX = (e - b*ansY) / a;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "1110":
                ansX = f / c;
                ansY = (e - a*ansX) / b;
                ans = "2 " + ansX + " " + ansY;
                break;
            case "1111":
                ans = getKramerSolution(a, b, c, d, e, f);
                break;
            default:
                ans = "0";
        }
        out.println(ans);
    }

    private String getSymbol(double d) {
        return (d == 0d) ? "0" : "1";
    }

    private String getKramerSolution(double a, double b, double c, double d, double e, double f) {
        double det = a*d - c*b;
        double detX = e*d - f*b;
        double detY = a*f - c*e;
        boolean detIsZero = Double.compare(det, 0d) == 0;
        boolean detXisZero = Double.compare(detX, 0d) == 0;
        boolean detYisZero = Double.compare(detY, 0d) == 0;

        if (detIsZero && detXisZero && detYisZero) {
            return "1 " + (-a / b) + " " + e / b;
        }
        if (detIsZero) {
            return "0";
        }

        double ansX = detX / det;
        double ansY = detY / det;
        return "2 " + ansX + " " + ansY;
    }


    private double readDouble() {
        return Double.parseDouble(readString());
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
