package com.mycompany.codeforces.div2betaround4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://codeforces.com/contest/4
public class RegistrationSystem {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new RegistrationSystem().run();
        out.close();
    }

    void run() {
        HashMap<String, Integer> db = new HashMap<>();
        int n = readInt();
        for(int i = 0; i < n; i++) {
            String s = readString();
            int cnt = db.getOrDefault(s, 0);
            if (cnt == 0) {
                out.println("OK");
            } else {
                String hintName = s + cnt;
                out.println(hintName);
            }
            db.merge(s, 1, Integer::sum);
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
