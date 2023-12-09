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
            int[] arrState = new int[N];
            int[] arrZeroState = new int[N];
            for(int j = 0; j < N; j++) {
                arr[j] = readInt();
            }
            Arrays.sort(arr);


            StringBuilder ans = new StringBuilder();
            for(int curr = 0; curr < N; curr++) {
                // curr - deleted elem index
                arrState[curr] = 1; // deleted
                long currAccount = arr[curr];
                int currAns = 0;
                while(currAns < N-1) {
                    // try to find elem to delete
                    int foundedInd = rightBound(arr, arrState, currAccount, 0, N-1);
                    if (foundedInd == -1) {
                        break;
                    } else {
                        currAccount += arr[foundedInd];
                        arrState[foundedInd] = 1; // deleted
                        currAns++;
                    }
                }
                ans.append(currAns);
                if (curr < N-1) ans.append(' ');
                System.arraycopy(arrZeroState, 0, arrState, 0, N);
            }
            out.println(ans);
        }
    }

    // максимальный индекс i, для которого a[i] <= currAccount и aState[i] = 0
    // если все a[i] у которых aState[i] = 0 больше currAccount, вернет -1
    int rightBound(int[] a, int[] aState, long currAccount, int startL, int startR) {
        int res = -1;
        int l = startL, r = startR;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a[mid] <= currAccount) {
                if (aState[mid] == 0) {
                    res = mid;
                    l = mid + 1;
                } else {
                    // запускаем бин поиски слева и справа от mid
                    int res1 = rightBound(a, aState, currAccount, l, mid-1);
                    int res2 = rightBound(a, aState, currAccount, mid+1, r);
                    return Math.max(Math.max(res1, res2), res);
                }
            } else {
                r = mid - 1;
            }
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

    private static class Pair implements Comparable<Pair> {
        public int ind;
        public int val;

        public Pair(int ind, int val) {
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
                    Integer.compare(this.val, o.val);
        }
    }
}
