package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class UnitedWeStand {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new UnitedWeStand().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();

        // solution
        boolean isAllEquals = true;
        int maxVal = arr[0], maxCnt = 1;
        for(int i = 1; i < n; i++) {
            if (arr[i] != arr[i-1]) isAllEquals = false;
            if (arr[i] == maxVal) {
                maxCnt++;
            } else if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxCnt = 1;
            }
        }
        // output
        final int maxValFinal = maxVal;
        if (isAllEquals) {
            out.println("-1");
        } else {
            out.println("" + (n - maxCnt) + " " + maxCnt);
            String bStr = Arrays.stream(arr).filter(e -> e != maxValFinal)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            String cStr = Arrays.stream(arr).filter(e -> e == maxValFinal)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            out.println(bStr);
            out.println(cStr);
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
