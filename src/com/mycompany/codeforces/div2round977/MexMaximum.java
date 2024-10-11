package com.mycompany.codeforces.div2round977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MexMaximum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MexMaximum().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int x = readInt();
        Map<Integer, Integer> nums = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int elem = readInt();
            nums.merge(elem, 1, Integer::sum);
        }
        int num = 0;
        while(true) {
            int cnt = nums.getOrDefault(num, 0);
            boolean found = false;
            if (cnt == 0) {
                // how to get num with x
                for(int prev = num - x; prev >= 0; prev -= x) {
                    int prevCnt = nums.getOrDefault(prev, 0);
                    if (prevCnt > 1) {
                        found = true;
                        nums.put(prev, prevCnt - 1);
                        break;
                    }
                }
            } else {
                found = true;
            }
            if (!found) break;
            num++;
        }
        out.println(num);
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