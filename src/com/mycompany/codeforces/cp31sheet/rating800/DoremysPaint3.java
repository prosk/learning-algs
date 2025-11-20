package com.mycompany.codeforces.cp31sheet.rating800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class DoremysPaint3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new DoremysPaint3().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();

        Map<Integer, Integer> freq = new HashMap<>();
        for(int i = 0; i < n; i++) {
            freq.merge(arr[i], 1, Integer::sum);
            if (freq.size() > 2) {
                break;
            }
        }
        int size = freq.size();
        /*Set<Integer> valuesSet = new HashSet<>(freq.values());
        boolean ans = size == 1 ||
                (size == 2 && n % 2 == 0 && valuesSet.equals(Collections.singleton(n/2))) ||
                (size == 2 && n % 2 == 1 && valuesSet.equals(new HashSet<>(Arrays.asList(n/2, n/2+1))));*/

        boolean ans = false;
        if (size == 1) {
            ans = true;
        } else if (size == 2) {
            Iterator<Integer> iter = freq.values().iterator();
            int v1 = iter.next();
            int v2 = iter.next();
            ans = Math.abs(v1 - v2) <= 1;
        }
        out.println(ans ? "Yes" : "No");
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
