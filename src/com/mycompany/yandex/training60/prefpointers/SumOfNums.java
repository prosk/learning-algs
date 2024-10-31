//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SumOfNums {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new SumOfNums().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long k = readInt();
        long[] arr = new long[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        Map<Long, Long> pref = new HashMap<>();
        pref.put(0L, 1L);
        long sum = 0, ans = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            // sum - prevSum = k
            long cnt = pref.getOrDefault(sum - k, 0L);
            ans += cnt;
            pref.merge(sum, 1L, Long::sum);
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