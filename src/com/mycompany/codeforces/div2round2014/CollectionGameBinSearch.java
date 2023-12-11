package com.mycompany.codeforces.div2round2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class CollectionGameBinSearch {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CollectionGameBinSearch().run();
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

        for(; t > 0; t--) {
            int N = readInt();
            List<Pair> arr = new ArrayList<>(N);
            for(int i = 0; i < N; i++) {
                arr.add(new Pair(i, readInt()));
            }

            Collections.sort(arr);
            long[] prefSum = new long[N+1];
            prefSum[0] = 0;
            for (int i = 1; i <= N; i++) {
                prefSum[i] = prefSum[i - 1] + (long)arr.get(i - 1).val;
            }

            int[] ans = new int[N];
            Map<Integer, Integer> answers = new HashMap<>();
            for(int curr = 0; curr < N; curr++) {
                // get answer for curr
                Pair currPair = arr.get(curr);

                Integer readyAns = answers.get(currPair.val);
                if (readyAns != null) {
                    ans[currPair.ind] = readyAns;
                    continue;
                }

                int maxNotEqual = curr+1;
                while(maxNotEqual < N && arr.get(maxNotEqual).val == arr.get(curr).val)
                    maxNotEqual++;
                int maxLeftInd = maxNotEqual;
                int cnt = maxLeftInd - 1;
                long currAmount = prefSum[maxLeftInd];
                while(maxLeftInd < N) {
                    int rightBoundInd = rightBound(arr, currAmount, maxLeftInd, N-1);
                    if (rightBoundInd == -1)
                        break;
                    cnt += (rightBoundInd - maxLeftInd + 1);
                    currAmount = prefSum[rightBoundInd+1];
                    maxLeftInd = rightBoundInd + 1;
                }
                ans[currPair.ind] = cnt;
                answers.put(currPair.val, cnt);
            }

            StringBuilder ansStr = new StringBuilder();
            for(int i = 0; i < N; i++) {
                ansStr.append(ans[i]);
                if (i < N-1) ansStr.append(' ');
            }
            out.println(ansStr);
        }

    }

    // максимальный индекс i, для которого a[i] <= currAccount
    // если все a[i] больше right, вернет -1
    int rightBound(List<Pair> a,long currAccount, int startL, int startR) {
        int res = -1;
        int l = startL, r = startR;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a.get(mid).val <= currAccount) {
                res = mid;
                l = mid + 1;
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
