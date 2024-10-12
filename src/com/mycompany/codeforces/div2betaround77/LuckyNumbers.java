// package com.mycompany.codeforces.div2betaround77;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LuckyNumbers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new LuckyNumbers().run();
        out.close();
    }

    void run() {
        long n = readInt();

        // list of lucky numbers
        List<Long> list = new ArrayList<>();
        list.add(47L);
        list.add(74L);
        for(int len = 4; len <= 8; len += 2) {
            fillLucky(list, len);
        }
        list.add(4444477777L);

        /*for(long l: list) {
            out.println(l);
        }*/

        // Найдите наименьшее очень счастливое число, которое не меньше n.
        int l = 0, r = list.size() - 1;
        long ans = 0;
        while (l <= r) {
            int mid = l + (r - l)/2;
            long lucky = list.get(mid);
            if (lucky == n) {
                ans = lucky; break;
            } else if (lucky > n) {
                ans = lucky;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        out.println(ans);
    }

    void fillLucky(List<Long> list, int len) {
        char[] str = new char[len];
        genLucky(list, len, str, 0, 0, 0);
    }

    void genLucky(List<Long> list, int len, char[] str, int pos, int cnt4, int cnt7) {
        if (pos == len) {
            list.add(Long.parseLong(new String(str)));
            return;
        }
        int maxCnt = len / 2;
        if (cnt4 == maxCnt) {
            str[pos] = '7';
            genLucky(list, len, str, pos+1, cnt4, cnt7+1);
        } else if (cnt7 == maxCnt) {
            str[pos] = '4';
            genLucky(list, len, str, pos+1, cnt4+1, cnt7);
        } else {
            str[pos] = '4';
            genLucky(list, len, str, pos+1, cnt4+1, cnt7);
            str[pos] = '7';
            genLucky(list, len, str, pos+1, cnt4, cnt7+1);
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