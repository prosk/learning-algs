package com.mycompany.codeforces.div2round2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class CollectionGame {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CollectionGame().run();
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
        int t = readInt();

        for(int i = 0; i < t; i++) {
            int N = readInt();
            int[] arr = new int[N];
            for(int j = 0; j < N; j++) {
                arr[j] = readInt();
            }

            StringBuilder ans = new StringBuilder();
            for(int curr = 0; curr < N; curr++) {
                // curr - deleted elem index
                TreeSet<Pair> currSet = new TreeSet<>();
                for(int j = 0; j < N; j++) {
                    if (j != curr)
                        currSet.add(new Pair(j, arr[j]));
                }
                long currAccount = arr[curr];
                int currAns = 0;
                while(!currSet.isEmpty()) {
                    // try to find elem to delete
                    Pair foundedElem = currSet.lower(new Pair(-1, currAccount+1));
                    if (foundedElem == null) {
                        break;
                    } else {
                        currAccount += foundedElem.val;
                        currSet.remove(foundedElem);
                        currAns++;
                    }
                }
                ans.append(currAns);
                if (curr < N-1) ans.append(' ');
            }
            out.println(ans);
        }
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

    private static class Pair implements Comparable<Pair> {
        public int ind;
        public long val;

        public Pair(int ind, long val) {
            this.ind = ind;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return ind == pair.ind && val == pair.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ind, val);
        }

        @Override
        public int compareTo(Pair o) {
            return (this.val == o.val) ? Integer.compare(this.ind, o.ind) :
                    Long.compare(this.val, o.val);
        }
    }
}
