package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class OfficeRest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new OfficeRest().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long H = readInt();
        List<Chair> chairs = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            long h = readInt();
            chairs.add(new Chair(h, 0));
        }
        boolean isOneChair = false;
        for(int i = 0; i < n; i++) {
            long w = readInt();
            chairs.get(i).w = w;
            if (w >= H) isOneChair = true;
        }
        // solution
        Collections.sort(chairs);
        long maxAns = chairs.get(n-1).h - chairs.get(0).h;
        if (isOneChair || maxAns == 0L) {
            out.println("0");
            return;
        }

        // two pointers and maximums deque
        Deque<HDiff> maxOfHDiff = new ArrayDeque<>();

        long ans = maxAns;
        int right = 0;
        long wSum = chairs.get(0).w;

        for(int left = 0; left < n; left++) {
            while(right < n-1 && wSum < H) {
                right++;
                wSum += chairs.get(right).w;
                long currHDiff = chairs.get(right).h - chairs.get(right-1).h;
                // adding to maximums deque
                while(!maxOfHDiff.isEmpty() && maxOfHDiff.peekLast().hDiff <= currHDiff) {
                    maxOfHDiff.pollLast();
                }
                maxOfHDiff.addLast(new HDiff(currHDiff, right-1));
            }
            if (wSum >= H && !maxOfHDiff.isEmpty()) {
                // update answer
                ans = Math.min(ans, maxOfHDiff.peekFirst().hDiff);
            }
            // remove left elem
            wSum -= chairs.get(left).w;
            // remove from maximum deque
            while (!maxOfHDiff.isEmpty() && (maxOfHDiff.peekFirst().startInd <= left)) {
                maxOfHDiff.pollFirst();
            }
        }
        out.println(ans);
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

    public static class Chair implements Comparable<Chair> {
        long h;
        long w;

        public Chair(long h, long w) {
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Chair o) {
            return Long.compare(this.h, o.h);
        }
    }

    public static class HDiff {
        long hDiff;
        int startInd;

        public HDiff(long hDiff, int startInd) {
            this.hDiff = hDiff;
            this.startInd = startInd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HDiff hDiff1 = (HDiff) o;
            return hDiff == hDiff1.hDiff && startInd == hDiff1.startInd;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hDiff, startInd);
        }
    }
}