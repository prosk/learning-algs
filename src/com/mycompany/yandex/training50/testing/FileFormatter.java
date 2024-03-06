package com.mycompany.yandex.training50.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FileFormatter {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new FileFormatter().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        long ans = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            int q = arr[i] / 4, r = arr[i] % 4;
            ans += q;
            if (r == 1)
                ans++;
            else if (r == 2 || r == 3)
                ans += 2;
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
