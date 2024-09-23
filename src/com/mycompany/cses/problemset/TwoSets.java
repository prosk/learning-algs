// package com.mycompany.cses.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TwoSets {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TwoSets().run();
        out.close();
    }

    void run() {
        long n = readInt();
        long sum = n*(n+1)/2;
        if (sum % 2 == 1) {
            out.println("NO");
            return;
        }
        // division is possible
        long r = n % 4;
        if (r == 1 || r == 2) {
            out.println("NO");
            return;
        }
        // r == 0 || r == 3
        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();
        int minValue = (r == 0) ? 1 : 4;
        if (r == 3) {
            set1.add(1);
            set1.add(2);
            set2.add(3);
        } else if (n == 4) {
            set1.add(1);
            set1.add(4);
            set2.add(3);
            set2.add(2);
        }
        if (n > 4) {
            for (int i = (int) n; i >= minValue; i -= 4) {
                set1.add(i);
                set1.add(i-3);

                set2.add(i-1);
                set2.add(i-2);
            }
        }
        out.println("YES");
        out.println(set1.size());
        out.println(set1.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(" ")));
        out.println(set2.size());
        out.println(set2.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(" ")));
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
