package com.mycompany.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

// https://codeforces.com/contest/1788/problem/A
public class OnesAndTwos {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new OnesAndTwos().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int inpSetCnt = readInt();
        for(int i = 0; i < inpSetCnt; i++) {
            int n = readInt();
            int a[] = readIntArray(n);

            // print curr Inp Data
            /*out.println("Curr inp set is " + i);
            out.println("n = " + n);
            out.println("Array is " + Arrays.toString(a));*/

            int res = getResult(a);
            out.println(res);
        }
    }

    private int getResult(int a[]) {
        int twoCnt = 0;
        for(int i = 0; i < a.length; i++) {
            if (a[i] == 2) {
                twoCnt++;
            }
        }

        if (twoCnt == 0) return 1;

        if ((twoCnt % 2) == 0) {
            int midCnt = twoCnt / 2;
            int currTwoCnt = 0;
            for(int i = 0; i < a.length; i++) {
                if (a[i] == 2) {
                    currTwoCnt++;
                    if (currTwoCnt == midCnt) {
                        return i + 1;
                    }
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    private int[] readIntArray(int arrayLength) {
        int res[] = new int[arrayLength];
        for(int j = 0; j < arrayLength; j++) {
            res[j] = readInt();
        }
        return res;
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
