// package com.mycompany.codeforces.problemset.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class FindPairSimple {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new FindPairSimple().run();
        out.close();
    }

    void run() {
        long n = readInt();
        long k = readLong();

        Map<Long, Long> freq = new HashMap<>();
        for(int i = 0; i < n; i++) {
            long elem = readInt();
            freq.merge(elem, 1L, Long::sum);
        }
        List<Map.Entry<Long, Long>> sortedFreq = freq.entrySet()
            .stream().sorted(Comparator.comparingLong(x -> x.getKey())).toList();

        // first
        int firstInd = 0;
        for(int i = 0; i < sortedFreq.size(); i++) {
            long iFreq = sortedFreq.get(i).getValue();
            long iFirstPairsCnt = iFreq*n;
            if (k <= iFirstPairsCnt) {
                firstInd = i; break;
            }
            k -= iFirstPairsCnt;
        }
        // second
        int secondInd = 0;
        long firstFreq = sortedFreq.get(firstInd).getValue();
        for(int i = 0; i < sortedFreq.size(); i++) {
            long iFreq = sortedFreq.get(i).getValue();
            long pairs = firstFreq*iFreq;
            if (k <= pairs) {
                secondInd = i; break;
            }
            k -= pairs;
        }

        out.printf("%d %d\n", sortedFreq.get(firstInd).getKey(),
                              sortedFreq.get(secondInd).getKey());
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
}