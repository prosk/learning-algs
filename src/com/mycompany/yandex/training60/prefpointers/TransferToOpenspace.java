//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class TransferToOpenspace {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        //long timeBegin = System.currentTimeMillis();
        new TransferToOpenspace().run();
        //long timeEnd = System.currentTimeMillis();
        //out.println("Time = " + (timeEnd - timeBegin));
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n+1];
        long[] pref = new long[n+2];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            pref[i+1] = pref[i] + arr[i];
        }

        BigInteger first = BigInteger.valueOf(0L);
        long koeff = 0;
        for(int i = 1; i <= n; i++) {
            first = first.add(BigInteger.valueOf(koeff * (long)arr[i]));
            koeff++;
        }

        BigInteger ans = first, curr = first;
        for(int i = 2; i <= n; i++) {
            long minusSum = getSum(pref, i, n);
            BigInteger afterMinus = curr.subtract(BigInteger.valueOf(minusSum));
            long plusSum = getSum(pref, 1, i-1);
            BigInteger next = afterMinus.add(BigInteger.valueOf(plusSum));
            ans = ans.min(next);
            curr = next;
        }

        out.println(ans);
    }

    long getSum(long pref[], int i, int j) {
        return pref[j+1] - pref[i];
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