// package com.mycompany.codeforces.problemset.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://codeforces.com/contest/160/problem/C
public class FindPair {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new FindPair().run();
        out.close();
    }

    void run() {
        int n = readInt();
        long k = readLong();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr);

        List<Elem> freqList = new ArrayList<>();
        long freqSum = 0;
        int curr = arr[0], currFreq = 0;
        for(int i = 0; i <= n; i++) {
            if (i < n && arr[i] == curr) {
                currFreq++;
            } else {
                freqList.add(new Elem(curr, currFreq));
                freqSum += currFreq;
                curr = (i < n) ? arr[i] : 0;
                currFreq = 1;
            }
        }

        /*for(Elem e: freqList) {
            System.out.println(e.val + " " + e.freq);
        }
        System.out.println(freqSum);*/

        int len = freqList.size();
        long firstBound[] = new long[len];
        long prev = 0;
        for(int i = 0; i < len; i++) {
            firstBound[i] = freqList.get(i).freq*freqSum + prev;
            prev = firstBound[i];
        }

        /*for(Long fb: firstBound) {
            System.out.println(fb);
        }*/

        int ind = Arrays.binarySearch(firstBound, k);
        int firstInd = (ind >= 0) ? ind : -1* (ind+1);
        int first = freqList.get(firstInd).val;

        long secondBound[] = new long[len];
        long startValue = firstInd == 0 ? 1 : firstBound[firstInd-1] + 1;

        prev = startValue - 1;
        for(int i = 0; i < len; i++) {
            secondBound[i] = freqList.get(firstInd).freq*freqList.get(i).freq + prev;
            prev = secondBound[i];
        }

        /*System.out.println(firstInd);

        for(Long sb: secondBound) {
            System.out.println(sb);
        }*/

        ind = Arrays.binarySearch(secondBound, k);
        int secondInd = (ind >= 0) ? ind : -1* (ind+1);
        int second = freqList.get(secondInd).val;

        out.printf("%d %d\n", first, second);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    long readLong() {
        return Long.parseLong(readString());
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

    public static class Elem {
        public int val;
        public long freq;

        public Elem(int val, long freq) {
            this.val = val;
            this.freq = freq;
        }
    }
}