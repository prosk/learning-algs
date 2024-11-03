//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ResearchOfEvidence {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ResearchOfEvidence().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
        int m = readInt();
        int k = readInt();
        int[] startNum = new int[m];
        for(int i = 0; i < m; i++) {
            startNum[i] = readInt();
        }
        // solution
        int[] maxLeft = new int[n+1];
        int l = n;
        int equals = 0;
        for(int r = n; r >= 1; r--) {
            // fix right index = r
            l = Math.min(r, l);
            while(l > 1 &&
                    (arr[l-1] < arr[l] || (arr[l-1] == arr[l] && equals < k))) {
                if (arr[l-1] == arr[l]) {
                    equals++;
                }
                l--;
            }
            maxLeft[r] = l;
            if (r > 1 && arr[r-1] == arr[r] && equals > 0) {
                equals--;
            }
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < m; i++) {
            sb.append(maxLeft[startNum[i]]);
            if (i < m-1) sb.append(' ');
        }
        out.println(sb);
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