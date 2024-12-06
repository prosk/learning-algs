package com.mycompany.codeforces.streams.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://codeforces.com/group/yeVhAfeK6s/contest/571840/problem/A
public class InterestingDrink {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new InterestingDrink().run();
        out.close();
    }

    void run() {
        int shopCnt = readInt();
        int[] shopPrices = new int[shopCnt];
        for(int i = 0; i < shopCnt; i++) {
            shopPrices[i] = readInt();
        }
        int daysCnt = readInt();
        int[] daysAmount = new int[daysCnt];
        for(int i = 0; i < daysCnt; i++) {
            daysAmount[i] = readInt();
        }
        // solve
        StringBuilder sb = new StringBuilder();
        Arrays.sort(shopPrices);
        for(int i = 0; i < daysCnt; i++) {
            int ans = getShopCnt(shopPrices, daysAmount[i]);
            sb.append(ans);
            sb.append('\n');
        }
        out.print(sb);
    }

    int getShopCnt(int[] shopPrices, int amount) {
        int maxInd = -1;
        // max index where price <= amount
        int l = 0, r = shopPrices.length-1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (shopPrices[mid] <= amount) {
                maxInd = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return maxInd+1;
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