package com.mycompany.yandex.interview.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BinaryArrayZeroPos {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new BinaryArrayZeroPos().run();
        out.close();
    }

    /*
       ЗАДАЧА
       Для массива из нулей и единиц надо найти позицию pos, для которой значение
       равно 0, а расстояние до ближайшей единицы максимально
     */

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        int pos = solve(arr);
        out.println(pos);
    }

    int solve(int[] arr) {
        int len = arr.length;
        int lastOnePos = -1;
        int cnt = 0, pos = -1;
        int ans = 0;   // 101  ans = 1
        for(int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                cnt++;
            } else {
                if (cnt == 0) {
                    lastOnePos = i;
                    continue;
                }
                if (lastOnePos >= 0) {
                    // слева 1     101  1001  1000001
                    int dist = (cnt + 1)/2;
                    if (dist > ans) {
                        ans = dist;
                        pos = lastOnePos + dist;
                    }
                } else {
                    // слева край 01  001  0001 00001
                    ans = i;
                    pos = 0;
                }
                cnt = 0;
                lastOnePos = i;
            }
        }
        if (cnt > 0) {
            int dist = cnt;
            if (dist > ans) {
                pos = len-1;
            }
        }
        return pos;
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