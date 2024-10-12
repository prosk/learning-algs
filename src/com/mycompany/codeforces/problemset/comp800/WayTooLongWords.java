// package com.mycompany.codeforces.problemset.comp800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WayTooLongWords {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new WayTooLongWords().run();
        out.close();
    }

    void run() {
        int n = readInt();
        while(n > 0) {
            String word = readString();
            int len = word.length();
            if (len > 10)
                out.println(word.charAt(0) + String.valueOf(len-2) + word.charAt(len-1));
            else
                out.println(word);
            n--;
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